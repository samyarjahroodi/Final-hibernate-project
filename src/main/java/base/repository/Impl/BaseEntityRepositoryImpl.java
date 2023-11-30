package base.repository.Impl;

import base.domain.BaseEntity;
import base.repository.BaseEntityRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;

public abstract class BaseEntityRepositoryImpl
        <T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseEntityRepository<T, ID> {

    protected final Session session;

    protected BaseEntityRepositoryImpl(Session session) {
        this.session = session;
    }


    @Override
    public T load(ID id) {
        T loadedEntity = null;
        loadedEntity = session.load(getEntityClass(), id);
        return loadedEntity;
    }

    @Override
    public T saveOrUpdate(T t) {
        try {
            openSession();
            session.saveOrUpdate(t);
            commitSession();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        }
        return t;
    }

    @Override
    public void delete(ID id) {
        try {
            openSession();
            T t = session.get(getEntityClass(), id);
            session.remove(t);
            commitSession();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
        }
    }

    @Override
    public boolean exist(String nationalCode) {
        try {
            openSession();
            String hql = "SELECT COUNT(t) FROM " + getEntityClass().getName() + " t WHERE t.nationalCode = :nationalCode";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("nationalCode", nationalCode);
            Long count = query.uniqueResult();
            commitSession(); // Move this line into the try block
            return count != null && count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            rollBack();
            return false;
        } finally {
            closeSession();  // Ensure that you close the session in the finally block
        }
    }

    @Override
    public boolean signIn(String nationalCode, String code) {
        try {
            openSession();
            String hql = "SELECT COUNT(e) FROM " + getEntityClass().getSimpleName() + " e WHERE e.nationalCode = :nationalCode AND e." + getCodeName() + " = :code";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("nationalCode", nationalCode);
            query.setParameter("code", code);
            Long count = query.uniqueResult();
            commitSession();  // Move this line into the try block
            return count != null && count == 1;
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception or handle it appropriately
            rollBack();  // Rollback the transaction in case of an exception
            return false;
        }
    }



    public Long getIdBasedOnNationalCodeAndCodeForTeacher(String nationalCode, String teacherCode) {

        String hql = "SELECT id FROM Teacher WHERE nationalCode = :nationalCode AND teacherCode = :teacherCode";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("nationalCode", nationalCode);
        query.setParameter("teacherCode", teacherCode);
        return query.uniqueResult();
    }

    @Override
    public void openSession() {
        if (!session.getTransaction().isActive())
            session.getTransaction().begin();
    }

    @Override
    public void commitSession() {
        if (session.getTransaction().isActive())
            session.getTransaction().commit();
    }

    @Override
    public void rollBack() {
        if (session.getTransaction().isActive())
            session.getTransaction().rollback();

    }
@Override
    public void closeSession() {
        try {
            if (session != null && session.isOpen()) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Class<T> getEntityClass();

    public abstract String getCodeName();

}
