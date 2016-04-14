package com.BookStore.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class XmlReader {
    private Path filePath;

    public XmlReader(Path filePath) {
        this.filePath = filePath;
    }

    private List loadEntitiesFromNode(Node node) {
        List entities = new ArrayList();
        try {
            NodeList nl = node.getChildNodes();

            for (int i = 0; i < nl.getLength(); i++) {
                Node parentNode = nl.item(i);
                if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList nl2 = parentNode.getChildNodes();

                    Class cls = Class.forName(parentNode.getNodeName());

                    HashMap<String, Object> valueMap = new HashMap<>();
                    HashMap<String, Class> typeMap = new HashMap<>();

                    Field[] fields = new Field[cls.getSuperclass().getDeclaredFields().length + cls.getDeclaredFields().length];
                    System.arraycopy(cls.getSuperclass().getDeclaredFields(), 0, fields, 0, cls.getSuperclass().getDeclaredFields().length);
                    System.arraycopy(cls.getDeclaredFields(), 0, fields, cls.getSuperclass().getDeclaredFields().length, cls.getDeclaredFields().length);
                    for (Field f : fields) {
                        valueMap.put(f.getName(), null);
                        typeMap.put(f.getName(), f.getType());
                    }

                    for (int i2 = 0; i2 < nl2.getLength(); i2++) {
                        Node childNode = nl2.item(i2);
                        if (valueMap.containsKey(childNode.getNodeName())) {
                            if (typeMap.get(childNode.getNodeName()) == Integer.class) {
                                valueMap.put(childNode.getNodeName(), Integer.valueOf(childNode.getTextContent()));
                            } else if (typeMap.get(childNode.getNodeName()) == Long.class) {
                                valueMap.put(childNode.getNodeName(), Long.valueOf(childNode.getTextContent()));
                            } else if (typeMap.get(childNode.getNodeName()) == String.class) {
                                valueMap.put(childNode.getNodeName(), childNode.getTextContent());
                            } else if (typeMap.get(childNode.getNodeName()) == Boolean.class) {
                                valueMap.put(childNode.getNodeName(), Boolean.parseBoolean(childNode.getTextContent()));
                            } else if (typeMap.get(childNode.getNodeName()) == List.class) {
                                valueMap.put(childNode.getNodeName(), loadEntitiesFromNode(childNode));
                            } else {
                                valueMap.put(childNode.getNodeName(), Integer.valueOf(childNode.getTextContent()));
                            }
                        }
                    }
                    Object instance = cls.newInstance();
                    for (Field f : fields) {
                        f.setAccessible(true);
                        f.set(instance, valueMap.get(f.getName()));
                    }
                    entities.add(instance);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return entities;
    }

    public Optional<List> loadEntities() {
        try {
            Document doc = XmlHelper.loadDocument(String.valueOf(filePath));
            Node node = doc.getFirstChild();
            return Optional.ofNullable(loadEntitiesFromNode(node));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
