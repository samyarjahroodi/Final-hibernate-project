package services;

import base.service.BaseEntityService;
import entity.Course;
import entity.Student;
import entity.student_Course;

import java.util.List;
import java.util.Set;

public interface StudentService
        extends BaseEntityService<Student,Long> {

    Set<student_Course> addCourseToStudent(Long studentId, Long id);

    List<Course> seeCourses(Long studentId);

    List<Course> notPassesCourses(Long studentId);

    double getAverageMarksForStudent(Long studentId);

    List<Course> passesCoursesWithMarks(Long studentId);

    Long getIdBasedOnNationalCodeAndCodeForStudent(String nationalCode, String studentCode);


    Student getExistedStudent(Long id);
}
