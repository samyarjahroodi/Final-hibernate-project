package repositories.Impl;

import entity.Course;
import entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repositories.TeacherRepository;
import services.TeacherService;
import utility.SessionFactoryProvider;

import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository {
    Session session = SessionFactoryProvider.getSessionFactory().openSession();

    @Override
    public Teacher seeTeacherItems(Teacher teacher, Long id) {

        session.beginTransaction().begin();
        session.load(teacher, id);
        session.beginTransaction().commit();
        return null;
    }

    @Override
    public void giveMarkToStudents(Course course) {

    }

    @Override
    public double salary( boolean scienceCommittee,Long id) {
        Integer totalUnitsForTeacher = getTotalUnitsForTeacher(id);
        if (scienceCommittee) {
            return (totalUnitsForTeacher * 1000000) + 5000000;
        } else {
            return totalUnitsForTeacher * 1000000;
        }
    }

    public Integer getTotalUnitsForTeacher(Long teacherId) {
        try {
            session.beginTransaction();

            String hql = "SELECT SUM(c.unit) FROM Course c WHERE c.teacher.id = :teacherId";
            Query query = session.createQuery(hql);
            query.setParameter("teacherId", teacherId);
            Integer totalUnits = (Integer) query.uniqueResult();

            session.getTransaction().commit();

            return totalUnits != null ? totalUnits : 0;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return 0;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


}
