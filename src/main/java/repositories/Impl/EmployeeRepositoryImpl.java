package repositories.Impl;

import base.repository.Impl.BaseEntityRepositoryImpl;
import entity.Employee;
import entity.Student;
import entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import repositories.EmployeeRepository;


public class EmployeeRepositoryImpl
        extends BaseEntityRepositoryImpl<Employee, Long>
        implements EmployeeRepository {

    public EmployeeRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public void saveOrUpdateStudent(Student student) {
        try {
            openSession();
            session.saveOrUpdate(student);
            commitSession();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        } finally {
            closeSession();
        }
    }

    @Override
    public void deleteStudent(Student student) {
        try {
            openSession();
            Student student1 = session.find(Student.class, student.getId());
            session.delete(student1);
            commitSession();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        }
    }

    @Override
    public void saveOrUpdateTeacher(Teacher teacher) {
        try {
            openSession();
            session.saveOrUpdate(teacher);
            commitSession();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        }
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        try {
            openSession();
            Teacher teacher1 = session.find(Teacher.class, teacher.getId());
            session.delete(teacher1);
            commitSession();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        }
    }


    @Override
    public Long getIdBasedOnNationalCodeAndCodeForEmployee(String nationalCode, String employeeCode) {
        String hql = "SELECT id FROM Employee WHERE nationalCode =:nationalCode AND employeeCode =:employeeCode";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("nationalCode", nationalCode);
        query.setParameter("employeeCode", employeeCode);
        Long id = query.uniqueResult();
        return id;
    }

    public Employee getExistedEmployee(Long id) {
        return session.get(Employee.class, id);
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

