package services.Impl;

import base.service.Impl.BaseEntityServiceImpl;
import domain.Course;
import domain.Teacher;
import repositories.Impl.TeacherRepositoryImpl;
import repositories.TeacherRepository;
import services.TeacherService;

public class TeacherServiceImpl
        extends BaseEntityServiceImpl<Teacher, Long, TeacherRepository>
        implements TeacherService {
    private TeacherRepositoryImpl teacherRepository;

    public TeacherServiceImpl(TeacherRepository baseEntityRepository) {
        super(baseEntityRepository);
    }

    @Override
    public Teacher seeTeacherItems(Teacher teacher, Long id) {
        return teacherRepository.seeTeacherItems(teacher, id);
    }

    @Override
    public void giveMarkToStudents(Course course, int markValue) {
        if (markValue < 0 || markValue > 20) {
            System.out.println("Your input must be between 0 and 20");
        } else {
            teacherRepository.giveMarkToStudents(course,markValue);
        }
    }

    @Override
    public double salary(Long teacherId, boolean scienceCommittee) {
        Integer totalUnitsForTeacher = getTotalUnitsForTeacher(teacherId);
        if (scienceCommittee) {
            return totalUnitsForTeacher * 1000000 + 5000000;
        } else {
            return totalUnitsForTeacher * 1000000;
        }
    }

    @Override
    public Integer getTotalUnitsForTeacher(Long teacherId) {
        return teacherRepository.getTotalUnitsForTeacher(teacherId);
    }
}
