package services.Impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.Employee;
import entity.Student;
import entity.Teacher;
import org.hibernate.Session;
import repositories.EmployeeRepository;
import repositories.Impl.EmployeeRepositoryImpl;
import services.EmployeeService;
import utility.SessionFactoryProvider;

public class EmployeeServiceImpl
        extends BaseEntityServiceImpl<Employee, Long, EmployeeRepository>
        implements EmployeeService {
    Session session = SessionFactoryProvider.getSessionFactory().openSession();
    private final EmployeeRepositoryImpl employeeRepository;  // If needed

    public EmployeeServiceImpl(EmployeeRepository baseEntityRepository) {
        super(baseEntityRepository);
        this.employeeRepository = new EmployeeRepositoryImpl(session);
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

    @Override
    public Long getIdBasedOnNationalCodeAndCodeForEmployee(String nationalCode, String employeeCode) {
        return employeeRepository.getIdBasedOnNationalCodeAndCodeForEmployee(nationalCode, employeeCode);
    }

    @Override
    public Employee getExistedEmployee(Long id) {
        return employeeRepository.getExistedEmployee(id);
    }

    @Override
    public boolean signIn(String nationalCode, String code) {
        return employeeRepository.signIn(nationalCode, code);
    }
}
