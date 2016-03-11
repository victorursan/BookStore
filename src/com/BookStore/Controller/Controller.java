package com.BookStore.Controller;

import com.BookStore.Model.BaseEntity;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.IRepository;
import com.BookStore.Repository.InMemoryRepository;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Controller {
    private IRepository<Book> bookRepository;
    private IRepository<Client> clientRepository;

    /**
     */
    public Controller() {
        this(new InMemoryRepository<>(),new InMemoryRepository<>() );
    }

    /**
     * @param bookRepository
     * @param clientRepository
     */
    public Controller(IRepository<Book> bookRepository, IRepository<Client> clientRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * @param it
     * @return
     */
    private int getValidIDForIterable(Iterable<? extends BaseEntity<Integer>> it) {
        Stream<? extends BaseEntity<Integer>> entities = StreamSupport.stream(it.spliterator(), false);
        OptionalInt validID = entities.mapToInt(BaseEntity::getId).max();
        int id = 0;
        if (validID.isPresent()) id = validID.getAsInt() + 1;
        return id;
    }

    /**
     * @param title
     * @param author
     * @param ISBN
     * @param genre
     * @param publisher
     * @param price
     * @param available
     * @throws ValidatorException
     */
    public void addBook(String title, String author, Long ISBN, String genre, String publisher, Integer price,
                        Boolean available) throws ValidatorException {
        Integer validID = getValidIDForIterable(bookRepository.getAll());
        Book book = new Book(validID, title, author, ISBN, genre, publisher, price, available);
        bookRepository.add(book);
    }

    /**
     * @param firstName
     * @param lastName
     * @throws ValidatorException
     */
    public void addClient(String firstName, String lastName) throws ValidatorException {
        Integer validID = getValidIDForIterable(clientRepository.getAll());
        Client client = new Client(validID, firstName, lastName);
        clientRepository.add(client);
    }

    /**
     * @param initId
     * @param title
     * @param author
     * @param ISBN
     * @param genre
     * @param publisher
     * @param price
     * @param available
     * @throws ValidatorException
     */
    public void updateBook(int initId, String title, String author, Long ISBN, String genre, String publisher,
                           Integer price, Boolean available) throws ValidatorException {
        Integer validID = getValidIDForIterable(bookRepository.getAll());
        Book book = new Book(validID, title, author, ISBN, genre, publisher, price, available);
        bookRepository.update(initId, book);
    }

    /**
     * @param initId
     */
    public void deleteBook(int initId) {
        bookRepository.delete(initId);
    }

    /**
     * @return
     */
    public Iterable<Client> getAllClients() {
        return clientRepository.getAll();
    }

    /**
     * @return
     */
    public Iterable<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    /**
     * Returns a list of the books of the specified genre
     *
     * @param s the genre to search for
     * @return elements that have the specified genre
     */
    public List<Book> filterBooksByGenre(String s) {
        Iterable<Book> books = bookRepository.getAll();
        return StreamSupport.stream(books.spliterator(), false)
                .filter(book -> book.getGenre().equals(s)).collect(Collectors.toList());
    }
}