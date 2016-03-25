package com.BookStore.Repository.XMLRepository;

import com.BookStore.Model.Book;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.Exceptions.RepositoryException;
import com.BookStore.Repository.InMemoryRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

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
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(InMemoryRepository.class);
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            InMemoryRepository<Book> newRepo = (InMemoryRepository<Book>) jaxbUnMarshaller.unmarshal(Files.newInputStream(bookFilePath));
            super.setEntities(newRepo.getAll());
        } catch (JAXBException | IOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(Book entity) throws ValidatorException {
        super.add(entity);
        rewriteToFile();
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


    private void rewriteToFile() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(bookFilePath, StandardOpenOption.TRUNCATE_EXISTING)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(InMemoryRepository.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, bufferedWriter);
        } catch (JAXBException | IOException e) {
            throw new RepositoryException(e);
        }
    }
}
