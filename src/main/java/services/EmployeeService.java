package services;

import base.service.BaseEntityService;
import entity.Employee;
import entity.Student;
import entity.Teacher;

public interface EmployeeService
        extends BaseEntityService<Employee, Long> {

    void saveOrUpdateStudent(Student student);

    void deleteStudent(Student student);

    void saveOrUpdateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    double seeSalary();

    boolean signIn(String nationalCode, String code);

    Long getIdBasedOnNationalCodeAndCodeForEmployee(String nationalCode, String employeeCode);

    Employee getExistedEmployee(Long id);
}
