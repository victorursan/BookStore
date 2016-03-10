package com.BookStore;

import com.BookStore.Controller.Controller;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Model.Validators.ClientValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.InMemoryRepository;

public class Main {
    public static void main(String[] args) {
        Controller ctrl = new Controller(new InMemoryRepository<>(new BookValidator()), new InMemoryRepository<>(new ClientValidator()));
        try {
            ctrl.addClient("Ion", "Ion");
            ctrl.addClient("Ion1", "Ion4");
            ctrl.addClient("Ion2", "Ion3");
            System.out.print(ctrl.getAllClients());
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }
}
