package com.BookStore.web.Repository.DbRepository;

import com.BookStore.web.Model.Validators.IValidator;
import com.BookStore.web.Model.Validators.ValidatorException;
import com.BookStore.web.Models.Book;
import com.BookStore.web.Repository.Exceptions.RepositoryException;
import com.BookStore.web.Repository.IRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;


public class BookDbRepository implements IRepository<Book> {
    private IValidator<Book> validator;

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
        jdbcTemplate.update(sql, entity.getId(), entity.getTitle(), entity.getAuthor(), entity.getISBN(), entity.getGenre(), entity.getPublisher(), entity.isAvailable(), entity.getPrice());
    }

    @Override
    public Optional<Book> get(int id) {
        String getBooks = "SELECT * FROM books WHERE bookid = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(getBooks, new BookRowMapper(), id));
    }


    @Override
    public Iterable<Book> getAll() {
        //language=PostgreSQL
        String sqlSelect = "SELECT * FROM books";
        return jdbcTemplate.query(sqlSelect, new BookRowMapper());
    }

    @Override
    public Optional<Book> update(Book entity) throws ValidatorException {
        validator.validate(entity);
        String bookupdate = "UPDATE books SET title = ?, author = ?, isbn = ?, genre = ?, publisher = ?, available = ?, price = ? WHERE bookid = ?";
        jdbcTemplate.update(bookupdate, entity.getTitle(), entity.getAuthor(), entity.getISBN(), entity.getGenre(), entity.getPublisher(), entity.isAvailable(), entity.getPrice(), entity.getId());
        return Optional.of(entity);
    }

    @Override
    public Optional<Book> delete(int id) {
        String sqlDelete = "DELETE FROM books WHERE bookid=?";
        jdbcTemplate.update(sqlDelete, id);
        return Optional.empty();
    }

}
