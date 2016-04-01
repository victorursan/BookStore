package com.BookStore.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.BookStore.Model.BaseEntity;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sun.rmi.rmic.iiop.ClassType;
import sun.rmi.rmic.iiop.InterfaceType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class XmlReader<T extends BaseEntity<Integer>> {
    private Path filePath;

    public XmlReader(Path filePath) {
        this.filePath = filePath;
    }

    public List<T> loadEntities() {
        List<T> entities = new ArrayList<>();
//        Document document = XmlHelper.loadDocument(String.valueOf(filePath));

        try {
            File inputFile = new File(String.valueOf(filePath));
            DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            Node n = doc.getFirstChild();
            NodeList nl = n.getChildNodes();
            Node an,an2;

            for (int i=0; i < nl.getLength(); i++) {
                an = nl.item(i);
                if(an.getNodeType()==Node.ELEMENT_NODE) {
                    NodeList nl2 = an.getChildNodes();

                    Class cls = Class.forName(an.getNodeName());
                    HashMap<String, String> valueMap = new HashMap<>();

                    for (Field f : cls.getDeclaredFields()) {
                        valueMap.put(f.getName(), "null");
                    }

                    for (Field f : cls.getSuperclass().getDeclaredFields()) {
                        valueMap.put(f.getName(), "null");
                    }

                    for(int i2=0; i2<nl2.getLength(); i2++) {
                        an2 = nl2.item(i2);
                        if(valueMap.containsKey(an2.getNodeName())) {
                            valueMap.put(an2.getNodeName(), an2.getTextContent());
                        }

                    }

                    Object instance = cls.newInstance();
                    for (Field f : cls.getDeclaredFields() ) {
                        f.setAccessible(true);
                        String value = valueMap.get(f.getName());
                        Object newValue = (Object) value;
                        System.out.print("");
                        if (f.getType() == Integer.class) {
                            newValue = Integer.valueOf(value);
                        } else
                        if (f.getType() == Long.class) {
                            newValue = Long.valueOf(value);
                        } else
                        if (f.getType() == String.class) {
                            newValue = value;
                        } else
                        if (f.getType() == Boolean.class) {
                            newValue = Boolean.parseBoolean(value);
                        }
                        f.set(instance, newValue);
                    }

                    for (Field f : cls.getSuperclass().getDeclaredFields()) {
                        f.setAccessible(true);
                        String value = valueMap.get(f.getName());
                        Object newValue = (Object) value;
                        if (f.getType() == Integer.class) {
                            newValue = Integer.valueOf(value);
                        } else
                        if (f.getType() == Long.class) {
                            newValue = Long.valueOf(value);
                        } else
                        if (f.getType() == String.class) {
                            System.out.print("");
                            newValue = value;
                        } else
                        if (f.getType() == Boolean.class) {
                            newValue = Boolean.parseBoolean(value);
                        }
                        f.set(instance, newValue);
                    }
                    entities.add((T) instance);
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

//        NodeList nodelist = parentNode.getChildNodes();
////        NodeList nList = list.getChildNodes();
//        for (int temp = 0; temp < nList.getLength(); temp++) {
//            Node nNode = nList.item(temp);
//            for (int i = 0; temp < nNode.getAttributes().getLength(); temp++) {
//                Node cNode = nList.item(temp);
//                System.out.println("\nCurrent Element :" + cNode.getNodeName());
//            }
////            System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//        }
//        DocumentBuilder builder = factory.newDocumentBuilder();
        return entities;
    }


}
