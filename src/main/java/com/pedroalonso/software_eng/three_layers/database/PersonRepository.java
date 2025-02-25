/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.software_eng.three_layers.database;

import com.pedroalonso.software_eng.three_layers.entities.Person;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class PersonRepository {

    private static PersonRepository instance;
    private static ArrayList<Person> repository;
    private static String databaseName = "personRepo.json";

    private PersonRepository() {
        repository = new ArrayList<Person>();
    }

    public static synchronized PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
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
}
