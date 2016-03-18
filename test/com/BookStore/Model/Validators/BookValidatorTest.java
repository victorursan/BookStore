package com.BookStore.Model.Validators;

import com.BookStore.Model.Book;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookValidatorTest {

    @Test
    public void validate() throws Exception {
        BookValidator validator = new BookValidator();
        Book b = new Book(1, "", "Barry", 456789012L, "Fantasy", "SomePub", 10, true);
        try {
            validator.validate(b);
            assertTrue(false);
        } catch (ValidatorException e) {
            assertFalse(false);
        }
    }
}