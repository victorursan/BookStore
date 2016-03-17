package com.BookStore.Repository;

import com.BookStore.Model.BaseEntity;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryRepository<T extends BaseEntity<Integer>> implements IRepository<T> {
    private List<T> entities;
    private IValidator<T> validator;

    public InMemoryRepository() {
        this(entity -> {});
    }

    public InMemoryRepository(IValidator<T> validator) {
        this.entities = new ArrayList<>();
        this.validator = validator;
    }

    public InMemoryRepository(List<T> elem, IValidator<T> validator) {
        this.entities = elem;
        this.validator = validator;
    }

    @Override
    public void add(T elem) throws ValidatorException {
        validator.validate(elem);
        entities.add(elem);
    }

    @Override
    public Optional<T> get(int id) {
        return entities.stream().reduce((element, acc) -> {if (element.getId() == id) return element; return acc;});
    }

    @Override
    public List<T> getAll() {
        return entities;
    }

    @Override
    public Optional<T> update(T elem) throws ValidatorException {
        if (!entities.stream().anyMatch(e -> e.getId().equals(elem.getId()))) {
            return Optional.of(elem);
        }
        validator.validate(elem);
        entities = entities.stream().filter(e -> !e.getId().equals(elem.getId())).collect(Collectors.toList());
        entities.add(elem);
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(int id) {
        return Optional.ofNullable(entities.remove(id));
    }
}

