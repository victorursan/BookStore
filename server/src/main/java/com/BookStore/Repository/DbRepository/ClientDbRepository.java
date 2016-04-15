package com.BookStore.Repository.DbRepository;

import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Models.Book;
import com.BookStore.Models.Client;
import com.BookStore.Repository.Exceptions.RepositoryException;
import com.BookStore.Repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDbRepository implements IRepository<Client> {
    private IValidator<Client> validator;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClientDbRepository(JdbcTemplate jdbcTemplate, IValidator<Client> validator) {
        this.validator = validator;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Client entity) throws ValidatorException {
        if (entity == null) {
            throw new RepositoryException("Book entity must not be null");
        }
        validator.validate(entity);
        //language=PostgreSQL
        String sql = "INSERT INTO clients (clientid, firstname, lastname) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, entity.getId(), entity.getFirstName(), entity.getLastName());

        if (entity.getBooks().size() > 0) {
            for (Book item : entity.getBooks()) {
                addPurchase(entity.getId(), item.getId());
            }
        }
    }

    @Override
    public Optional<Client> get(int id) {
        //language=PostgreSQL
        String sqlSelect = "SELECT * FROM clients WHERE clientid=?";
        Client client = jdbcTemplate.queryForObject(sqlSelect, BeanPropertyRowMapper.newInstance(Client.class), id);
        if (client == null) return Optional.empty();
        //language=PostgreSQL
        String sqlBooks = "SELECT * FROM books INNER JOIN purchases ON books.bookid = purchases.bookid WHERE clientid = ?";
        List<Book> books = jdbcTemplate.query(sqlBooks, BeanPropertyRowMapper.newInstance(Book.class), id);
        client.setBooks(books);
        return Optional.of(client);
    }

    @Override
    public Iterable<Client> getAll() {
        //language=PostgreSQL
        String sql = "SELECT * FROM clients";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {
        validator.validate(entity);
        //language=PostgreSQL
        String sqlUPDATE = "update clients set firstname = ?, lastname = ? WHERE clientid = ?";
        jdbcTemplate.update(sqlUPDATE, entity.getFirstName(), entity.getLastName(),entity.getId());

        //language=PostgreSQL
        Set<Integer> addBooks = entity.getBooks().stream().map(Book::getId).collect(Collectors.toSet());
        Set<Integer> deleteBooks = new HashSet<>();


        String booksId = "SELECT bookid FROM purchases WHERE clientid=?";
        List<Integer> oldBookIds = jdbcTemplate.query(booksId, BeanPropertyRowMapper.newInstance(Integer.class), entity.getId());
        for (Integer bookId : oldBookIds) {
            if (addBooks.contains(bookId)) {
                addBooks.remove(bookId);
            } else {
                deleteBooks.add(bookId);
            }
        }

        for (Integer item : addBooks) {
            this.addPurchase(entity.getId(), item);
        }

        for (Integer item : deleteBooks) {
            this.removePurchase(entity.getId(), item);
        }
        return Optional.empty();
    }

    private void removePurchase(Integer cid, Integer bid) {
        //language=PostgreSQL
        String addPurchaseBook = "DELETE FROM purchases WHERE clientid=? AND bookid=?";
        jdbcTemplate.update(addPurchaseBook, cid, bid);
    }

    private void addPurchase(Integer cid, Integer bid) {
        //language=PostgreSQL
        String addPurchaseBook = "INSERT INTO purchases (clientid, bookid) VALUES (?, ?)";
        jdbcTemplate.update(addPurchaseBook, cid, bid);
    }

    @Override
    public Optional<Client> delete(int id) {
        //language=PostgreSQL
        String sqlDelete = "DELETE FROM clients WHERE clientid = ?";
        jdbcTemplate.update(sqlDelete, id);
        return Optional.empty();
    }
}
