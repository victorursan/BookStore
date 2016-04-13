package com.BookStore.Model.Validators;

import com.BookStore.Model.Book;
import org.junit.Test;

public class BookValidatorTest {

    @Test(expected = ValidatorException.class)
    public void validate() throws Exception {
        BookValidator validator = new BookValidator();
        Book b = new Book(1, "", "Barry", 456789012L, "Fantasy", "SomePub", 10, true);
        validator.validate(b);
    }
}