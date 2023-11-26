package repositories.Impl;

import base.repository.Impl.BaseEntityRepositoryImpl;
import entity.Course;
import entity.Mark;
import entity.Student;
import entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repositories.TeacherRepository;

import java.util.List;

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
    public void giveMarkToStudents(Course course, int markValue) {
        try {
            session.getTransaction().begin();
            List<Student> students = course.getStudents();
            for (Student s : students) {
                Mark mark = new Mark();
                mark.setStudents((Student) students);
                mark.setCourse(course);
                mark.setTeacher(course.getTeacher());
                mark.setMark(markValue);
                session.saveOrUpdate(mark);
                session.beginTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.beginTransaction().rollback();
        }
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
