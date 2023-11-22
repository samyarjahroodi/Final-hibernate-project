package repositories.Impl;

import base.repository.Impl.BaseEntityRepositoryImpl;
import entity.Course;
import entity.Student;
import org.hibernate.Session;
import repositories.StudentRepository;
import utility.SessionFactoryProvider;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class StudentRepositoryImpl
        extends BaseEntityRepositoryImpl<Student, Long>
        implements StudentRepository {
    Session session = SessionFactoryProvider.getSessionFactory().openSession();


    @Override
    public double getAverageMarksForStudent(Long studentId) {
        try {
            session.beginTransaction();
            String hql = "SELECT AVG(m.mark) FROM Mark m WHERE m.students.id = :studentId";
            Query query = session.createQuery(hql);
            query.setParameter("studentId", studentId);
            Double averageMarks = (Double) query.getSingleResult();
            session.getTransaction().commit();

            return averageMarks != null ? averageMarks : 0.0;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return 0.0;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Course> passesCoursesWithMarks(Long studentId) {
        try {
            session.beginTransaction().begin();
            session.

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return Collections.emptyList();
        }
    }


    public void addCourseToStudent(Long studentId, Course course) {
        try {
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                List<Course> courses = student.getCourses();
                if (getAverageMarksForStudent(studentId) >= 18) {
                    if (!courses.contains(course) && course.getUnit() < 24) {
                        while (course.getUnit() <= 24) {
                            courses.add(course);
                            student.setCourses(courses);
                        }
                        session.saveOrUpdate(student);
                    }
                } else if (getAverageMarksForStudent(studentId) < 18 &&
                        getAverageMarksForStudent(studentId) >= 12) {
                    if (!courses.contains(course) && course.getUnit() < 20) {
                        while (course.getUnit() <= 20) {
                            courses.add(course);
                            student.setCourses(courses);
                        }
                        session.saveOrUpdate(student);
                    }
                } else if (getAverageMarksForStudent(studentId) < 12) {
                    if (!courses.contains(course) && course.getUnit() < 14) {
                        while (course.getUnit() <= 14) {
                            courses.add(course);
                            student.setCourses(courses);
                        }
                        session.saveOrUpdate(student);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> seeCourses(Long studentId) {
        try {
            session.getTransaction().begin();
            Student student = session.get(Student.class, studentId);
            session.getTransaction().commit();
            return student.getCourses();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return Collections.emptyList();
        }
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public List<Course> passesCourses(Long studentId) {
        try {
            session.beginTransaction().begin();
            Student student = session.get(Student.class, studentId);
            List<Course> allCourses = student.getCourses();
            session.getTransaction().commit();
            return allCourses.stream().filter(Course::isPass).toList();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().commit();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Course> notPassesCourses(Long studentId) {
        try {
            session.beginTransaction().begin();
            Student student = session.get(Student.class, studentId);
            List<Course> allCourses = student.getCourses();
            session.getTransaction().commit();
            return allCourses.stream().filter(Course -> !Course.isPass()).toList();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().commit();
            return Collections.emptyList();
        }
    }

}
