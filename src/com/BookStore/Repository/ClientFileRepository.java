package com.BookStore.Repository;

import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.IValidator;
import com.BookStore.Model.Validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ClientFileRepository extends InMemoryRepository<Client> {
    private Path clientFilePath;
    private Path purchaseFilePath;

    public ClientFileRepository(IValidator<Client> validator, String clientFile, String purchaseFile) {
        super(validator);
        this.clientFilePath = Paths.get(clientFile);
        this.purchaseFilePath = Paths.get(purchaseFile);
        loadFromFile();
    }

    private void loadFromFile() {
//        try {
//            Files.lines(path).forEach(line -> {
//                List<String> items = Arrays.asList(line.split(","));
//
//                int id = Integer.parseInt(items.get(0));
//                String title = items.get(1);
//                String author = items.get((2));
//                Long ISBN = Long.valueOf(items.get(3));
//                String genre = items.get(4);
//                String publisher = items.get((5));
//                int price = Integer.parseInt(items.get(6));
//                Boolean available = Boolean.parseBoolean(items.get(7));
//
//                Book book = new Book(id, title, author, ISBN, genre, publisher, price, available);
//
//                try {
//                    super.add(book);
//                } catch (ValidatorException e) {
//                    e.printStackTrace();
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void add(Client entity) throws ValidatorException {
        super.add(entity);
        saveToFile(entity);
    }

    private void saveToFile(Client entity) {
//        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(clientFilePath, StandardOpenOption.APPEND)) {
//            bufferedWriter.write(entity.toString());
//            bufferedWriter.newLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
