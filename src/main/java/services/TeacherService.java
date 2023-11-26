package services;

import base.service.BaseEntityService;
import domain.Course;
import domain.Teacher;

public interface TeacherService
        extends BaseEntityService<Teacher, Long> {
    Teacher seeTeacherItems(Teacher teacher, Long ID);

    void giveMarkToStudents(Course course, int markValue);

    double salary(Long teacherId, boolean scienceCommittee);

    Integer getTotalUnitsForTeacher(Long teacherId);
}
