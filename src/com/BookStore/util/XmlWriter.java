package com.BookStore.util;

import com.BookStore.Model.BaseEntity;

import java.nio.file.Path;

public class XmlWriter<T extends BaseEntity<Integer>> {
    private Path filePath;

    public XmlWriter(Path filePath) {
        this.filePath = filePath;
    }

    public void save(T entity) {
       //TODO implement writer
    }


}
