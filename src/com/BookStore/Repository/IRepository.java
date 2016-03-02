package com.BookStore.Repository;

import java.util.ArrayList;

public interface IRepository<E> {
    void add(E elem);

    E get(int id);

    ArrayList<E> getAll();

    void update(int id, E elem);

    void delete(int id);
}