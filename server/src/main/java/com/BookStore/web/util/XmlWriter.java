package com.BookStore.web.util;

import com.BookStore.web.Models.BaseEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.List;

public class XmlWriter<T extends BaseEntity<Integer>> {
    private Path filePath;

    public XmlWriter(Path filePath) {
        this.filePath = filePath;
    }

    private Document getDocFromEntity(List<T> entity) throws ParserConfigurationException, IllegalAccessException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement;

        rootElement = doc.createElement("list");
        doc.appendChild(rootElement);
        for (Object mainObject : (java.util.List) entity) {
            Element element = doc.createElement(mainObject.getClass().getTypeName());

            Field[] fields = new Field[mainObject.getClass().getSuperclass().getDeclaredFields().length + mainObject.getClass().getDeclaredFields().length];
            System.arraycopy(mainObject.getClass().getSuperclass().getDeclaredFields(), 0, fields, 0, mainObject.getClass().getSuperclass().getDeclaredFields().length);
            System.arraycopy(mainObject.getClass().getDeclaredFields(), 0, fields, mainObject.getClass().getSuperclass().getDeclaredFields().length, mainObject.getClass().getDeclaredFields().length);

            for (Field field : fields) {
                field.setAccessible(true);
                Element newElem = doc.createElement(field.getName());
                if (field.getType() == java.util.List.class) {
                    Object value = field.get(mainObject);
                    for (Object obj : (java.util.List) value) {
                        Element grandchild = doc.createElement(String.valueOf(obj.getClass().getTypeName()));
                        Field[] cfields = new Field[obj.getClass().getSuperclass().getDeclaredFields().length + obj.getClass().getDeclaredFields().length];
                        System.arraycopy(obj.getClass().getSuperclass().getDeclaredFields(), 0, cfields, 0, obj.getClass().getSuperclass().getDeclaredFields().length);
                        System.arraycopy(obj.getClass().getDeclaredFields(), 0, cfields, obj.getClass().getSuperclass().getDeclaredFields().length, obj.getClass().getDeclaredFields().length);
                        for (Field cfield : cfields) {
                            cfield.setAccessible(true);
                            Element newChildElem = doc.createElement(cfield.getName());
                            newChildElem.appendChild(doc.createTextNode(String.valueOf(cfield.get(obj))));
                            grandchild.appendChild(newChildElem);
                        }
                        newElem.appendChild(grandchild);
                    }
                    element.appendChild(newElem);
                } else {
                    newElem.appendChild(doc.createTextNode(String.valueOf(field.get(mainObject))));
                    element.appendChild(newElem);
                }
                rootElement.appendChild(element);
            }

        }
        return doc;
    }

    public void save(List<T> entity) {
        try {
            XmlHelper.saveDocument(String.valueOf(filePath), getDocFromEntity(entity));
        } catch (ParserConfigurationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
