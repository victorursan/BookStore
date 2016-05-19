package com.BookStore.web.converter;

import com.BookStore.core.models.Client;
import com.BookStore.web.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by victor on 5/19/16.
 */
@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Autowired
    private BookConverter bookConverter;

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientDto = new ClientDto();

        clientDto.setId(client.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());

        clientDto.setBooks(bookConverter.convertModelsToIDs(client.getBooks()));

        return clientDto;
    }
}
