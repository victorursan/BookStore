package com.BookStore;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.View.Console;

public class Main {
    public static void main(String[] args) throws ValidatorException {
        Console console = new Console();
        console.run();
    }
}
