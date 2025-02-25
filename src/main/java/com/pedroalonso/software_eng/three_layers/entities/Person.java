/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.software_eng.three_layers.entities;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author pedro
 */
public class Person {

    private UUID id = UUID.randomUUID();
    private String name;
    private Date birthDate;
    private String email;
    private String document;

    public Person(String name, Date birthDate, String email, String document) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.document = document;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", id, document, email, birthDate.toString());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

}
