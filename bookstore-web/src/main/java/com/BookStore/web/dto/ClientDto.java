package com.BookStore.web.dto;

import com.BookStore.core.models.Client;

/**
 * Created by victor on 5/12/16.
 */
public class ClientDto {
    private Client client;

    public ClientDto() {
    }

    public ClientDto(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getId() {
        return client.getId();
    }

    public void setId(Integer aInteger) {
        client.setId(aInteger);
    }

    public String getFirstName() {
        return client.getFirstName();
    }

    public void setFirstName(String firstName) {
        client.setFirstName(firstName);
    }

    public String getLastName() {
        return client.getLastName();
    }

    public void setLastName(String firstName) {
        client.setLastName(firstName);
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "client=" + client +
                '}';
    }
}
