package com.BookStore.Repository;

import java.util.ArrayList;

public interface IRepository {
    void add(Object o);
    Object get(Integer id);
    ArrayList<Object> getall();
    void update(Integer id, Object o);
    void delete(Integer id);
}