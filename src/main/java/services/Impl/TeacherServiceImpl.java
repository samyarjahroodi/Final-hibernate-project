package services.Impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.Course;
import entity.Student;
import entity.Teacher;
import repositories.Impl.TeacherRepositoryImpl;
import repositories.TeacherRepository;
import services.TeacherService;

import java.util.List;
import java.util.Set;

public class TeacherServiceImpl
        extends BaseEntityServiceImpl<Teacher, Long, TeacherRepository>
        implements TeacherService {
    private TeacherRepositoryImpl teacherRepository;

    public TeacherServiceImpl(TeacherRepository baseEntityRepository) {
        super(baseEntityRepository);
    }


    @Override
    public Set<Course> teacherCourse(Long id) {
        return teacherRepository.teacherCourse(id);
    }

    @Override
    public Long getIdBasedOnNationalCodeAndCodeForTeacher(String nationalCode, String teacherCode) {
        return teacherRepository.getIdBasedOnNationalCodeAndCodeForTeacher(nationalCode, teacherCode);
    }

    @Override
    public Teacher getExistedTeacher(Long id) {
        return teacherRepository.getExistedTeacher(id);
    }

    @Override
    public Boolean scienceCommittee(Long id) {
        return null;
    }

    @Override
    public List<Student> seeStudentOfCourse(Long id, Long courseId) {
        return teacherRepository.seeStudentOfCourse(id, courseId);
    }

    @Override
    public void giveMarkToStudents(Long id, Course course, int markValue) {
        if (markValue < 0 || markValue > 20) {
            System.out.println("Your input must be between 0 and 20");
        } else {
            teacherRepository.giveMarkToStudents(id, course, markValue);
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
