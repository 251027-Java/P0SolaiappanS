package org.example.Repository;

import org.example.Flight;

import java.sql.*;

public class FlightSQL {
    // Fields
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432/airport";
    private static final String Postgre_Username = "postgres";
    private static final String Postgre_Password = "xaR501%=";
    private Connection connection;

    public FlightSQL() {
        try {
            connection = DriverManager.getConnection(Postgre_URL, Postgre_Username, Postgre_Password);
            try (Statement stmt = connection.createStatement()) {
                String sql = "CREATE SCHEMA IF NOT EXISTS Airport;" +
                        "CREATE TABLE IF NOT EXISTS Airport.FlightInformation (" +
                        "flightnumber VARCHAR(25) PRIMARY KEY," +
                        "date TIMESTAMP NOT NULL," +
                        "airlines VARCHAR(50) NOT NULL," +
                        "price FLOAT CHECK (price > 0)" +
                        ");";

                stmt.execute(sql);
                System.out.println("Successful creation of Postgre database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //@Override
    public void createFlight(Flight flight) {
        String sql = "INSERT INTO ExpenseReport.Expenses (id , date, price, merchant) VALUES ( ?, ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, flight.getFlightNumber());
            stmt.setDate(2, new java.sql.Date(flight.getDate().getTime()));
            stmt.setString(3, flight.getAirlines());
            stmt.setDouble(4, flight.getPrice());
            stmt.executeUpdate();
            System.out.println("Expense created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}