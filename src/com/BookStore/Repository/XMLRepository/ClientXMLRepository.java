package com.BookStore.Repository.XMLRepository;

import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.Exceptions.RepositoryException;
import com.BookStore.Repository.InMemoryRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

public class ClientXMLRepository extends InMemoryRepository<Client> {
    private Path clientFilePath;

    public ClientXMLRepository(IValidator<Client> validator, String file) {
        super(validator);
        this.clientFilePath = Paths.get(file);
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(InMemoryRepository.class);
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            InMemoryRepository<Client> newRepo = (InMemoryRepository<Client>) jaxbUnMarshaller.unmarshal(Files.newInputStream(clientFilePath));
            super.setEntities(newRepo.getAll());
        } catch (JAXBException | IOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(Client entity) throws ValidatorException {
        super.add(entity);
        rewriteToFile();
    }

    @Override
    public Optional<Client> get(int id) {
        return super.get(id);
    }

    @Override
    public List<Client> getAll() {
        return super.getAll();
    }

    @Override
    public Optional<Client> update(Client elem) throws ValidatorException {
        Optional<Client> client = super.update(elem);
        if (!client.isPresent()) {
            rewriteToFile();
        }
        return client;
    }

    @Override
    public Optional<Client> delete(int id) {
        Optional<Client> client = super.delete(id);
        client.ifPresent(t -> rewriteToFile());
        return client;
    }


    private void rewriteToFile() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(clientFilePath, StandardOpenOption.TRUNCATE_EXISTING)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(InMemoryRepository.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, bufferedWriter);
        } catch (JAXBException | IOException e) {
            throw new RepositoryException(e);
        }
    }
}
