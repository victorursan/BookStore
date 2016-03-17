package com.BookStore.Model.Validators;

import com.BookStore.Model.Book;

public class BookValidator implements IValidator<Book> {
    @Override
    public void validate(Book entity) throws ValidatorException {
        String message = "";
        if (entity.getAuthor().isEmpty()) message += "Author can't be empty!\n";
        if (entity.getTitle().isEmpty()) message += "Title can't be empty!\n";
        if (!message.isEmpty()) throw new ValidatorException(message);
    }
}
