package com.BookStore.util;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.BookStore.Model.BaseEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Loads all entities from an xml file containing entities of type T with id of type ID.
 *
 * @author radu.
 */
public class XmlReader<T extends BaseEntity<Integer>> {
    private Path filePath;

    public XmlReader(Path filePath) {
        this.filePath = filePath;
    }

    public List<T> loadEntities() {
        List<T> entities = new ArrayList<>();
        //TODO implement reader
        return entities;
    }


}
