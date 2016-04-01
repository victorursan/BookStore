package com.BookStore;

import com.BookStore.Controller.Controller;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Model.Validators.ClientValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.DbRepository.BookDbRepository;
import com.BookStore.Repository.DbRepository.ClientDbRepository;
import com.BookStore.Repository.IRepository;
import com.BookStore.View.Console;

public class Main {
    public static void main(String[] args) throws ValidatorException {
        String url = "jdbc:postgresql://localhost:5432/bookstore";
        String user = "dana";
        String pass = "sillycats";
        try {
            IRepository<Book> bookrepo = new BookDbRepository(url, user, pass, new BookValidator());
            IRepository<Client> clientrepo = new ClientDbRepository(url, user, pass, new ClientValidator(), bookrepo);
            Console console = new Console(new Controller(bookrepo, clientrepo));
            console.run();
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }
}
