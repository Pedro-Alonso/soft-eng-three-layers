/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.software_eng.three_layers.database;

import com.pedroalonso.software_eng.three_layers.entities.Person;
import com.pedroalonso.software_eng.three_layers.mappers.PersonMapper;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author pedro
 */
public class PersonRepository {

    private static PersonRepository instance;
    private static PersonMapper mapper;
    private static ArrayList<Person> repository;
    private static String databaseName = "personRepo.txt";

    private PersonRepository() throws IOException {
        mapper = PersonMapper.getInstance();
        String fileData = readFile();
        repository = fileData.isBlank() ? new ArrayList<Person>() : mapper.manyPlainToManyInstance(readFile());
    }

    public static synchronized PersonRepository getInstance() {
        if (instance == null) {
            try {
                instance = new PersonRepository();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private String readFile() throws IOException {

        StringBuilder personRepo = new StringBuilder();
        FileReader db = new FileReader(databaseName);
        try {
            int ch;
            while ((ch = db.read()) != -1) {
                personRepo.append((char) ch);
            }
            personRepo.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return personRepo.toString();
    }

    private void writeFile(String str) throws IOException {
        FileWriter db = new FileWriter(databaseName);
        try {
            db.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void save(Person person) {
        this.repository.add(person);
        try {
            String repoData = readFile();
            if (repoData.length() > 0) {
                repoData = repoData.concat(";");
            }
            repoData = repoData.concat(person.toString());
            writeFile(repoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Person> getById(UUID id) {
        return repository.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }
}
