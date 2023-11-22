package services;

import entity.Course;
import entity.Teacher;

public interface TeacherService {
    Teacher seeTeacherItems(Teacher teacher, Long ID);

    void giveMarkToStudents(Course course);

    double salary(int unit, boolean scienceCommittee);
}
