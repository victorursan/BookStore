package com.BookStore.Repository.DbRepository;

import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.Exceptions.RepositoryException;
import com.BookStore.Repository.IRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientDbRepository implements IRepository<Client> {
    private String url;
    private String username;
    private String password;
    private IValidator<Client> validator;
    private IRepository<Book> bookRepo;

    public ClientDbRepository(String url, String username, String password, IValidator<Client> validator, IRepository<Book> bookRepo) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
        this.bookRepo = bookRepo;
    }

    public void addBookTo(int idClient, int idBook) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO purchases (clientid, bookid) VALUES (?, ?)")) {
            statement.setInt(1, idClient);
            statement.setInt(1, idBook);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public void deleteBookOf(int idClient, int idBook) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM purchases WHERE clientid=? AND bookid=?")) {
            statement.setInt(1, idClient);
            statement.setInt(1, idBook);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<Optional<Book>> getAllBooksOf(int id) {
        Set<Optional<Book>> books = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM purchases WHERE clientid=?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int bookId = resultSet.getInt("clientid");
                    Optional<Book> book = bookRepo.get(bookId);
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
        return books;
    }

    @Override
    public void add(Client entity) throws ValidatorException {
        if (entity == null) {
            throw new RepositoryException("Book entity must not be null");
        }
        validator.validate(entity);

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO clients (clientid, firstname, lastname) VALUES (?, ?, ?)")) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Optional<Client> get(int id) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients WHERE clientid=?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int clientId = resultSet.getInt("clientid");
                    String firstName = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    Client client = new Client(clientId, firstName, lastName);
                    return Optional.of(client);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Client> getAll() {
        Set<Client> clients = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int clientId = resultSet.getInt("clientid");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                Client client = new Client(clientId, firstName, lastName);
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {
        if (entity == null) {
            throw new RepositoryException("entity must not be null");
        }
        validator.validate(entity);

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE clients SET firstname=?, lastname=? WHERE clientid=?")) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(entity);
    }

    @Override
    public Optional<Client> delete(int id) {
        Optional<Client> client = get(id);

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM clients WHERE clientid=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
}
