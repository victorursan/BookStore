package com.BookStore.models;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Serializable {
    private ID id;

    public BaseEntity(ID id) {
        this.id = id;
    }

    @Getter
    public ID getId() {
        return id;
    }

    @Setter
    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{ id=" + id + '}';
    }
}
