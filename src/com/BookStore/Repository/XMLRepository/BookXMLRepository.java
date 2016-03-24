package com.BookStore.Repository.XMLRepository;

import com.BookStore.Model.Book;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.Exceptions.RepositoryException;
import com.BookStore.Repository.InMemoryRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by victor on 3/24/16.
 */
public class BookXMLRepository extends InMemoryRepository<Book> {
    private Path bookFilePath;

    public BookXMLRepository(IValidator<Book> validator, String file) {
        super(validator);
        this.bookFilePath = Paths.get(file);
        loadFromFile();
    }

    private void loadFromFile() {
//        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(bookFilePath, StandardOpenOption.TRUNCATE_EXISTING)) {
//
//        }
//        try {
//            Files.lines(bookFilePath).forEach(line -> {
//                List<String> items = Arrays.asList(line.split(", ")).stream().map(String::trim).collect(Collectors.toList());
//                int id = Integer.parseInt(items.get(0));
//                String title = items.get(1);
//                String author = items.get((2));
//                Long ISBN = Long.valueOf(items.get(3));
//                String genre = items.get(4);
//                String publisher = items.get((5));
//                int price = Integer.parseInt(items.get(6));
//                Boolean available = Boolean.parseBoolean(items.get(7));
//
//                Book book = new Book(id, title, author, ISBN, genre, publisher, price, available);
//                super.add(book);
//            });
//        } catch (IOException e) {
//            throw new RepositoryException(e.getMessage());
//        }
    }

    @Override
    public void add(Book entity) throws ValidatorException {
        super.add(entity);
        saveToFile(entity);
    }

    @Override
    public Optional<Book> get(int id) {
        return super.get(id);
    }

    @Override
    public List<Book> getAll() {
        return super.getAll();
    }

    @Override
    public Optional<Book> update(Book elem) throws ValidatorException {
        Optional<Book> book = super.update(elem);
        if (!book.isPresent()) {
            rewriteToFile();
        }
        return book;
    }

    @Override
    public Optional<Book> delete(int id) {
        Optional<Book> book = super.delete(id);
        book.ifPresent(t -> rewriteToFile());
        return book;
    }

    private void saveToFile(Book entity) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(bookFilePath, StandardOpenOption.APPEND)) {
            JAXBContext context = JAXBContext.newInstance(Book.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            // m.marshal(emp, System.out);

            // Write to File
            m.marshal(entity,bufferedWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

//    private static Employee jaxbXMLToObject() {
//        try {
//            JAXBContext context = JAXBContext.newInstance(Employee.class);
//            Unmarshaller un = context.createUnmarshaller();
//            Employee emp = (Employee) un.unmarshal(new File(FILE_NAME));
//            return emp;
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    private void rewriteToFile() {
//        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(bookFilePath, StandardOpenOption.TRUNCATE_EXISTING)) {
//            for (Book element : super.getAll()) {
//                bufferedWriter.write(element.toString());
//                bufferedWriter.newLine();
//            }
//        } catch (IOException e) {
//            throw new RepositoryException(e.getMessage());
//        }
    }
}
