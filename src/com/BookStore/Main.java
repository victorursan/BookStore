package com.BookStore;

import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Model.Validators.ClientValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.BookFileRepository;
import com.BookStore.Repository.ClientFileRepository;
import com.BookStore.Repository.IRepository;

public class Main {
    public static void main(String[] args) throws ValidatorException {
//        Console console = new Console();
//        console.run();
        IRepository<Book> bookrepo = new BookFileRepository(new BookValidator(), "./data/FileData/Books.txt");
        IRepository<Client> clientrepo = new ClientFileRepository(new ClientValidator(), "./data/FileData/Clients.txt", "./data/FileData/Purchase.txt", bookrepo);
        System.out.print(clientrepo.getAll());
    }
}
