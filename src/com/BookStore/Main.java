package com.BookStore;

import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Model.Validators.ClientValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.BookFileRepository;
import com.BookStore.Repository.ClientFileRepository;
import com.BookStore.Repository.IRepository;
import com.BookStore.View.Console;

public class Main {
    public static void main(String[] args) throws ValidatorException {
//        Console console = new Console();
//        console.run();
        IRepository<Client> rep = new ClientFileRepository(new ClientValidator(), "./data/FileData/Clients.txt",
                                                        "./data/FileData/Purchase.txt");
        System.out.print(rep.getAll());
    }
}
