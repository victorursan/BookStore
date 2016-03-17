package com.BookStore.Repository;

import com.BookStore.Model.Book;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;

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

public class BookFileRepository extends InMemoryRepository<Book> {
    private Path bookFilePath;

    public BookFileRepository(IValidator<Book> validator, String file) {
        super(validator);
        this.bookFilePath = Paths.get(file);
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            Files.lines(bookFilePath).forEach(line -> {
                List<String> items = Arrays.asList(line.split(",")).stream().map(String::trim).collect(Collectors.toList());
                int id = Integer.parseInt(items.get(0));
                String title = items.get(1);
                String author = items.get((2));
                Long ISBN = Long.valueOf(items.get(3));
                String genre = items.get(4);
                String publisher = items.get((5));
                int price = Integer.parseInt(items.get(6));
                Boolean available = Boolean.parseBoolean(items.get(7));

                Book book = new Book(id, title, author, ISBN, genre, publisher, price, available);

                try {
                    super.add(book);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return super.delete(id);
    }

    private void saveToFile(Book entity) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(bookFilePath, StandardOpenOption.APPEND)) {
            bufferedWriter.write(entity.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rewriteToFile() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(bookFilePath, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Book element: super.getAll()) {
                bufferedWriter.write(element.toString());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
