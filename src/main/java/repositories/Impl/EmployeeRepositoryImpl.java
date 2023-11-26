package repositories.Impl;

import base.repository.Impl.BaseEntityRepositoryImpl;
import entity.Employee;
import entity.Student;
import entity.Teacher;
import org.hibernate.Session;
import repositories.EmployeeRepository;


public class EmployeeRepositoryImpl
        extends BaseEntityRepositoryImpl<Employee, Long>
        implements EmployeeRepository {

    Session session;

    public EmployeeRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void saveOrUpdateStudent(Student student) {
        try {
            session.beginTransaction().begin();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void deleteStudent(Student student) {
        try {
            session.beginTransaction().begin();
            Student student1 = session.find(Student.class, student.getId());
            session.delete(student1);
            session.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.beginTransaction().rollback();
        }
    }

    @Override
    public void saveOrUpdateTeacher(Teacher teacher) {
        try {
            session.beginTransaction().begin();
            session.saveOrUpdate(teacher);
            session.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.beginTransaction().rollback();
        }
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        try {
            session.beginTransaction().begin();
            Teacher teacher1 = session.find(Teacher.class, teacher.getId());
            session.delete(teacher1);
            session.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.beginTransaction().rollback();
        }
    }

    @Override
    public double seeSalary() {
        return 10000000.0;
    }

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    public String getCodeName() {
        return "employeeCode";
    }
}
