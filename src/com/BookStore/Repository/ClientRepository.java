package com.BookStore.Repository;

import com.BookStore.Model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements IRepository<Client> {

    private List<Client> clients;

    public ClientRepository() {
        clients = new ArrayList<>();
    }

    @Override
    public void add(Client c) {
        clients.add(c);
    }

    @Override
    public Client get(int id) {
        return clients.get(id);
    }

    @Override
    public ArrayList<Client> getAll() {
        return (ArrayList<Client>) clients;
    }

    @Override
    public void update(int id, Client c) {
        clients.set(id, c);
    }

    @Override
    public void delete(int id) {
        clients.remove(id);
    }
}
