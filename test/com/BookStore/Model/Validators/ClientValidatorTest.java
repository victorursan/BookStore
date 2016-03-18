package com.BookStore.Model.Validators;

import com.BookStore.Model.Client;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientValidatorTest {

    @Test
    public void validate() throws Exception {
        ClientValidator validator = new ClientValidator();
        Client c = new Client(1, "", "");
        try {
            validator.validate(c);
            assertTrue(false);
        } catch (ValidatorException e) {
            assertFalse(false);
        }
    }
}