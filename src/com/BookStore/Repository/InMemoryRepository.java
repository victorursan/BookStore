package com.BookStore.Repository;

import com.BookStore.Model.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<T> get(int id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public ArrayList<T> getAll() {
        return (ArrayList<T>) entities;
    }

    @Override
    public Optional<T> update(int id, T elem) {
        if (!entities.contains(elem)) return Optional.of(elem);
        entities.set(id, elem);
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(int id) {
       return Optional.ofNullable(entities.remove(id));
    }
}

