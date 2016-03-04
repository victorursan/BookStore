package com.BookStore.Repository;

import java.util.ArrayList;

public interface IRepository<T> {
    void add(T elem);

    T get(int id);

    ArrayList<T> getAll();

    void update(int id, T elem);

    void delete(int id);
}