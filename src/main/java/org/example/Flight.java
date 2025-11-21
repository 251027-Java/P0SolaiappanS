package org.example;

import java.util.Date;

public class Flight {
    // Fields
    private int id;
    private String flightNumber;
    private Date flightDate;
    private String airlines;
    private double price;
    private String destination;

    // Constructor
    public Flight(String flightNumber, Date date, String airlines, double price, String destination) {
        this.flightNumber = flightNumber;
        flightDate = date;
        this.airlines = airlines;
        this.price = price;
        this.destination=destination;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return this.id; }
    public String getFlightNumber() { return this.flightNumber; }
    public Date getDate() { return flightDate; }
    public String getAirlines() { return this.airlines; }
    public double getPrice() { return this.price; }
    public String getDestination() { return this.destination; }


    public String toString() {
        return "Flight [id= " + this.id + ", flightNumber= " + this.flightNumber + ", date= " + flightDate + ", airlines= " + this.airlines + ", price= " + this.price +"]";
    }
}