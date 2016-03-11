package com.BookStore.View;


import com.BookStore.Controller.Controller;
import com.BookStore.Model.Validators.ValidatorException;

public class Console {

    public <T> void print(T message) {
        System.out.print(message);
    }

    public <T> void println(T message) {
        System.out.println(message);
    }

    public void run() {
        Controller ctrl = new Controller();
        try {
            ctrl.addClient("Ion", "Ion");
            ctrl.addClient("Ion1", "Ion4");
            ctrl.addClient("Ion2", "Ion3");
            print(ctrl.getAllClients());
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }
}
