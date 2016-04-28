package com.BookStore.web.dto;

import com.BookStore.core.models.Client;

import java.io.Serializable;
import java.util.List;

/**
 * Created by victor on 4/28/16.
 */
public class ClientsDto implements Serializable {
    private List<Client> clients;

    public ClientsDto() {}

    public ClientsDto(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "ClientsDto{ clients = " + clients + '}';
    }
}
