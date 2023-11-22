package repositories;

import base.repository.BaseEntityRepository;
import entity.Course;
import entity.Teacher;
import org.hibernate.Session;

public interface TeacherRepository  {

    Teacher seeTeacherItems(Teacher teacher,Long ID);

    void giveMarkToStudents(Course course);

    double salary(boolean scienceCommittee,Long id);


}
