package repositories;

import base.repository.BaseEntityRepository;
import entity.Course;
import entity.Teacher;

public interface TeacherRepository
        extends BaseEntityRepository<Teacher, Long> {

    Teacher seeTeacherItems(Teacher teacher, Long ID);

    void giveMarkToStudents(Long id, Course course, int markValue);

    Integer getTotalUnitsForTeacher(Long teacherId);


}
