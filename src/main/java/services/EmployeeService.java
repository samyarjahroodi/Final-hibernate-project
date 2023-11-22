package services;

import entity.Employee;
import entity.Student;
import entity.Teacher;

public interface EmployeeService {

    void saveStudent(Student student);

    void deleteStudent(String StudentCode);

    void updateStudent(Student student, Long id);

    void saveTeacher(Teacher teacher);

    void deleteTeacher(String teacherCode);

    void updateTeacher(Teacher teacher, Long id);

    void saveEmployee(Employee employee);

    void deleteEmployee(String employeeCode);

    void updateEmployee(Employee employee, Long id);

    double seeSalary();
}
