package repositories;

import base.repository.BaseEntityRepository;
import entity.Course;
import entity.Student;

import java.util.List;

public interface StudentRepository extends BaseEntityRepository<Student, Long> {

    void addCourseToStudent(Long studentId, Course course);

    List<Course> seeCourses(Long studentId);

    List<Course> notPassesCourses(Long studentId);

    List<Course> passesCourses(Long studentId);

    double getAverageMarksForStudent(Long studentId);

    List<Course> passesCoursesWithMarks(Long studentId);

}
