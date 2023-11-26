package repositories;

import base.repository.BaseEntityRepository;
import domain.Employee;
import domain.Student;
import domain.Teacher;

public interface EmployeeRepository
        extends BaseEntityRepository<Employee,Long> {

    void saveOrUpdateStudent(Student student);

    void deleteStudent(Student student);

    void saveOrUpdateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    double seeSalary();

}
