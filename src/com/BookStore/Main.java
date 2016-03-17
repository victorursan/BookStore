package com.BookStore;

import com.BookStore.Model.Book;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.BookFileRepository;
import com.BookStore.Repository.IRepository;
import com.BookStore.View.Console;

public class Main {
    public static void main(String[] args) throws ValidatorException {
//        Console console = new Console();
//        console.run();
        IRepository<Book> rep = new BookFileRepository(new BookValidator(), "./data/FileData/Books.txt");
//        rep.update(new Book(5,"a","b", (long) 123, "c", "d", 12));
        System.out.print(rep.getAll());
    }
}
