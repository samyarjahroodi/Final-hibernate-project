package services;

import base.service.BaseEntityService;
import domain.Employee;
import domain.Student;
import domain.Teacher;

public interface EmployeeService
        extends BaseEntityService<Employee,Long> {

    void saveOrUpdateStudent(Student student);

    void deleteStudent(Student student);

    void saveOrUpdateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    double seeSalary();


}
