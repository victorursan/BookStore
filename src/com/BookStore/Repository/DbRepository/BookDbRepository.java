package com.BookStore.Repository.DbRepository;

import com.BookStore.Model.Book;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.Exceptions.RepositoryException;
import com.BookStore.Repository.IRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BookDbRepository implements IRepository<Book> {
    private String url;
    private String username;
    private String password;
    private IValidator<Book> validator;

    public BookDbRepository(String url, String username, String password, IValidator<Book> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public void add(Book entity) throws ValidatorException {
        if (entity == null) {
            throw new RepositoryException("Book entity must not be null");
        }
        validator.validate(entity);

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO books (bookid, title, author, isbn, genre, publisher, available, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getTitle());
            statement.setString(3, entity.getAuthor());
            statement.setLong(4, entity.getISBN());
            statement.setString(5, entity.getGenre());
            statement.setString(6, entity.getPublisher());
            statement.setBoolean(7, entity.isAvailable());
            statement.setInt(8, entity.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Optional<Book> get(int id) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * from books where bookid=?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int bookid = resultSet.getInt("bookid");
                    String auth = resultSet.getString("author");
                    String title = resultSet.getString("title");
                    Long isbn = resultSet.getLong("isbn");
                    String genre = resultSet.getString("genre");
                    String publ = resultSet.getString("publisher");
                    Boolean av = resultSet.getBoolean("available");
                    int price = resultSet.getInt("price");
                    Book book = new Book(bookid, title, auth, isbn, genre, publ, price, av);
                    return Optional.of(book);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
        return Optional.empty();
    }


    @Override
    public Iterable<Book> getAll() {
        Set<Book> books = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from books");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int bookid = resultSet.getInt("bookid");
                String auth = resultSet.getString("author");
                String title = resultSet.getString("title");
                Long isbn = resultSet.getLong("isbn");
                String genre = resultSet.getString("genre");
                String publ = resultSet.getString("publisher");
                Boolean av = resultSet.getBoolean("available");
                int price = resultSet.getInt("price");
                Book book = new Book(bookid, title, auth, isbn, genre, publ, price, av);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Optional<Book> update(Book entity) throws ValidatorException {
        if (entity == null) {
            throw new RepositoryException("entity must not be null");
        }
        validator.validate(entity);

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE books SET title=?, author=?, isbn=?, genre=?, publisher=?, available=?, price=? where bookid=?")) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getAuthor());
            statement.setLong(3, entity.getISBN());
            statement.setString(4, entity.getGenre());
            statement.setString(5, entity.getPublisher());
            statement.setBoolean(6, entity.isAvailable());
            statement.setInt(7, entity.getPrice());
            statement.setInt(8, entity.getId());
            statement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(entity);
    }

    @Override
    public Optional<Book> delete(int id) {
        Optional<Book> book = get(id);

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM books where bookid=?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
