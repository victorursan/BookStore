package com.BookStore.Repository.XMLRepository;

import com.BookStore.Model.Book;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.Exceptions.RepositoryException;
import com.BookStore.Repository.InMemoryRepository;
import com.BookStore.util.XmlReader;
import com.BookStore.util.XmlWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

/**
 * Created by victor on 3/31/16.
 */
public class BookXmlRepository extends InMemoryRepository<Book> {
    private Path bookFilePath;

    public BookXmlRepository(IValidator<Book> validator, String fileName) {
        super(validator);
        bookFilePath = Paths.get(fileName);
        loadData();
    }

    private void rewriteToFile() {
        try {
            new XmlWriter<Book>(bookFilePath).save(super.getAll());
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private void loadData() {
        List<Book> books = new XmlReader<Book>(bookFilePath).loadEntities();
        for (Book book : books) {
            try {
                super.add(book);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
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
    public void add(Book entity) throws ValidatorException {
        super.add(entity);
        rewriteToFile();
    }

    @Override
    public Optional<Book> update(Book elem) throws ValidatorException {
        Optional<Book> client = super.update(elem);
        if (!client.isPresent()) {
            rewriteToFile();
        }
        return super.update(elem);
    }

    @Override
    public Optional<Book> delete(int id) {
        Optional<Book> client = super.delete(id);
        client.ifPresent(t -> rewriteToFile());
        return client;
    }
}
