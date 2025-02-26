/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.software_eng.three_layers.main;

import com.pedroalonso.software_eng.three_layers.controllers.PersonController;
import com.pedroalonso.software_eng.three_layers.entities.Person;
import com.pedroalonso.software_eng.three_layers.utils.Utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

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

    private void startProgram() {
        ArrayList<Person> currentDatabase = personController.getAll();
        if (currentDatabase.isEmpty()) {
            Utils.seedDatabase();
        }
        System.out.println("====== BEM-VINDO ======");
        menu();
    }

    private void menu() {
        int option;
        String menuOptions = ("""
                                [1] Listar usuários
                                [2] Criar usuário
                                [3] Buscar usuário [em breve]
                                [0] SAIR
                               """);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ESCOLHA SUA AÇÃO");
        System.out.println(menuOptions);
        try {
            option = Integer.parseInt(reader.readLine());
            runOption(option);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listUsers() {
        ArrayList<Person> dataset = personController.getAll();
        System.out.println("== LISTANDO USUÁRIOS ==");
        for (Person p : dataset) {
            String s = String.format("%s - %s - %s - %s", p.getDocument(), p.getName(), p.getEmail(), p.getBirthDate());
            System.out.println(s);
        }
    }

    private void createUser() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name, email, document, birthDateString;
        LocalDate birthDate;
        System.out.println("== CRIANDO USUÁRIO ==");
        try {
            System.out.println("Nome: ");
            name = reader.readLine();
            System.out.println("E-mail: ");
            email = reader.readLine();
            System.out.println("CPF: ");
            document = reader.readLine();
            System.out.println("Data de nascimento (yyyy-mm-dd): ");
            birthDateString = reader.readLine();
            if (!Utils.isLocalDateStringValid(birthDateString)) {
                System.out.println("!!! DADOS INVÁLIDOS !!!");
                menu();
                return;
            }
            birthDate = Utils.getDateFromString(birthDateString);
            Person p = new Person(name, birthDate, email, document);
            personController.create(p);
            System.out.println("USUÁRIO CRIADO COM SUCESSO!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runOption(int option) {

        switch (option) {
            case 0:
                System.exit(option);
            case 1:
                listUsers();
                break;
            case 2:
                createUser();
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA");
                break;
        }
        menu();
    }
}
