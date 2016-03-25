package com.BookStore.Repository.XMLRepository;

import com.BookStore.Model.BaseEntity;
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

public class XMLRepository<T extends BaseEntity<Integer>> extends InMemoryRepository<T> {
    private Path filePath;

    public XMLRepository(IValidator<T> validator, String file) {
        super(validator);
        this.filePath = Paths.get(file);
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(InMemoryRepository.class);
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();

            InMemoryRepository<T> newRepo = (InMemoryRepository<T>) jaxbUnMarshaller.unmarshal(Files.newInputStream(filePath));
            super.setEntities(newRepo.getAll());
        } catch (JAXBException | IOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(T entity) throws ValidatorException {
        super.add(entity);
        rewriteToFile();
    }

    @Override
    public Optional<T> get(int id) {
        return super.get(id);
    }

    @Override
    public List<T> getAll() {
        return super.getAll();
    }

    @Override
    public Optional<T> update(T elem) throws ValidatorException {
        Optional<T> book = super.update(elem);
        if (!book.isPresent()) {
            rewriteToFile();
        }
        return book;
    }

    @Override
    public Optional<T> delete(int id) {
        Optional<T> book = super.delete(id);
        book.ifPresent(t -> rewriteToFile());
        return book;
    }

    private void rewriteToFile() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(InMemoryRepository.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, bufferedWriter);
        } catch (JAXBException | IOException e) {
            throw new RepositoryException(e);
        }
    }
}
