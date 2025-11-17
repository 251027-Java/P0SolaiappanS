package org.example.Repository;

import org.example.Flight;

import java.sql.*;
import java.util.Date;

public class PostgreSQLRepository implements IRepository {
    // Fields
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432/airport";
    private static final String Postgre_Username = "postgres";
    private static final String Postgre_Password = "xaR501%=";
    private Connection connection;

    public PostgreSQLRepository() {
        try {
            connection = DriverManager.getConnection(Postgre_URL, Postgre_Username, Postgre_Password);
            try (Statement stmt = connection.createStatement()) {
                String sql = "CREATE SCHEMA IF NOT EXISTS airport;" +
                        "CREATE TABLE IF NOT EXISTS airport.flight (" +
                        "id int PRIMARY KEY," +
                        "flightnumber VARCHAR(25)," +
                        "date TIMESTAMP," +
                        "airlines VARCHAR(50)," +
                        "price FLOAT CHECK (price > 0)" +
                        ");" +
                        "CREATE TABLE IF NOT EXISTS airport.passenger (" +
                        "id int PRIMARY KEY," +
                        "firstname VARCHAR(20)," +
                        "lastname VARCHAR(20)," +
                        "date TIMESTAMP" +
                        ");" +
                        "CREATE TABLE IF NOT EXISTS airport.passenger_flights (" +
                        "passenger_id INT,"+
                        "flight_id INT,"+
                        "PRIMARY KEY (passenger_id, flight_id),"+
                        "FOREIGN KEY (passenger_id) REFERENCES airport.passenger(id)," +
                        "FOREIGN KEY (flight_id) REFERENCES airport.flight(id)" +
                        ");";


                stmt.execute(sql);
                System.out.println("Successful creation of Postgre database with Flight and Passenger Info!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void createFlight() {
        String sqlTableOne = "INSERT INTO airport.flight (id , flightnumber, date, airlines, price) VALUES (?, ?, ?, ?, ?) ON CONFLICT (id) DO NOTHING;";
        String sqlTableTwo = "INSERT INTO airport.passenger (id , firstname, lastname, date) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO NOTHING;";
        String sqlTableThree = "INSERT INTO airport.passenger_flights (passenger_id, flight_id) VALUES (?, ?) ON CONFLICT (passenger_id, flight_id) DO NOTHING;";
        try (PreparedStatement stmt = connection.prepareStatement(sqlTableOne)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "AA242");
            stmt.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            stmt.setString(4, "American Airlines");
            stmt.setDouble(5, 500.23);
            stmt.executeUpdate();
            System.out.println("Flight created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PreparedStatement stmt = connection.prepareStatement(sqlTableTwo)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "John");
            stmt.setString(3, "Johnson");
            stmt.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
            stmt.executeUpdate();
            System.out.println("Person created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PreparedStatement stmt = connection.prepareStatement(sqlTableThree)) {
            stmt.setInt(1, 1);
            stmt.setInt(2, 1);
            stmt.executeUpdate();
            System.out.println("Person FLight junction table created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void bookTicket(Flight flight) {

    }

    @Override
    public void changeTicket(Flight flight) {

    }

    @Override
    public void deleteTicket(int id) {

    }
}