package com.BookStore;

import com.BookStore.Controller.Controller;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Model.Validators.ClientValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.IRepository;
import com.BookStore.Repository.XMLRepository.BookXMLRepository;
import com.BookStore.Repository.XMLRepository.ClientXMLRepository;
import com.BookStore.View.Console;

public class Main {
    public static void main(String[] args) throws ValidatorException {

        String bookPath = "./data/XMLData/Book.xml";
        String clientPath = "./data/XMLData/Client.xml";
        String purchasePath = "./data/FileData/Purchase.txt";
        try {
            IRepository<Book> bookrepo = new BookXMLRepository(new BookValidator(), bookPath);
            IRepository<Client> clientrepo = new ClientXMLRepository(new ClientValidator(), clientPath);
            Console console = new Console(new Controller(bookrepo, clientrepo));
            console.run();
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }
}
