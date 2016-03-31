package com.BookStore.util;

import com.BookStore.Model.BaseEntity;
import com.BookStore.Repository.Exceptions.RepositoryException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.util.List;

public class XmlWriter<T extends BaseEntity<Integer>> {
    private Path filePath;

    public XmlWriter(Path filePath) {
        this.filePath = filePath;
    }

    private DOMSource getDOMFromEntity(List<T> entity) throws ParserConfigurationException, IllegalAccessException {
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
        return new DOMSource(doc);
    }

    public void save(List<T> entity) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = getDOMFromEntity(entity);
            StreamResult result = new StreamResult(new File(String.valueOf(filePath)));
            transformer.transform(source, result);
        } catch (Throwable e) {
            throw new RepositoryException("Something went wrong whe writing to XML");
        }
    }
}
