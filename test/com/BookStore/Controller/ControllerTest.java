package com.BookStore.Controller;

import com.BookStore.Model.BaseEntity;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Repository.IRepository;
import com.BookStore.Repository.InMemoryRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.OptionalInt;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller ctrl;
    @Before
    public void setUp() throws Exception {
        IRepository<Book> bookrepo = new InMemoryRepository<>();
        IRepository<Client> clientrepo = new InMemoryRepository<>();
        ctrl = new Controller(bookrepo, clientrepo);
        ctrl.addBook("LastBook", "Barry", 456789012L, "Fantasy", "SomePub", 10);
        ctrl.addBook("SecondBook", "Jane", 234567890L, "Horror", "NicePub", 27);
        ctrl.addBook("ThirdBook", "Foo", 345678900L, "SF", "AwesomePub", 7);
        ctrl.addBook("Paradigme Universale", "Solomon Marcus", 9736975797L, "filosofie", "Paralela 45", 100);
        ctrl.addClient("Laura", "Pop");
        ctrl.addClient("Victor", "Ursan");
        ctrl.buyBook(1,1);
        ctrl.buyBook(0,0);
        ctrl.buyBook(1,2);
    }
    private <T extends BaseEntity<Integer>> Stream<T> getStreamFromIterable(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    @Test
    public void addBook() throws Exception {
        ctrl.addBook("FirstBook", "John", 123456789L, "SF", "CoolPub", 17);
        assertSame(getStreamFromIterable(ctrl.getAllBooks()).count(), 5L);
    }

    @Test
    public void addClient() throws Exception {
        ctrl.addClient("Maria", "Ion");
        assertSame(getStreamFromIterable(ctrl.getAllClients()).count(), 3L);
    }

    @Test
    public void updateBook() throws Exception {
        ctrl.updateBook(0, "L", "B", 456789012L, "F", "S", 10, false);

    }

    @Test
    public void updateClient() throws Exception {

    }

    @Test
    public void deleteBook() throws Exception {

    }

    @Test
    public void deleteClient() throws Exception {

    }

    @Test
    public void getAllClients() throws Exception {

    }

    @Test
    public void getAllBooks() throws Exception {

    }

    @Test
    public void availableBooks() throws Exception {

    }

    @Test
    public void filterBooksByGenre() throws Exception {

    }

    @Test
    public void filterBooksByAuthor() throws Exception {

    }

    @Test
    public void filterBooksCheaperThan() throws Exception {

    }

    @Test
    public void filterBooksMoreExpensiveThan() throws Exception {

    }

    @Test
    public void clientWhoSpentMost() throws Exception {

    }

    @Test
    public void clientWithMostBooks() throws Exception {

    }

    @Test
    public void buyBook() throws Exception {

    }

    @Test
    public void returnBook() throws Exception {

    }

    @Test
    public void clientBooks() throws Exception {

    }

    @Test
    public void booksWithISBN() throws Exception {

    }

    @Test
    public void uniqueAvailableBooks() throws Exception {

    }
}