package repositories;

import base.repository.BaseEntityRepository;
import entity.Course;
import entity.Student;
import entity.Teacher;

import java.util.List;
import java.util.Set;

public interface TeacherRepository
        extends BaseEntityRepository<Teacher, Long> {


    void giveMarkToStudents(Long id, Course course, int markValue);

    Integer getTotalUnitsForTeacher(Long teacherId);

    Set<Course> teacherCourse(Long id);

    List<Long> teacherCoursesBasedOnId(Long id);

    List<Student> seeStudentOfCourse(Long id, Long courseId);

    Long getIdBasedOnNationalCodeAndCodeForTeacher(String nationalCode, String teacherCode);

    Teacher getExistedTeacher(Long id);

    Boolean scienceCommittee(Long id);

}
