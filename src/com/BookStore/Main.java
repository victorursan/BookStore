package com.BookStore;

import com.BookStore.Model.Book;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Repository.BookFileRepository;
import com.BookStore.Repository.IRepository;
import com.BookStore.View.Console;

public class Main {
    public static void main(String[] args) {
//        Console console = new Console();
//        console.run();
        IRepository<Book> rep = new BookFileRepository(new BookValidator(), "./data/FileData/Books.txt");
        System.out.print(rep.getAll());
    }
}
