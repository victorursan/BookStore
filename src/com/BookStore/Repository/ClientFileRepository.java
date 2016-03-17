package com.BookStore.Repository;

import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;

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

                try {
                    super.add(client);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
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
            e.printStackTrace();
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
        return super.update(elem);
    }

    @Override
    public Optional<Client> delete(int id) {
        return super.delete(id);
    }

    private void saveToFile(Client entity) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(clientFilePath, StandardOpenOption.APPEND)) {
            bufferedWriter.write(entity.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rewriteToFile() {
//        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(bookFilePath, StandardOpenOption.TRUNCATE_EXISTING)) {
//            for (Book element: super.getAll()) {
//                bufferedWriter.write(element.toString());
//                bufferedWriter.newLine();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
