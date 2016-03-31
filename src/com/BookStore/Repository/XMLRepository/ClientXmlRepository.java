package com.BookStore.Repository.XMLRepository;

import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.InMemoryRepository;
import com.BookStore.util.XmlReader;
import com.BookStore.util.XmlWriter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * @author radu.
 */
public class ClientXmlRepository extends InMemoryRepository<Client> {
    private Path clientFilePath;

    public ClientXmlRepository(IValidator<Client> validator, String fileName) {
        super(validator);
        clientFilePath = Paths.get(fileName);

        loadData();
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
        new XmlWriter<Client>(clientFilePath).save(entity);
    }
}
