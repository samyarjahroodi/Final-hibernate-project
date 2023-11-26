package services;

import base.service.BaseEntityService;
import domain.Course;
import domain.Student;

import java.util.List;

public interface StudentService
        extends BaseEntityService<Student,Long> {

    void addCourseToStudent(Long studentId, Course course);

    List<Course> seeCourses(Long studentId);

    List<Course> notPassesCourses(Long studentId);

    double getAverageMarksForStudent(Long studentId);

    List<Course> passesCoursesWithMarks(Long studentId);
}
