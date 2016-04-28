package com.BookStore.core.service;

import com.BookStore.Controller.Controller;
import com.BookStore.ControllerService;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Model.Validators.ClientValidator;
import com.BookStore.Models.Book;
import com.BookStore.Models.Client;
import com.BookStore.Repository.DbRepository.BookDbRepository;
import com.BookStore.Repository.DbRepository.ClientDbRepository;
import com.BookStore.Repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ControllerServiceServer implements ControllerService {

    private Controller ctrl;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        IRepository<Book> bookrepo = new BookDbRepository(jdbcTemplate, new BookValidator());
        IRepository<Client> clientrepo = new ClientDbRepository(jdbcTemplate, new ClientValidator());
        ctrl = new Controller(bookrepo, clientrepo);
    }


    @Override
    public String getAllOptions() {
        return "Options:" +
                "\n1. Add client" +
                "\n2. Add book" +
                "\n3. Delete client" +
                "\n4. Delete book" +
                "\n5. Update client" +
                "\n6. Update book" +
                "\n7. Show all clients" +
                "\n8. Show all books" +
                "\n9. Show available books" +
                "\n10. Books of a genre" +
                "\n11. Books by an author" +
                "\n12. Books cheaper than" +
                "\n13. Books more expensive than" +
                "\n14. Client with most books" +
                "\n15. Client who spent most" +
                "\n16. Client purchase" +
                "\n17. Client return" +
                "\n18. Purchases by one client" +
                "\n0. Exit";
    }

    @Override
    public void addClient(String firstName, String lastName) {
        ctrl.addClient(firstName, lastName);
    }

    @Override
    public void addBook(String title, String auth, Long isbn, String genre, String publisher, Integer price) {
        ctrl.addBook(title, auth, isbn, genre, publisher, price);
    }

    @Override
    public void updateClient(Integer id, String firstName, String lastName) {
        ctrl.updateClient(id, firstName, lastName);
    }

    @Override
    public void deleteClient(Integer id) {
        ctrl.deleteClient(id);
    }

    @Override
    public void updateBook(Integer id, String title, String auth, Long isbn, String genre, String publisher, Integer price, Boolean available) {
        ctrl.updateBook(id, title, auth, isbn, genre, publisher, price, available);
    }

    @Override
    public void deleteBook(Integer id) {
        ctrl.deleteBook(id);
    }

    @Override
    public List<Book> clientBooks(Integer id) {
        Optional<List<Book>> books = ctrl.clientBooks(id);
        if (books.isPresent()) {
            return books.get();
        }
        return null;
    }

    @Override
    public Client clientWhoSpentMost() {
        Optional<Client> client = ctrl.clientWhoSpentMost();
        if (client.isPresent()) {
            return client.get();
        }
        return null;
    }

    @Override
    public Client clientWithMostBooks() {
        Optional<Client> client = ctrl.clientWithMostBooks();
        if (client.isPresent()) {
            return client.get();
        }
        return null;
    }

    @Override
    public Iterable<Client> getAllClients() {
        return ctrl.getAllClients();
    }

    @Override
    public Iterable<Book> getAllBooks() {
        return ctrl.getAllBooks();
    }

    @Override
    public List<Book> availableBooks() {
        return ctrl.availableBooks();
    }

    @Override
    public List<Book> filterBooksByGenre(String genre) {
        return ctrl.filterBooksByGenre(genre);
    }

    @Override
    public List<Book> filterBooksByAuthor(String auth) {
        return ctrl.filterBooksByAuthor(auth);
    }

    @Override
    public List<Book> filterBooksCheaperThan(Integer price) {
        return ctrl.filterBooksCheaperThan(price);
    }

    @Override
    public List<Book> filterBooksMoreExpensiveThan(Integer price) {
        return ctrl.filterBooksMoreExpensiveThan(price);
    }

    @Override
    public void returnBook(Integer clientId, Integer bookId) {
        ctrl.returnBook(clientId, bookId);
    }

    @Override
    public void buyBook(Integer clientId, Integer bookId) {
        ctrl.buyBook(clientId, bookId);
    }

}