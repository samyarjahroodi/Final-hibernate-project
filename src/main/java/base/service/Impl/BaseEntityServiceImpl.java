package base.service.Impl;

import base.domain.BaseEntity;
import base.repository.BaseEntityRepository;
import base.service.BaseEntityService;
import org.hibernate.Session;
import utility.SessionFactoryProvider;

import java.io.Serializable;

public class BaseEntityServiceImpl<T extends BaseEntity<ID>, ID extends Serializable,
        R extends BaseEntityRepository<T, ID>>
        implements BaseEntityService<T, ID> {
    Session session = SessionFactoryProvider.getSessionFactory().openSession();

    protected final R baseEntityRepository;

    public BaseEntityServiceImpl(R baseEntityRepository) {
        this.baseEntityRepository = baseEntityRepository;
    }


    @Override
    public T load(ID id) {
        return baseEntityRepository.load(id);
    }

    @Override
    public T saveOrUpdate(T t) {
        return baseEntityRepository.saveOrUpdate(t);
    }

    @Override
    public void delete(ID id) {
        baseEntityRepository.delete(id);
    }

    @Override
    public boolean signIn(String nationalCode, String EntityCode) {
        return baseEntityRepository.signIn(nationalCode, nationalCode);
    }


    @Override
    public boolean exist(String nationalCode) {
        return baseEntityRepository.exist(nationalCode);
    }
}
