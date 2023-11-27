package services;

import base.service.BaseEntityService;
import entity.Course;
import entity.Teacher;

public interface TeacherService
        extends BaseEntityService<Teacher, Long> {
    Teacher seeTeacherItems(Teacher teacher, Long ID);

    void giveMarkToStudents(Long id, Course course, int markValue);

    double salary(Long teacherId, boolean scienceCommittee);

    Integer getTotalUnitsForTeacher(Long teacherId);
}
