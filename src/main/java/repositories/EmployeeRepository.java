package repositories;

import base.repository.BaseEntityRepository;
import entity.Employee;
import entity.Student;
import entity.Teacher;

public interface EmployeeRepository
        extends BaseEntityRepository<Employee, Long> {

    void saveOrUpdateStudent(Student student);

    void deleteStudent(Student student);

    void saveOrUpdateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    double seeSalary();

    boolean signIn(String nationalCode, String code);

    Long getIdBasedOnNationalCodeAndCodeForEmployee(String nationalCode, String employeeCode);

    Employee getExistedEmployee(Long id);

}
