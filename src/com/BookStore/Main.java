package com.BookStore;

import com.BookStore.Controller.Controller;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Model.Validators.ClientValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.FileRepository.BookFileRepository;
import com.BookStore.Repository.FileRepository.ClientFileRepository;
import com.BookStore.Repository.IRepository;
import com.BookStore.Repository.XMLRepository.BookXmlRepository;
import com.BookStore.Repository.XMLRepository.ClientXmlRepository;
import com.BookStore.View.Console;
import com.sun.tools.javac.code.Attribute;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws ValidatorException {
//        String bookPath = "./data/FileData/Books.txt";
//        String clientPath = "./data/FileData/Clients.txt";
//        String purchasePath = "./data/FileData/Purchase.txt";

        String bookXMLPath = "./data/XMLData/BooksXML.xml";
        String clientXMLPath = "./data/XMLData/ClientsXML.xml";

        IRepository<Book> bookrepo = new BookXmlRepository(new BookValidator(), bookXMLPath);
//        IRepository<Client> clientrepo = new ClientXmlRepository(new ClientValidator(), clientXMLPath);

//        bookrepo.add(new Book(1, "Victor", "Barry", 456789012L, "Fantasy", "SomePub", 10, true));
//        System.out.println("\n");
//        clientrepo.add(new Client(1, "Victor", "Uran", Arrays.asList(new Book(1, "Victor", "Barry", 456789012L, "Fantasy", "SomePub", 10, true), new Book(2, "Victr", "Barry", 456789012L, "Fantasy", "SomePub", 10, true))));

//        try {
//            IRepository<Book> bookrepo = new BookFileRepository(new BookValidator(), bookPath);
//            IRepository<Client> clientrepo = new ClientFileRepository(new ClientValidator(), clientPath, purchasePath, bookrepo);
//            Console console = new Console(new Controller(bookrepo, clientrepo));
//            console.run();
//        } catch (BaseException e) {
//            System.out.println(e.getMessage());
//        }


    }
}
