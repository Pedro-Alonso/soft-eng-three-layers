/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.software_eng.three_layers.main;

import com.pedroalonso.software_eng.three_layers.controllers.PersonController;
import com.pedroalonso.software_eng.three_layers.entities.Person;
import java.util.Date;

/**
 *
 * @author pedro
 */
public class Main {
    
    private static PersonController personController = PersonController.getInstance();
    
    public static void execute(String[] args) {
        Person p1 = new Person("Pedro", new Date(), "pedro@email.com", "12345678-90");
        personController.create(p1);
    }
}
