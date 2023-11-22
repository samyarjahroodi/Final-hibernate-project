package services;

import entity.Course;
import entity.Student;

import java.util.List;

public interface StudentService {
    void seeStudentItems(Student student, Long id);

    List<Course> seeCourses(Long studentId);

    Course seeCoursesInThePreviousTerm(Long studentId,int term);

    double getAverageMarksForStudent(Long studentId);
}
