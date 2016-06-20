package com.BookStore.core.service.hessian;

import com.BookStore.Models.Book;
import com.BookStore.core.repositories.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by victor on 6/20/16.
 */
@Service
@DependsOn("hessianControllerService")
public class HessianControllerServiceClientImpl implements HessianControllerServiceClient {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private HessianControllerService hessianControllerService;

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Override
    public void importBooks() {
        List<Book> hessianBooks = hessianControllerService.getAllBooks();
        List<com.BookStore.core.models.Book> disciplines = hessianBooks.stream()
                .map(this::convertHessianBookToBook)
                .collect(Collectors.toList());

        disciplines.stream()
                .forEach(d -> bookStoreRepository.save(d));

    }

    private com.BookStore.core.models.Book convertHessianBookToBook(Book book) {
        com.BookStore.core.models.Book newBook =  new com.BookStore.core.models.Book();
        newBook.setIsbn(book.getISBN());
        newBook.setTitle(book.getTitle());
        newBook.setAvailable(book.isAvailable());
        newBook.setAuthor(book.getAuthor());
        newBook.setPublisher(book.getPublisher());
        newBook.setGenre(book.getGenre());
        newBook.setPrice(book.getPrice());
        return newBook;
    }


}
