package repositories.Impl;

import base.repository.Impl.BaseEntityRepositoryImpl;
import entity.Course;
import entity.student_Course;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import repositories.StudentRepository;

import javax.persistence.Query;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentRepositoryImpl
        extends BaseEntityRepositoryImpl<Student, Long>
        implements StudentRepository {

    public StudentRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public double getAverageMarksForStudent(Long studentId) {

        String hql = "SELECT AVG(m.mark) FROM student_Course  m WHERE m.students.id = :studentId";
        Query query = session.createQuery(hql);
        query.setParameter("studentId", studentId);
        Double averageMarks = (Double) query.getSingleResult();
        return averageMarks != null ? averageMarks : 0.0;

    }

    @Override
    public List<Course> passesCoursesWithMarks(Long studentId) {

        String hql = "SELECT m.course FROM student_Course m WHERE m.students.id = :studentId AND m.isPass = true";
        Query query = session.createQuery(hql, Course.class);
        query.setParameter("studentId", studentId);
        List<Course> passedCourses = query.getResultList();
        return passedCourses;
    }

    @Override
    public Set<Course> addCourseToStudent(Long studentId, Long courseId) {
        Set<Course> updatedCourses = new HashSet<>();

        try {
            openSession();

            // Retrieve the student and course entities
            Student student = session.get(Student.class, studentId);
            Course course = session.get(Course.class, courseId);

            // Check if student or course is null
            if (student == null || course == null) {
                System.out.println("Student or course not found.");
                return updatedCourses; // or throw an exception, depending on your design
            }

            // Create a new student_Course entity
            student_Course studentCourse = new student_Course();
            studentCourse.setStudents(student);
            studentCourse.setCourse(course);

            // Save the student_Course entity
            session.save(studentCourse);

            // Update the set of courses for the student
            updatedCourses = student.getStudent_courses().stream()
                    .map(studentCourse1 -> studentCourse1.getCourse())
                    .collect(Collectors.toSet());

            commitSession();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        }

        return updatedCourses;
    }


    @Override
    public List<Course> seeCourses(Long studentId) {

        Student student = session.get(Student.class, studentId);
        return student.getStudent_courses().stream()
                .map(student_Course::getCourse)
                .toList();

    }

    @Override
    public List<Course> passesCourses(Long studentId) {

        Student student = session.get(Student.class, studentId);
        return student.getStudent_courses().stream()
                .filter(student_Course::isPass)
                .map(student_Course::getCourse)
                .toList();

    }

    @Override
    public List<Course> notPassesCourses(Long studentId) {

        Student student = session.get(Student.class, studentId);
        return student.getStudent_courses().stream()
                .filter(reportCard -> !reportCard.isPass())
                .map(student_Course::getCourse)
                .toList();

    }

    public Long getIdBasedOnNationalCodeAndCodeForStudent(String nationalCode, String studentCode) {

        String hql = "SELECT id FROM Student WHERE nationalCode =:nationalCode AND studentCode =:studentCode";
        org.hibernate.query.Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("nationalCode", nationalCode);
        query.setParameter("studentCode", studentCode);
        return query.uniqueResult();
    }

    public Student getExistedStudent(Long id) {
        return session.get(Student.class, id);
    }


    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public String getCodeName() {
        return "studentCode";
    }
}
