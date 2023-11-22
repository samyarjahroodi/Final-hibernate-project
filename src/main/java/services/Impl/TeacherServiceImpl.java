package services.Impl;

import entity.Course;
import entity.Teacher;
import repositories.Impl.TeacherRepositoryImpl;
import services.TeacherService;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepositoryImpl teacherRepository;

    public TeacherServiceImpl(TeacherRepositoryImpl teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher seeTeacherItems(Teacher teacher, Long id) {
        return teacherRepository.seeTeacherItems(teacher,id);
    }

    @Override
    public void giveMarkToStudents(Course course) {
         teacherRepository.giveMarkToStudents(course);
    }

    @Override
    public double salary(int unit, boolean scienceCommittee) {
        return 0;
    }
}
