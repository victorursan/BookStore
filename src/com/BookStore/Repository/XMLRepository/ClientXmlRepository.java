package com.BookStore.Repository.XMLRepository;

import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.Exceptions.RepositoryException;
import com.BookStore.Repository.InMemoryRepository;
import com.BookStore.util.XmlReader;
import com.BookStore.util.XmlWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;


public class ClientXmlRepository extends InMemoryRepository<Client> {
    private Path clientFilePath;

    public ClientXmlRepository(IValidator<Client> validator, String fileName) {
        super(validator);
        clientFilePath = Paths.get(fileName);

        loadData();
    }

    private void rewriteToFile() {
        try {
            new XmlWriter<Client>(clientFilePath).save(super.getAll());
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private void loadData() {
        List<Client> clients = new XmlReader<Client>(clientFilePath).loadEntities();
        for (Client Client : clients) {
            try {
                super.add(Client);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Client entity) throws ValidatorException {
        super.add(entity);
        rewriteToFile();

    }

    @Override
    public Optional<Client> update(Client elem) throws ValidatorException {
        Optional<Client> client = super.update(elem);
        if (!client.isPresent()) {
            rewriteToFile();
        }
        return super.update(elem);

    }

    @Override
    public Optional<Client> delete(int id) {
        Optional<Client> client = super.delete(id);
        client.ifPresent(t -> rewriteToFile());
        return client;
    }
}
