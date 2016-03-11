package com.BookStore.Model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

public class BaseEntity<ID> {
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
