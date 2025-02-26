/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.software_eng.three_layers.utils;

import com.pedroalonso.software_eng.three_layers.controllers.PersonController;
import com.pedroalonso.software_eng.three_layers.entities.Person;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author pedro
 */
public class Utils {

    private static Utils instance;
    private static PersonController personController;

    private Utils() {
        personController = PersonController.getInstance();
    }

    public static synchronized Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public static void seedDatabase() {
        String[] names = {
            "Lucas Almeida", "Mariana Costa", "João Pereira", "Ana Souza", "Guilherme Rocha",
            "Camila Fernandes", "Pedro Santos", "Fernanda Oliveira", "Rafael Lima", "Juliana Mendes",
            "Carlos Eduardo", "Beatriz Figueiredo", "Mateus Correia", "Larissa Martins", "Thiago Nogueira",
            "Aline Ribeiro", "Vinícius Carvalho", "Isabela Duarte", "Fábio Teixeira", "Natália Gonçalves",
            "Daniel Moreira", "Tatiane Moura", "Rodrigo Antunes", "Bianca Barros", "Leandro Ferreira",
            "Gabriela Pires", "César Amaral", "Bruna Lopes", "Anderson Xavier", "Patrícia Silveira",
            "Eduardo Tavares", "Letícia Brito", "Renato Maciel", "Caroline Magalhães", "Felipe Moraes",
            "Amanda Neves", "Hugo Monteiro", "Raquel Nascimento", "Leonardo Vieira", "Vanessa Sales",
            "Wesley Aragão", "Cristiane Pacheco", "Alexandre Barreto", "Tatiana Cunha", "Fernando Viana",
            "Deborah Coelho", "Gustavo Rezende", "Luciana Azevedo", "Ricardo Prado", "Érica Souza"
        };

        String[] documents = new String[50];
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            documents[i] = String.format("%03d.%03d.%03d-%02d",
                    rand.nextInt(1000),
                    rand.nextInt(1000),
                    rand.nextInt(1000),
                    rand.nextInt(100));
        }
        for (int i = 0; i < 50; i++) {
            String email = names[i].toLowerCase().replace(" ", ".") + "@example.com";
            Utils u = Utils.getInstance();
            Person p = new Person(names[i], u.randomDateGenerator(), email, documents[i]);
            personController.create(p);
        }
    }

    private LocalDate randomDateGenerator() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().minusYears(18).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }
}
