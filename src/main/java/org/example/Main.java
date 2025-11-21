package org.example;

import org.example.Repository.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        IRepository repo = new PostgreSQLRepository();

        List<Flight> flights = repo.getAllFlights();

        Scanner readPersonInformation = new Scanner(System.in);
        String firstName = "";
        String lastName = "";
        boolean continueInput = true;
        String keepBooking = "";

        while (true) {
            System.out.println("What is your first name?");
            firstName = readPersonInformation.nextLine();

            if (firstName.matches("[A-Za-z ]+")) {
                break;
            }

            System.out.println("Invalid firstName. Please enter letters only.");
        }

        while (true) {
            System.out.println("What is your last name?");
            lastName = readPersonInformation.nextLine();

            if (lastName.matches("[A-Za-z ]+")) {
                break;
            }

            System.out.println("Invalid lastName. Please enter letters only.");
        }

        Passenger you = new Passenger(firstName, lastName, new Date());
        int generatedPassengerId = repo.insertPassenger(you);

        List<Integer> bookedFlightIds = new ArrayList<>();

        while (continueInput) {
            System.out.println("Choose a flight you want to book.");
            for (Flight f : flights) {
                    System.out.println(f.getId() + ": " + f.getFlightNumber() + " - " + f.getDate()+ " - " + f.getAirlines() + " - " +f.getDestination());
            }
            int chosenFlightId = 0;
            while (true) {
                System.out.print("Enter a valid flight ID: ");
                String input = readPersonInformation.next();

                if (!input.matches("\\d+")) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                chosenFlightId = Integer.parseInt(input);
                if (!repo.flightExists(chosenFlightId)) {
                    System.out.println("Flight ID not found. Try again.");
                    continue;
                }

                if (bookedFlightIds.contains(chosenFlightId)) {
                    System.out.println("You already booked this flight. Choose another one.");
                    continue;
                }

                break;
            }

            repo.insertPassengerFlight(generatedPassengerId, chosenFlightId);
            bookedFlightIds.add(chosenFlightId);

            boolean validInput = false;
            while (!validInput) {
                System.out.println("Do you want to book any more flights? Y/N");
                keepBooking = readPersonInformation.next();

                if (keepBooking.equals("Y")) {
                    validInput = true;
                } else if (keepBooking.equals("N")) {
                    continueInput = false;
                    validInput = true;
                    System.out.println("Thank you! Your bookings are complete.");
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }
        }


    }
}
