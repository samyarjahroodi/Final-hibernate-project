package services.Impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.Employee;
import entity.Student;
import entity.Teacher;
import repositories.EmployeeRepository;
import repositories.Impl.EmployeeRepositoryImpl;
import services.EmployeeService;

public class EmployeeServiceImpl
        extends BaseEntityServiceImpl<Employee, Long, EmployeeRepository>
        implements EmployeeService {

    private  EmployeeRepositoryImpl employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository baseEntityRepository) {
        super(baseEntityRepository);
    }


    @Override
    public void saveOrUpdateStudent(Student student) {
        employeeRepository.saveOrUpdateStudent(student);
    }

    @Override
    public void deleteStudent(Student student) {
        employeeRepository.deleteStudent(student);
    }

    @Override
    public void saveOrUpdateTeacher(Teacher teacher) {
        employeeRepository.saveOrUpdateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        employeeRepository.deleteTeacher(teacher);
    }

    @Override
    public double seeSalary() {
        return 10000000;
    }
}
