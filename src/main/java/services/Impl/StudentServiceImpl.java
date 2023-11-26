package services.Impl;

import base.service.Impl.BaseEntityServiceImpl;
import domain.Course;
import domain.Student;
import org.hibernate.Session;
import repositories.Impl.StudentRepositoryImpl;
import repositories.StudentRepository;
import services.StudentService;
import utility.SessionFactoryProvider;

import java.util.List;

public class StudentServiceImpl
        extends BaseEntityServiceImpl<Student, Long, StudentRepository>
        implements StudentService {

    Session session = SessionFactoryProvider.getSessionFactory().openSession();
    private  StudentRepositoryImpl studentRepository;

    public StudentServiceImpl(StudentRepository baseEntityRepository) {
        super(baseEntityRepository);
    }


    @Override
    public void addCourseToStudent(Long studentId, Course course) {
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
    }


    @Override
    public List<Course> seeCourses(Long studentId) {
        return studentRepository.seeCourses(studentId);
    }

    @Override
    public List<Course> notPassesCourses(Long studentId) {
        return studentRepository.notPassesCourses(studentId);
    }

    @Override
    public double getAverageMarksForStudent(Long studentId) {
        return studentRepository.getAverageMarksForStudent(studentId);
    }

    @Override
    public List<Course> passesCoursesWithMarks(Long studentId) {
        return studentRepository.passesCoursesWithMarks(studentId);
    }
}
