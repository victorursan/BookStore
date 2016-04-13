package com.BookStore.Repository.FileRepository;

import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;
import com.BookStore.Repository.Exceptions.RepositoryException;
import com.BookStore.Repository.IRepository;
import com.BookStore.Repository.InMemoryRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientFileRepository extends InMemoryRepository<Client> {
    private Path clientFilePath;
    private Path purchaseFilePath;
    private IRepository<Book> bookRepo;

    public ClientFileRepository(IValidator<Client> validator, String clientFile, String purchaseFile, IRepository<Book> bookRepo) {
        super(validator);
        this.clientFilePath = Paths.get(clientFile);
        this.purchaseFilePath = Paths.get(purchaseFile);
        this.bookRepo = bookRepo;
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            Files.lines(clientFilePath).forEach(line -> {
                List<String> items = Arrays.asList(line.split(", ")).stream()
                        .map(String::trim)
                        .collect(Collectors.toList());
                int id = Integer.parseInt(items.get(0));
                String firstName = items.get(1);
                String lastName = items.get((2));

                Client client = new Client(id, firstName, lastName);
                super.add(client);
            });
            Files.lines(purchaseFilePath).forEach(line -> {
                List<String> items = Arrays.asList(line.split(", ")).stream()
                        .map(String::trim)
                        .collect(Collectors.toList());
                int clientId = Integer.parseInt(items.get(0));
                int bookId = Integer.parseInt(items.get(1));
                super.get(clientId).ifPresent(client -> bookRepo.get(bookId).ifPresent(client::buyBook));
            });
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void add(Client entity) throws ValidatorException {
        super.add(entity);
        saveToFile(entity);
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
        return super.update(elem);
    }

    @Override
    public Optional<Client> delete(int id) {
        Optional<Client> client = super.delete(id);
        client.ifPresent(t -> rewriteToFile());
        return client;
    }

    private void saveToFile(Client client) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(clientFilePath, StandardOpenOption.APPEND)) {
            bufferedWriter.write(String.format("%d, %s, %s\n", client.getId(), client.getFirstName(), client.getLastName()));
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private void rewriteToFile() {
        try (BufferedWriter clientBuffer = Files.newBufferedWriter(clientFilePath, StandardOpenOption.TRUNCATE_EXISTING);
             BufferedWriter purchaseBuffer = Files.newBufferedWriter(purchaseFilePath, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Client client : super.getAll()) {
                String clientStr = String.format("%d, %s, %s\n", client.getId(), client.getFirstName(), client.getLastName());
                clientBuffer.write(clientStr);
                for (Book book : client.getBooks()) {
                    String purchaseStr = String.format("%d, %d\n", client.getId(), book.getId());
                    purchaseBuffer.write(purchaseStr);
                }
            }
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
