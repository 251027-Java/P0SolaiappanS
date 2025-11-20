package org.example;

import org.example.Repository.*; // explicit import
import org.example.*;

import java.util.Date;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        IRepository repo = new PostgreSQLRepository();
        Flight myFlight = new Flight(1, "AA242",  new java.sql.Timestamp(new Date().getTime()), "American Airlines", 500.23);
        repo.createFlight(myFlight);
        Scanner readPersonInformation = new Scanner(System.in);
        String firstName = "";
        String lastName = "";
        String flightId = "";
        boolean continueInput = true;
        String keepBooking = "";


        System.out.println("What is your first name?");
        firstName = readPersonInformation.next();
        System.out.println("What is your last name?");
        lastName = readPersonInformation.next();
        Passenger you = new Passenger(2, firstName, lastName, new Date());
        while(continueInput) {
            System.out.println("Choose a flight you want to book.");
            flightId = readPersonInformation.next();
            System.out.println("Do you want to book any more flights? Y/N");
            keepBooking = readPersonInformation.next();
            if (keepBooking.equals("N")) continueInput = false;
        }


        System.out.println("New passenger: "+you.toString());
        repo.insertPassenger(you);
    }
}
