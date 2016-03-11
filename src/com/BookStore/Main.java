package com.BookStore;

import com.BookStore.Controller.Controller;
import com.BookStore.Model.Validators.ValidatorException;

public class Main {
    public static void main(String[] args) {
        Controller ctrl = new Controller();
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
