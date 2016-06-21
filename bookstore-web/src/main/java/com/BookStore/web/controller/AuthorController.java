package com.BookStore.web.controller;

import com.BookStore.core.models.Author;
import com.BookStore.core.service.AuthorService;
import com.BookStore.web.converter.AuthorConverter;
import com.BookStore.web.dto.AuthorDto;
import com.BookStore.web.dto.AuthorsDataDto;
import com.BookStore.web.dto.EmptyJsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by victor on 6/21/16.
 */

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorConverter authorConverter;

    @RequestMapping(value = "/authors", method = RequestMethod.GET, produces = "application/vnd.api+json")
    public AuthorsDataDto getAuthors() {
        List<Author> authorList = authorService.findAll();
        Set<AuthorDto> clientDtos = authorConverter.convertModelsToDtos(authorList);
        return new AuthorsDataDto(clientDtos);
    }

    @RequestMapping(value = "/authors/{authorId}", method = RequestMethod.PUT, consumes = "application/vnd.api+json")
    public Map<String, AuthorDto> updateAuthor(@PathVariable final Integer authorId, @RequestBody final Map<String, AuthorDto> authorRequestDtoMap) {
        AuthorDto authorDto = authorRequestDtoMap.get("author");
        Author author = authorService.updateAuthor(authorId, authorDto.getName(), authorDto.getBooks());
        Map<String, AuthorDto> authorDtoMap = new HashMap<>();
        authorDtoMap.put("author", authorConverter.convertModelToDto(author));
        return authorDtoMap;
    }

    @RequestMapping(value = "/authors", method = RequestMethod.POST, consumes = "application/vnd.api+json", produces = "application/vnd.api+json")
    public Map<String, AuthorDto> createAuthor(@RequestBody final Map<String, AuthorDto> authorRequestDtoMap) {
        AuthorDto authorDto = authorRequestDtoMap.get("author");
        Author author = authorService.createAuthor(authorDto.getName());
        Map<String, AuthorDto> authorDtoMap = new HashMap<>();
        authorDtoMap.put("author", authorConverter.convertModelToDto(author));
        return authorDtoMap;
    }

    @RequestMapping(value = "/authors/{authorId}", method = RequestMethod.DELETE, consumes = "application/vnd.api+json")
    public ResponseEntity deleteAuthor(@PathVariable final Integer authorId) {
        authorService.deleteAuthor(authorId);
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
