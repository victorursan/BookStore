package com.BookStore.Model.Validators;

import com.BookStore.Model.Client;

public class ClientValidator implements IValidator<Client> {
    @Override
    public void validate(Client entity) throws ValidatorException {
        String message = "";
        if (entity.getFirstName().isEmpty()) message += "First name can't be empty!\n";
        if (entity.getLastName().isEmpty()) message += "Last name can't be empty!\n";
        if (!message.isEmpty()) throw new ValidatorException(message);
    }
}
