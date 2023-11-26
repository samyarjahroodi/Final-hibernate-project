package container;

import org.hibernate.Session;
import org.hibernate.query.Query;
import utility.SessionFactoryProvider;

public class Container {
    static Session session =
            SessionFactoryProvider.getSessionFactory().openSession();

    public static Long getIdBasedOnNationalCodeAndCodeForTeacher(String nationalCode, String teacherCode) {
        try {
            session.beginTransaction().begin();
            String hql = "SELECT id FROM Teacher WHERE nationalCode = :nationalCode AND teacherCode = :teacherCode";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("nationalCode", nationalCode);
            query.setParameter("teacherCode", teacherCode);
            Long id = query.uniqueResult();
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    public static Boolean getScienceCommitteeById(Long teacherId) {
        try {
            session.beginTransaction().begin();
            String hql = "SELECT scienceCommittee FROM Teacher WHERE id = :teacherId";
            Query<Boolean> query = session.createQuery(hql, Boolean.class);
            query.setParameter("teacherId", teacherId);
            Boolean scienceCommittee = query.uniqueResult();
            session.getTransaction().commit();
            return scienceCommittee;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

}
