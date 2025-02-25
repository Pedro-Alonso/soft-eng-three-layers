/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.eng.soft.three.layers.controllers;

import com.pedroalonso.eng.soft.three.layers.database.PersonRepository;
import java.util.ArrayList;
import com.pedroalonso.eng.soft.three.layers.entities.Person;

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
    
    public void create(Person p){
        personRepository.save(p);
    }
}
