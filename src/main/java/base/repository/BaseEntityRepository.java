package base.repository;

import base.domain.BaseEntity;

import java.io.Serializable;

public interface BaseEntityRepository<T extends BaseEntity<ID>, ID extends Serializable> {

    T load(ID id);

    T saveOrUpdate(T t);

    void delete(ID id);

    boolean signIn(String nationalCode, String EntityCode);

    boolean exist(String nationalCode);

}
