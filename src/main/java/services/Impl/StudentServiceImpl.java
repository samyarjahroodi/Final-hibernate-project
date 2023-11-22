package services.Impl;

import entity.Course;
import entity.Student;
import services.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Override
    public void seeStudentItems(Student student, Long id) {

    }

    @Override
    public List<Course> seeCourses(Long studentId) {
        return null;
    }

    @Override
    public Course seeCoursesInThePreviousTerm(Long studentId, int term) {
        return null;
    }

    @Override
    public double getAverageMarksForStudent(Long studentId) {
        return 0;
    }
}
