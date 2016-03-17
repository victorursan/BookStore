package com.BookStore;
import com.BookStore.Controller.Controller;
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
        String bookPath = "./data/FileData/Books.txt";
        String clientPath =  "./data/FileData/Clients.txt";
        String purchasePath = "./data/FileData/Purchase.txt";

        IRepository<Book> bookrepo = new BookFileRepository(new BookValidator(), bookPath);
        IRepository<Client> clientrepo = new ClientFileRepository(new ClientValidator(), clientPath, purchasePath, bookrepo);
        Console console = new Console(new Controller(bookrepo, clientrepo));
        console.run();
    }
}
