package com.BookStore.Repository;

import com.BookStore.Model.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T extends BaseEntity<Integer>> implements IRepository<T> {

    private List<T> entities;

    public InMemoryRepository(List<T> entities) {
        this.entities = new ArrayList<>();
    }

    @Override
    public void add(T elem) {
        entities.add(elem);
    }

    @Override
    public T get(int id) {
        return entities.get(id);
    }

    @Override
    public ArrayList<T> getAll() {
        return (ArrayList<T>) entities;
    }

    @Override
    public void update(int id, T elem) {
        entities.set(id, elem);
    }

    @Override
    public void delete(int id) {
        entities.remove(id);
    }
}
