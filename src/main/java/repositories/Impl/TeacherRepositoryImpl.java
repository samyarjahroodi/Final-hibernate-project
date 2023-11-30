package repositories.Impl;

import base.repository.Impl.BaseEntityRepositoryImpl;
import entity.Course;
import entity.student_Course;
import entity.Student;
import entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repositories.TeacherRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TeacherRepositoryImpl
        extends BaseEntityRepositoryImpl<Teacher, Long>
        implements TeacherRepository {

    public TeacherRepositoryImpl(Session session) {
        super(session);
    }


    @Override
    public void giveMarkToStudents(Long id, Course course, int markValue) {
        try {
            openSession();
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
            commitSession();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        }
    }

    public Long getIdBasedOnNationalCodeAndCodeForTeacher(String nationalCode, String teacherCode) {

        String hql = "SELECT id FROM Teacher WHERE nationalCode = :nationalCode AND teacherCode = :teacherCode";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("nationalCode", nationalCode);
        query.setParameter("teacherCode", teacherCode);
        return query.uniqueResult();

    }


    public Integer getTotalUnitsForTeacher(Long teacherId) {

        String hql = "SELECT SUM(c.unit) FROM Course c WHERE c.teacher.id = :teacherId";
        Query query = session.createQuery(hql, Teacher.class);
        query.setParameter("teacherId", teacherId);
        Integer totalUnits = (Integer) query.uniqueResult();
        return totalUnits != null ? totalUnits : 0;

    }


    @Override
    public Set<Course> teacherCourse(Long id) {
        Teacher teacher = session.get(Teacher.class, id);
        return teacher.getCourses();

    }

    @Override
    public List<Long> teacherCoursesBasedOnId(Long id) {
        try {
            String hql = "SELECT c.id FROM Course c " +
                    "JOIN c.teacher t " +
                    "WHERE t.id = :teacherId";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("teacherId", id);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return Collections.emptyList();
        }
    }


    @Override
    public Boolean scienceCommittee(Long id) {
        Teacher teacher = session.get(Teacher.class, id);
        return teacher.getScienceCommittee();
    }

    @Override
    public List<Student> seeStudentOfCourse(Long teacherId, Long courseId) {
        try {
            String hql = "SELECT sc.students FROM student_Course sc " +
                    "JOIN sc.course c " +
                    "JOIN c.teacher t " +
                    "WHERE t.id = :teacherId AND c.id = :courseId";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("teacherId", teacherId);
            query.setParameter("courseId", courseId);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Teacher getExistedTeacher(Long id) {
        return session.get(Teacher.class, id);
    }

    private boolean isPass(int markValue) {
        return markValue >= 10;
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
