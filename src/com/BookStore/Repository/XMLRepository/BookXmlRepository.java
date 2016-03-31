package com.BookStore.Repository.XMLRepository;

import com.BookStore.Model.Book;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.InMemoryRepository;
import com.BookStore.util.XmlReader;
import com.BookStore.util.XmlWriter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    public void add(Book entity) throws ValidatorException {
        super.add(entity);
        new XmlWriter<Book>(bookFilePath).save(entity);
    }
}
