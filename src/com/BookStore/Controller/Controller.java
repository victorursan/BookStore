package com.BookStore.Controller;

import com.BookStore.Model.BaseEntity;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.IRepository;
import com.BookStore.Repository.InMemoryRepository;

import java.util.List;
import java.util.Optional;
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
     * Return a list of available books
     * @return elements that are available
     */
    public List<Book> availableBooks() {
        return getStreamFromIterable(bookRepository.getAll())
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
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

    /**
     * Returns a list of the books from the specified author
     *
     * @param s the author to search for
     * @return elements that have the specified author
     */
    public List<Book> filterBooksByAuthor(String s) {
        return getStreamFromIterable(bookRepository.getAll())
                .filter(book -> book.getAuthor().equals(s))
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of books cheaper than a value given
     *
     * @param value to compare with other prices
     * @return elements that have the price attribute less than value given
     */
    public List<Book> filterBooksCheaperThan(int value) {
        return getStreamFromIterable(bookRepository.getAll())
                .filter(book -> book.getPrice() < value)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of books more expensive than a value given
     *
     * @param value to compare with other prices
     * @return elements that have the price attribute more than value given
     */
    public List<Book> filterBooksMoreExpensiveThan(int value) {
        return getStreamFromIterable(bookRepository.getAll())
                .filter(book -> book.getPrice() > value)
                .collect(Collectors.toList());
    }

    /**
     * Returns the client who spent the most amount of money on books
     *
     * @return A client who has the sum of prices of books the highest of all
     */
    public Optional<Client> clientWhoSpentMost() {
        return getStreamFromIterable(clientRepository.getAll())
                .sorted((c1, c2) -> c1.moneySpent() - c2.moneySpent()).findFirst();
    }

    /**
     * Returns the client with most books purchased
     *
     * @return A client who has the most books
     */
    public Optional<Client> clientWithMostBooks() {
        return getStreamFromIterable(clientRepository.getAll())
                .sorted((c1, c2) -> c1.getBooks().size() - c2.getBooks().size()).findFirst();
    }


    public void buyBook(int clientID, int bookID) {
        Optional<Book> optBook = bookRepository.get(bookID);
        Optional<Client> optClient = clientRepository.get(clientID);
        optClient.ifPresent(client -> optBook.ifPresent(book -> {
            book.setAvailable(false);
            client.buyBook(book);
            try {
                bookRepository.update(book);
                clientRepository.update(client);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }));

    }

    public void returnBook(int clientID, int bookID) {
        Optional<Book> optBook = bookRepository.get(bookID);
        Optional<Client> optClient = clientRepository.get(clientID);
        optClient.ifPresent(client -> optBook.ifPresent(book -> {
            book.setAvailable(true);
            if (client.returnBook(book)) {
                try {
                    bookRepository.update(book);
                    clientRepository.update(client);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            } else {
                book.setAvailable(false);
            }
        }));
    }

    public Optional<List<Book>> clientBooks(int clientID) {
        Optional<List<Book>> books = Optional.empty();
        Optional<Client> optClient = clientRepository.get(clientID);
        if (optClient.isPresent()) {
            books = Optional.of(optClient.get().getBooks());
        }
        return books;
    }

}