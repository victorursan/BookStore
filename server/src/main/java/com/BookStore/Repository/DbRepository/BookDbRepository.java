package com.BookStore.Repository.DbRepository;

import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Models.Book;
import com.BookStore.Repository.Exceptions.RepositoryException;
import com.BookStore.Repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class BookDbRepository implements IRepository<Book> {
    private IValidator<Book> validator;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BookDbRepository(JdbcTemplate jdbcTemplate, IValidator<Book> validator) {
        this.validator = validator;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Book entity) throws ValidatorException {
        if (entity == null) {
            throw new RepositoryException("Book entity must not be null");
        }
        validator.validate(entity);
        String sql = "INSERT INTO books (bookid, title, author, isbn, genre, publisher, available, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,entity.getId(), entity.getTitle(), entity.getAuthor(), entity.getISBN(), entity.getGenre(), entity.getPublisher(), entity.isAvailable(), entity.getPrice());
    }

    @Override
    public Optional<Book> get(int id) {
        String getBooks = "SELECT * from books where bookid=?";
        Book book = jdbcTemplate.queryForObject(getBooks, BeanPropertyRowMapper.newInstance(Book.class), id);
        return Optional.ofNullable(book);
    }


    @Override
    public Iterable<Book> getAll() {
        //language=PostgreSQL
        String sqlSelect = "SELECT * from books";
        return jdbcTemplate.queryForList(sqlSelect, Book.class);
    }

    @Override
    public Optional<Book> update(Book entity) throws ValidatorException {
        validator.validate(entity);
        String bookupdate = "UPDATE books SET title = ?, author = ?, isbn = ?, genre = ?, publisher = ?, available = ?, price = ? where bookid = ?";
        jdbcTemplate.update(bookupdate, entity.getTitle(), entity.getAuthor(), entity.getISBN(), entity.getGenre(), entity.getPublisher(), entity.isAvailable(), entity.getPrice(), entity.getId());
        return Optional.of(entity);
    }

    @Override
    public Optional<Book> delete(int id) {
        String sqlDelete = "DELETE FROM books where bookid=?";
        jdbcTemplate.update(sqlDelete, id);
            return Optional.empty();
    }


}
