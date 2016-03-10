package com.BookStore.Controller;

import com.BookStore.Model.BaseEntity;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.IRepository;
import java.util.OptionalInt;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Controller {
    private IRepository<Book> bookRepository;
    private IRepository<Client> clientRepository;

    public Controller(IRepository<Book> bookRepository, IRepository<Client> clientRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    private int getValidIDForIteratory(Iterable<? extends BaseEntity<Integer>> iter) {
        Stream<? extends BaseEntity<Integer>> books = StreamSupport.stream(iter.spliterator(), false);
        OptionalInt validID = books.mapToInt(BaseEntity::getId).max();
        int id = 0;
        if (validID.isPresent()) id = validID.getAsInt() + 1;
        return id;
    }

    public void addBook(String title, String author, Long ISBN, String genre, String publisher, Integer price, Boolean available) throws ValidatorException {
        Integer validID = getValidIDForIteratory(bookRepository.getAll());
        Book book = new Book(getValidIDForIteratory(bookRepository.getAll()), title, author, ISBN, genre, publisher, price, available);
        bookRepository.add(book);
    }

    public void addClient(String firstName, String lastName) throws ValidatorException {
        Integer validID = getValidIDForIteratory(clientRepository.getAll());
        Client client = new Client(validID, firstName, lastName);
        clientRepository.add(client);
    }

    public Iterable<Client> getAllClients() {
        return clientRepository.getAll();
    }
}