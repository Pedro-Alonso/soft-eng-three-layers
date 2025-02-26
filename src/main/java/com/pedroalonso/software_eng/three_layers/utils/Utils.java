/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedroalonso.software_eng.three_layers.utils;

import com.pedroalonso.software_eng.three_layers.controllers.PersonController;
import com.pedroalonso.software_eng.three_layers.entities.Person;
import java.time.LocalDate;
import java.time.YearMonth;
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

    private static int[] getNumbersFromLocalDateString(String dateString) {
        String[] splittedDate = dateString.split("-");
        String yearString,
                monthString,
                dayString;
        int year,
                month,
                day;
        int[] dateNumbers = new int[3];
        yearString = splittedDate[0];
        monthString = splittedDate[1];
        dayString = splittedDate[2];
        year = Integer.parseInt(yearString);
        month = Integer.parseInt(monthString);
        day = Integer.parseInt(dayString);
        dateNumbers[0] = year;
        dateNumbers[1] = month;
        dateNumbers[2] = day;
        return dateNumbers;
    }

    public static boolean isLocalDateStringValid(String dateString) {
        LocalDate date,
                today = LocalDate.now();
        YearMonth yearMonth;
        int maximumYear = today.getYear() - 18,
                minimumYear = LocalDate.MIN.getYear(),
                year,
                month,
                day;
        int[] dateNumbers = new int[3];
        String[] splittedDate;
        if (dateString.length() != 10) {
            System.out.println("TAMANHO INVÁLIDO");
            return false;
        }
        splittedDate = dateString.split("-");
        if (splittedDate[0].length() != 4
                || splittedDate[1].length() != 2
                || splittedDate[2].length() != 2) {

            System.out.println("DATAS INVÁLIDAS");
            return false;
        }
        dateNumbers = getNumbersFromLocalDateString(dateString);
        year = dateNumbers[0];
        month = dateNumbers[1];
        day = dateNumbers[2];
        if (year < minimumYear
                || year > maximumYear
                || month < 1
                || month > 12) {

            System.out.println("INTERVALO INVÁLIDO");
            return false;
        }
        yearMonth = YearMonth.of(year, month);
        if (!yearMonth.isValidDay(day)) {
            System.out.println(String.format("DIA: %s, MES: %s, ANO: %s", day, month, year));
            System.out.println("DIA INVÁLIDO");
            return false;
        }
        return true;
    }

    public static LocalDate getDateFromString(String dateString) {
        int[] dateNumbers = new int[3];
        int year,
                month,
                day;
        LocalDate date;
        if (!isLocalDateStringValid(dateString)) {
            return null;
        }
        dateNumbers = getNumbersFromLocalDateString(dateString);
        year = dateNumbers[0];
        month = dateNumbers[1];
        day = dateNumbers[2];
        date = LocalDate.of(year, month, day);
        return date;
    }

    private LocalDate randomDateGenerator() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().minusYears(18).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }
}
