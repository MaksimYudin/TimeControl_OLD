package com.hibernate.crud.operations;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IDao<T, Id extends Serializable> {

    void persist(T entity);

    List<T> getAllData();

    void delete(T t);

    Optional<T> getDataById(Id id);

    void update(T t);

    void deleteAllRecords();

}
