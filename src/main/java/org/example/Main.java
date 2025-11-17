package org.example;

import org.example.Repository.PostgreSQLRepository;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Scanner readPersonInformation = new Scanner(System.in);

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        PostgreSQLRepository flight = new PostgreSQLRepository();
        flight.createFlight();
        System.out.println("What is your first name?");
        System.out.println("What is your last name?");

    }
}
