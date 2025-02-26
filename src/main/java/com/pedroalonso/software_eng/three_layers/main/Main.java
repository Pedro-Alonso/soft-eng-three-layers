/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.software_eng.three_layers.main;

import com.pedroalonso.software_eng.three_layers.controllers.PersonController;
import com.pedroalonso.software_eng.three_layers.entities.Person;
import com.pedroalonso.software_eng.three_layers.utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author pedro
 */
public class Main {

    private static Main instance;
    private static PersonController personController = PersonController.getInstance();

    private Main() {
    }

    public static synchronized Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }

    public static void execute(String[] args) {
        Main program = Main.getInstance();
        program.startProgram();
    }

    private static synchronized void startProgram() {
        ArrayList<Person> currentDatabase = personController.getAll();
        if (currentDatabase.isEmpty()) {
            Utils.seedDatabase();
        }
    }
}
