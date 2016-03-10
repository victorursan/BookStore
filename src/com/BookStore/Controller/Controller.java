package com.BookStore.Controller;

import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.IRepository;

public class Controller {
    private IRepository<Book> bookRepository;
    private IRepository<Client> clientRepository;

    public Controller(IRepository<Book> bookRepository, IRepository<Client> clientRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    public void addBook(String title, String author, Long ISBN, String genre, String publisher, Integer price, Boolean available) throws ValidatorException {
        //TODO
//        Book book = new Book(1, title, author, ISBN, genre, publisher, price, available);
//        bookRepository.add(book);
    }

    public void addClient(String firstName, String lastName) throws ValidatorException {
        //TODO
//        Client client = new Client(1, firstName, lastName);
//        clientRepository.add(client);
    }
}