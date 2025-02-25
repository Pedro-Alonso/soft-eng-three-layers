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
        try {
            FileReader db = new FileReader(databaseName);
            int ch;
            while ((ch = db.read()) != -1) {
                personRepo.append((char) ch);
            }
            personRepo.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personRepo.toString();
    }

    private void writeFile(String str) throws IOException {
        try {
            FileWriter db = new FileWriter(databaseName);
            db.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Person person) {
        this.repository.add(person);
        try {
            String repoData = readFile();
            repoData.concat(repoData + ";" + person.toString());
            writeFile(repoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
