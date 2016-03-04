package com.BookStore.Model;

/**
 * Created by victor on 3/4/16.
 */
public class BaseEntity<ID> {
    private ID id;

    public BaseEntity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{ id=" + id + '}';
    }
}
