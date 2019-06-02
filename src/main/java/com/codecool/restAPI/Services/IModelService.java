package com.codecool.restAPI.Services;

import java.util.List;

public interface IModelService<T> {

    void persist(T entity);

    void update(T entity);

    T findById(Long id);

    void delete(Long id);

    List<T> findAll();

    void deleteAll();
}