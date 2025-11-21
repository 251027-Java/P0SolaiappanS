package org.example.Repository;

import org.example.Flight;
import org.example.Passenger;

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
                stmt.execute("CREATE SCHEMA IF NOT EXISTS airport;");
                stmt.execute("CREATE TABLE IF NOT EXISTS airport.flight (" +
                        "id SERIAL PRIMARY KEY," +
                        "flightnumber VARCHAR(25)," +
                        "date TIMESTAMP," +
                        "airlines VARCHAR(50)," +
                        "price FLOAT CHECK (price > 0)" +
                        ");");
                stmt.execute("CREATE TABLE IF NOT EXISTS airport.passenger (" +
                        "id SERIAL PRIMARY KEY," +
                        "firstname VARCHAR(20)," +
                        "lastname VARCHAR(20)," +
                        "date TIMESTAMP" +
                        ");");

                stmt.execute("CREATE TABLE IF NOT EXISTS airport.passenger_flights (" +
                        "passenger_id INT," +
                        "flight_id INT," +
                        "PRIMARY KEY (passenger_id, flight_id)," +
                        "FOREIGN KEY (passenger_id) REFERENCES airport.passenger(id)," +
                        "FOREIGN KEY (flight_id) REFERENCES airport.flight(id)" +
                        ");");


                System.out.println("Successful creation of Postgre database with Flight and Passenger Info (all three tables including junction table)!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //@Override
    public int createFlight(Flight flight) {
        int flightId = 0;
        String sqlTableOne = "INSERT INTO airport.flight (flightnumber, date, airlines, price) VALUES (?, ?, ?, ?) RETURNING id; ";

        try (PreparedStatement stmt = connection.prepareStatement(sqlTableOne, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, flight.getFlightNumber());
            stmt.setTimestamp(2, (Timestamp) flight.getDate());
            stmt.setString(3, flight.getAirlines());
            stmt.setDouble(4, flight.getPrice());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                flightId = rs.getInt(1);
                // ADD THIS:
                flight.setId(flightId);
            }
            System.out.println("Flight created successfully!");
            toString();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return flightId;
    }

    @Override
    public int insertPassenger(Passenger passenger) {
        String sql = "INSERT INTO airport.passenger (firstname, lastname, date) VALUES ( ?, ?, ?) RETURNING id;";
        int passengerId = 0;

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, passenger.getFirstName());
            stmt.setString(2, passenger.getLastName());
            stmt.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                passengerId = rs.getInt(1);

                passenger.setId(passengerId);
            }
            System.out.println("Passenger created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return passengerId;
    }

    public void insertPassengerFlight(int passengerId, int flightId) {
        String sql = "INSERT INTO airport.passenger_flights (passenger_id, flight_id) " +
                "VALUES (?, ?) ON CONFLICT (passenger_id, flight_id) DO NOTHING;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, passengerId);
            stmt.setInt(2, flightId);
            stmt.executeUpdate();
            System.out.println("Passenger-Flight junction table updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}