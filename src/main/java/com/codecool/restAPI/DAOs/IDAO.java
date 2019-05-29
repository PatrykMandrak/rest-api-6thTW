package com.codecool.restAPI.DAOs;

import java.util.List;

public interface IDAO<E> {

    public void create(E e);

    public E getById(E e);

    public E getByname(String name);

    public List<E> getAll();

    public void Update(E e, int id);

    public void delete(E e);
}