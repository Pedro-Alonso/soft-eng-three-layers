/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.software_eng.three_layers.controllers;

import com.pedroalonso.software_eng.three_layers.database.PersonRepository;
import java.util.ArrayList;
import com.pedroalonso.software_eng.three_layers.entities.Person;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author pedro
 */
public class PersonController {

    private static PersonController instance;
    private static PersonRepository personRepository;

    private PersonController() {
        personRepository = PersonRepository.getInstance();
    }

    public static synchronized PersonController getInstance() {
        if (instance == null) {
            instance = new PersonController();
        }

        return instance;
    }

    public void create(Person p) {
        personRepository.save(p);
    }

    public Person getById(UUID id) {
        Optional<Person> nullishPerson = personRepository.getById(id);
        return nullishPerson.orElse(null);
    }
    
    public ArrayList<Person> getAll(){
        return personRepository.getAll();
    }
}
