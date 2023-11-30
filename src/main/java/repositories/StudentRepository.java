package repositories;

import base.repository.BaseEntityRepository;
import entity.Course;
import entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentRepository
        extends BaseEntityRepository<Student, Long> {

    Set<Course> addCourseToStudent(Long studentId, Long id);

    List<Course> seeCourses(Long studentId);

    List<Course> notPassesCourses(Long studentId);

    List<Course> passesCourses(Long studentId);

    double getAverageMarksForStudent(Long studentId);

    List<Course> passesCoursesWithMarks(Long studentId);

    Long getIdBasedOnNationalCodeAndCodeForStudent(String nationalCode, String studentCode);

    Student getExistedStudent(Long id);

}
