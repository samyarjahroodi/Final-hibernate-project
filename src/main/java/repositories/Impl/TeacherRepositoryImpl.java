package repositories.Impl;

import base.repository.Impl.BaseEntityRepositoryImpl;
import entity.Course;
import entity.student_Course;
import entity.Student;
import entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repositories.TeacherRepository;

import java.util.List;
import java.util.Set;

public class TeacherRepositoryImpl
        extends BaseEntityRepositoryImpl<Teacher, Long>
        implements TeacherRepository {

    Session session;

    public TeacherRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Teacher seeTeacherItems(Teacher teacher, Long id) {

        session.beginTransaction().begin();
        session.load(teacher, id);
        session.beginTransaction().commit();
        return null;
    }

    @Override
    public void giveMarkToStudents(Long id, Course course, int markValue) {
        try {
            session.getTransaction().begin();
            if (id != null && id.equals(course.getTeacher().getId())) {
                Set<student_Course> student_courses = course.getStudent_courses();
                for (student_Course sc : student_courses) {
                    sc.setMark(markValue);
                    sc.setPass(isPass(markValue));
                    session.saveOrUpdate(sc);
                }
            } else {
                throw new Exception("You are not authorized to give marks for this course.");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }


    private boolean isPass(int markValue) {
        return markValue >= 10;
    }


    public Integer getTotalUnitsForTeacher(Long teacherId) {
        try {
            session.beginTransaction();

            String hql = "SELECT SUM(c.unit) FROM Course c WHERE c.teacher.id = :teacherId";
            Query query = session.createQuery(hql);
            query.setParameter("teacherId", teacherId);
            Integer totalUnits = (Integer) query.uniqueResult();

            session.getTransaction().commit();

            return totalUnits != null ? totalUnits : 0;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return 0;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }

    @Override
    public String getCodeName() {
        return "teacherCode";
    }
}
