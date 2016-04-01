package com.BookStore.util;

import com.BookStore.Model.BaseEntity;
import com.BookStore.Repository.Exceptions.RepositoryException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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

            for (Field field : mainObject.getClass().getSuperclass().getDeclaredFields()) {
                field.setAccessible(true);
                Element newElem = doc.createElement(field.getName());
                newElem.appendChild(doc.createTextNode(String.valueOf(field.get(mainObject))));
                System.out.print("");
                element.appendChild(newElem);
            }

            for (Field field : mainObject.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Element newElem = doc.createElement(field.getName());
                if (field.getType() == java.util.List.class) {
                    Object value = field.get(mainObject);
                    for (Object obj: (java.util.List) value) {
                        Element grandchild = doc.createElement(String.valueOf(obj.getClass().getTypeName()));
                        for (Field childField : obj.getClass().getSuperclass().getDeclaredFields()) {
                            childField.setAccessible(true);
                            Element newChildElem = doc.createElement(childField.getName());
                            newChildElem.appendChild(doc.createTextNode(String.valueOf(childField.get(obj))));
                            grandchild.appendChild(newChildElem);
                        }
                        for (Field childField : obj.getClass().getDeclaredFields()) {
                            childField.setAccessible(true);
                            Element newChildElem = doc.createElement(childField.getName());
                            newChildElem.appendChild(doc.createTextNode(String.valueOf(childField.get(obj))));
                            grandchild.appendChild(newChildElem);
                        }
                        newElem.appendChild(grandchild);
                    }
                    element.appendChild(newElem);
                } else {
                    newElem.appendChild(doc.createTextNode(String.valueOf(field.get(mainObject))));
                    element.appendChild(newElem);
                }
            }
            rootElement.appendChild(element);
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
