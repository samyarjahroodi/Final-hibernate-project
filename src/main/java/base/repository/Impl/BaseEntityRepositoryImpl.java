package base.repository.Impl;

import base.domain.BaseEntity;
import base.repository.BaseEntityRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utility.SessionFactoryProvider;

import java.io.Serializable;

public abstract class BaseEntityRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseEntityRepository<T, ID> {
    private final Session session;

    public BaseEntityRepositoryImpl() {
        this.session = SessionFactoryProvider.getSessionFactory().openSession();
    }

    @Override
    public T load(ID id) {
        T loadedEntity = null;
        try {
            session.beginTransaction();
            loadedEntity = session.load(getEntityClass(), id);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return loadedEntity;
    }


    @Override
    public T saveOrUpdate(T t) {
        try {
            session.beginTransaction().begin();
            session.saveOrUpdate(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.beginTransaction().rollback();
        }
        return t;
    }

    @Override
    public void delete(ID id) {
        try {
            session.beginTransaction().begin();
            T t = session.get(getEntityClass(), id);
            session.remove(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public boolean signIn(String nationalCode, String code) {
        try {
            session.getTransaction().begin();
            String hql = "SELECT COUNT(t) FROM " + getEntityClass().getSimpleName() +
                    " t WHERE t.nationalCode = :nationalCode AND t." + getCodeName() + " = :code";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("nationalCode", nationalCode);
            query.setParameter("code", code);
            Long count = query.uniqueResult();
            session.getTransaction().commit();
            return count != null && count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    public abstract Class<T> getEntityClass();

    public abstract String getCodeName();
}
