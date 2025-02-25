/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.eng.soft.three.layers.database;

import com.pedroalonso.eng.soft.three.layers.entities.Person;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class PersonRepository {

    private static PersonRepository instance;
    private static ArrayList<Person> repository;

    private PersonRepository() {
        repository = new ArrayList<Person>();
    }

    public static synchronized PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
        }
        return instance;
    }

    public void save(Person person) {
        this.repository.add(person);
    }
}
