/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.eng.soft.three.layers.database;

import com.pedroalonso.eng.soft.three.layers.entities.Person;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class PersonRepository {

    private static PersonRepository instance;
    private static ArrayList<Person> repository;
    private static String databaseName = "personRepo.txt";

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
        try {
            FileReader db = new FileReader(databaseName);
            StringBuilder personRepo = new StringBuilder();
            int ch;
            while ((ch = db.read()) != -1) {
                personRepo.append((char) ch);
            }
            return personRepo.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Person person) {
        this.repository.add(person);
        try {
            String repoData = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
