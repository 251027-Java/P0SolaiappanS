package org.example;

import java.util.Date;

public class Passenger {
    // Fields
    private int id;
    private String firstName;
    private String lastName;
    private Date flightDate;

    // Constructor
    public Passenger(int id, String firstName, String lastName, Date date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        flightDate = date;
    }

    public int getId() { return this.id; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public Date getDate() { return flightDate; }

}