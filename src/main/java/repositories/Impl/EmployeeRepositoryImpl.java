package repositories.Impl;

import base.repository.Impl.BaseEntityRepositoryImpl;
import entity.Employee;
import entity.Student;
import entity.Teacher;
import org.hibernate.Session;
import repositories.EmployeeRepository;
import utility.SessionFactoryProvider;


public class EmployeeRepositoryImpl
        extends BaseEntityRepositoryImpl<Employee, Long>
        implements EmployeeRepository {
    Session session = SessionFactoryProvider.getSessionFactory().openSession();

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
            session.delete(student);
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
            session.delete(teacher);
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
}
