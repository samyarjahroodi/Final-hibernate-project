package services.Impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.Course;
import entity.student_Course;
import entity.Student;
import org.hibernate.Session;
import repositories.Impl.StudentRepositoryImpl;
import repositories.StudentRepository;
import services.StudentService;
import utility.SessionFactoryProvider;

import java.util.List;
import java.util.Set;

public class StudentServiceImpl
        extends BaseEntityServiceImpl<Student, Long, StudentRepository>
        implements StudentService {

    Session session = SessionFactoryProvider.getSessionFactory().openSession();
    private StudentRepositoryImpl studentRepository;

    public StudentServiceImpl(StudentRepository baseEntityRepository) {
        super(baseEntityRepository);
    }

    @Override
    public Set<student_Course> addCourseToStudent(Long studentId, Long id) {
        Student student = session.get(Student.class, studentId);
        if (student != null) {
            List<Course> courses = notPassesCourses(studentId);

            Set<student_Course> student_courses = student.getStudent_courses();
            int totalUnits = student_courses.stream().mapToInt(rc -> rc.getCourse().getUnit()).sum();

            int unitLimit = 0;
            double averageMarks = getAverageMarksForStudent(studentId);

            if (averageMarks >= 18) {
                unitLimit = 24;
            } else if (averageMarks >= 12) {
                unitLimit = 20;
            } else {
                unitLimit = 14;
            }

            for (Course course : courses) {
                if (!student_courses.stream().anyMatch(rc -> rc.getCourse().equals(course)) &&
                        (totalUnits + course.getUnit()) <= unitLimit) {
                    student_Course student_course = new student_Course();
                    student_course.setCourse(course);
                    student_course.setStudents(student);
                    session.saveOrUpdate(student_course);
                }
            }
        }
        return null;
    }


//    @Override
//    public void addCourseToStudent(Long studentId, Course course) {
//        Student student = session.get(Student.class, studentId);
//        if (student != null) {
//            List<Course> courses = student.getCourses();
//            if (getAverageMarksForStudent(studentId) >= 18) {
//                if (!courses.contains(course) && course.getUnit() < 24) {
//                    while (course.getUnit() <= 24) {
//                        courses.add(course);
//                        student.setCourses(courses);
//                    }
//                    session.saveOrUpdate(student);
//                }
//            } else if (getAverageMarksForStudent(studentId) < 18 &&
//                    getAverageMarksForStudent(studentId) >= 12) {
//                if (!courses.contains(course) && course.getUnit() < 20) {
//                    while (course.getUnit() <= 20) {
//                        courses.add(course);
//                        student.setCourses(courses);
//                    }
//                    session.saveOrUpdate(student);
//                }
//            } else if (getAverageMarksForStudent(studentId) < 12) {
//                if (!courses.contains(course) && course.getUnit() < 14) {
//                    while (course.getUnit() <= 14) {
//                        courses.add(course);
//                        student.setCourses(courses);
//                    }
//                    session.saveOrUpdate(student);
//                }
//            }
//        }
//    }


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

    @Override
    public Student getExistedStudent(Long id) {
        return studentRepository.getExistedStudent(id);
    }

    @Override
    public Long getIdBasedOnNationalCodeAndCodeForStudent(String nationalCode, String studentCode) {
        return studentRepository.getIdBasedOnNationalCodeAndCodeForStudent(nationalCode, studentCode);
    }
}
