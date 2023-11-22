package base.service;

import base.domain.BaseEntity;

import java.io.Serializable;

public interface BaseEntityService<T extends BaseEntity<ID>,ID extends Serializable> {

    T load(ID id);

    T saveOrUpdate(T t);

    void delete(ID id);
}
