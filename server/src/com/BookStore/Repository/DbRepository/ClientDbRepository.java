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
import java.util.stream.Collectors;

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


        if (entity.getBooks().size() > 0) {
            for (Book item : entity.getBooks()) {
                addPurchase(entity.getId(), item.getId());
            }
        }
    }

    @Override
    public Optional<Client> get(int id) {
        Client client = null;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients WHERE clientid=?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int clientId = resultSet.getInt("clientid");
                    String firstName = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    client = new Client(clientId, firstName, lastName);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM purchases WHERE clientid=?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("bookid");
                    if (client != null) {
                        bookRepo.get(bookId).ifPresent(client::buyBook);
                    }
                }
            }
            if (client != null) {
                return Optional.of(client);
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
             PreparedStatement statement = connection.prepareStatement("SELECT clientid FROM clients");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int clientId = resultSet.getInt("clientid");
                this.get(clientId).ifPresent(clients::add);
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Set<Integer> addBooks = entity.getBooks().stream().map(Book::getId).collect(Collectors.toSet());
        Set<Integer> deleteBooks = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT bookid FROM purchases WHERE clientid=?")) {
            statement.setInt(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("bookid");
                    if (addBooks.contains(bookId)) {
                        addBooks.remove(bookId);
                    } else {
                        deleteBooks.add(bookId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
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
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM purchases WHERE clientid=? AND bookid=?")) {
            statement.setInt(1, cid);
            statement.setInt(2, bid);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addPurchase(Integer cid, Integer bid) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO purchases (clientid, bookid) VALUES (?, ?)")) {
            statement.setInt(1, cid);
            statement.setInt(2, bid);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
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
