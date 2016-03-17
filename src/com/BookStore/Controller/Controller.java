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
     * Default constructor
     */
    public Controller() {
        this(new InMemoryRepository<>(), new InMemoryRepository<>());
    }

    /**
     * Constructor
     *
     * @param bookRepository   the book repository
     * @param clientRepository the client repository
     */
    public Controller(IRepository<Book> bookRepository, IRepository<Client> clientRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Transforms an Iterable to Stream
     *
     * @param iterable the Iterable to be transformed
     * @return {@code Stream}
     */
    private <T extends BaseEntity<Integer>> Stream<T> getStreamFromIterable(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    /**
     * Get a valid position/id from the iterable
     *
     * @param iterable the iterable with entities
     * @return {@code id} that is valid in the iterable
     */
    private <T extends BaseEntity<Integer>> int getValidIDForIterable(Iterable<T> iterable) {
        Stream<T> entities = getStreamFromIterable(iterable);
        OptionalInt validID = entities.mapToInt(BaseEntity::getId).max();
        return validID.isPresent() ? validID.getAsInt() + 1 : 0;
    }

    /**
     * Adds a Book in the repository if possible, else throws ValidatorException
     *
     * @param title     the Title
     * @param author    the Author
     * @param ISBN      the ISBN
     * @param genre     the Genre
     * @param publisher the Publisher
     * @param price     the Price
     * @throws ValidatorException if the book is not valid
     */
    public void addBook(String title, String author, Long ISBN, String genre, String publisher, Integer price)
            throws ValidatorException {
        Integer validID = getValidIDForIterable(bookRepository.getAll());
        Book book = new Book(validID, title, author, ISBN, genre, publisher, price, true);
        bookRepository.add(book);
    }

    /**
     * Add a Client in the repository if possible, else throws ValidatorException
     *
     * @param firstName the First Name of the Client
     * @param lastName  the Last Name of the Client
     * @throws ValidatorException if the client is not valid
     */
    public void addClient(String firstName, String lastName) throws ValidatorException {
        Integer validID = getValidIDForIterable(clientRepository.getAll());
        Client client = new Client(validID, firstName, lastName);
        clientRepository.add(client);
    }

    /**
     * Updates the Book in the repository if possible, else throws Validator Exception
     *
     * @param initId    the id of the Book
     * @param title     the new Title
     * @param author    the new Author
     * @param ISBN      the new ISBN
     * @param genre     the new Genre
     * @param publisher the new Publisher
     * @param price     the new Price
     * @param available the new availability
     * @throws ValidatorException        if the books is not valid
     * @throws IndexOutOfBoundsException if the id is not in the repository
     */
    public void updateBook(int initId, String title, String author, Long ISBN, String genre, String publisher,
                           Integer price, Boolean available) throws ValidatorException {
        Book book = new Book(initId, title, author, ISBN, genre, publisher, price, available);
        if (bookRepository.update(book).isPresent())
            throw new IndexOutOfBoundsException();
    }

    /**
     * Updates the Client in the repository if possible, else throws Validator Exception
     *
     * @param initId    the id of the Client
     * @param firstName the new first name
     * @param lastName  the new last name
     * @throws ValidatorException        if the books is not valid
     * @throws IndexOutOfBoundsException if the id is not in the repository
     */
    public void updateClient(int initId, String firstName, String lastName) throws ValidatorException {
        Client client = new Client(initId, firstName, lastName);
        if (clientRepository.update(client).isPresent())
            throw new IndexOutOfBoundsException();
    }

    /**
     * Deletes the Book from repository by id
     *
     * @param initId the id of the book
     * @throws IndexOutOfBoundsException if the id is not in the repository
     */
    public void deleteBook(int initId) throws IndexOutOfBoundsException {
        if (!bookRepository.delete(initId).isPresent())
            throw new IndexOutOfBoundsException();
    }

    /**
     * Deletes the Client from repository by id
     *
     * @param initId the id of the client
     * @throws IndexOutOfBoundsException if the id is not in the repository
     */
    public void deleteClient(int initId) throws IndexOutOfBoundsException {
        if (!clientRepository.delete(initId).isPresent())
            throw new IndexOutOfBoundsException();
    }

    /**
     * Returns an Iterable with all the clients
     *
     * @return {@code Iterable<Client>}
     */
    public Iterable<Client> getAllClients() {
        return clientRepository.getAll();
    }

    /**
     * Returns an Iterable with all the books
     *
     * @return {@code Iterable<Book>}
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
        return getStreamFromIterable(bookRepository.getAll())
                .filter(book -> book.getGenre().equals(s))
                .collect(Collectors.toList());
    }
}