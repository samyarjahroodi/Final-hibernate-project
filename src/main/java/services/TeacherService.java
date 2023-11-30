package services;

import base.service.BaseEntityService;
import entity.Course;
import entity.Student;
import entity.Teacher;

import java.util.List;
import java.util.Set;

public interface TeacherService
        extends BaseEntityService<Teacher, Long> {

    void giveMarkToStudents(Long id, Course course, int markValue);

    double salary(Long teacherId, boolean scienceCommittee);

    Integer getTotalUnitsForTeacher(Long teacherId);

    Set<Course> teacherCourse(Long id);

    List<Student> seeStudentOfCourse(Long id, Long courseId);
    Long getIdBasedOnNationalCodeAndCodeForTeacher(String nationalCode, String teacherCode);

    Teacher getExistedTeacher(Long id);

    Boolean scienceCommittee(Long id);

}
