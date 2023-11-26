package repositories;

import base.repository.BaseEntityRepository;
import domain.Course;
import domain.Teacher;

public interface TeacherRepository
        extends BaseEntityRepository<Teacher, Long> {

    Teacher seeTeacherItems(Teacher teacher, Long ID);

    void giveMarkToStudents(Course course, int markValue);

    Integer getTotalUnitsForTeacher(Long teacherId);

}
