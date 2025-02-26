/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.software_eng.three_layers.mappers;

import com.pedroalonso.software_eng.three_layers.entities.Person;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class PersonMapper {

    private static PersonMapper instance;

    private PersonMapper() {
    }

    public static synchronized PersonMapper getInstance() {
        if (instance == null) {
            instance = new PersonMapper();
        }
        return instance;
    }

    public Person plainToInstance(String plainPerson) {
        String[] fields = plainPerson.split(",");
        String id, name, email, document;
        LocalDate birthDate;
        id = fields[0];
        document = fields[1];
        name = fields[2];
        email = fields[3];
        birthDate = LocalDate.parse(fields[4]);
        Person p = new Person(id, name, birthDate, email, document);
        return p;
    }

    public ArrayList<Person> manyPlainToManyInstance(String plainPersonArray) {
        String[] personStringArray = plainPersonArray.split(";");
        ArrayList personInstanceArray = new ArrayList<Person>();
        for (int i = 0; i < personStringArray.length; i++) {
            Person p = plainToInstance(personStringArray[i]);
            personInstanceArray.add(p);
        }
        return personInstanceArray;
    }
}
