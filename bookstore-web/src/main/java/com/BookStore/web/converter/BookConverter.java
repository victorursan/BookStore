package com.BookStore.web.converter;

import com.BookStore.core.models.Book;
import com.BookStore.web.dto.BookDto;
import org.springframework.stereotype.Component;

/**
 * Created by victor on 5/19/16.
 */
@Component
public class BookConverter extends BaseConverter<Book,BookDto>{

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setGenre(book.getGenre());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setPrice(book.getPrice());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        bookDto.setAvailable(book.isAvailable());

        return bookDto;
    }
}
