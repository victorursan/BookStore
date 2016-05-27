package com.BookStore.core.repositories;

import com.BookStore.core.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;


@Getter
@Setter
public abstract class CustomRepositorySupport<T extends BaseEntity<ID>, ID extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> domainClass;

    public CustomRepositorySupport(Class<T> domainClass) {
        this.domainClass = domainClass;
    }


}
