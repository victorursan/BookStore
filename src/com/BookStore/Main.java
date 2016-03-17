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
        String bookPath = "./data/FileData/Books.txt";
        String clientPath =  "./data/FileData/Clients.txt";
        String purchasePath = "./data/FileData/Purchase.txt";

        IRepository<Book> bookrepo = new BookFileRepository(new BookValidator(), bookPath);
        IRepository<Client> clientrepo = new ClientFileRepository(new ClientValidator(), clientPath, purchasePath, bookrepo);

        Client c = new Client(3, "John", "Doe");
        clientrepo.add(c);
        System.out.println(clientrepo.getAll());
        bookrepo.get(1).ifPresent(c::buyBook);
        clientrepo.update(c);
        System.out.println(clientrepo.getAll());
        clientrepo.delete(3);
        System.out.println(clientrepo.getAll());
    }
}
