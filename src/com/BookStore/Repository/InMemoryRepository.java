package com.BookStore.Repository;

import com.BookStore.Model.BaseEntity;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@XmlRootElement(name = "Repository")
public class InMemoryRepository<T extends BaseEntity<Integer>> implements IRepository<T> {
    private List<T> entities;
    private IValidator<T> validator;

    public InMemoryRepository() {
        this(entity -> {
        });
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
        return entities.stream().reduce((element, acc) -> {
            if (element.getId() == id) return element;
            return acc;
        });
    }

    @XmlElementWrapper(name = "entities")
    @XmlElementRef()
    @Override
    public List<T> getAll() {
        return entities;
    }

    @Setter
    protected void setEntities(List<T> entities) {
        this.entities = entities;
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
        Optional<T> element = get(id);
        element.ifPresent(entities::remove);
        return element;
    }
}

