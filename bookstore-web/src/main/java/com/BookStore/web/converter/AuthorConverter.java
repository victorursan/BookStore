package com.BookStore.web.converter;

import com.BookStore.core.models.Author;
import com.BookStore.web.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by victor on 6/21/16.
 */
@Component
public class AuthorConverter extends BaseConverter<Author, AuthorDto> {

    @Autowired
    private BookConverter bookConverter;

    @Override
    public AuthorDto convertModelToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setBooks(bookConverter.convertModelsToIDs(author.getList_of_books()));

        return authorDto;
    }
}
