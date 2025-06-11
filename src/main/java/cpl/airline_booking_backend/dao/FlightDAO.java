package cpl.airline_booking_backend.dao;

import cpl.airline_booking_backend.config.DatabaseConfig;
import cpl.airline_booking_backend.model.Flight;
import cpl.airline_booking_backend.model.Airplane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    public void save(Flight flight) throws Exception {
        String sql = "INSERT INTO flights (flight_number, origin, destination, departure_time, arrival_time, airplane_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, flight.getFlightNumber());
            stmt.setString(2, flight.getOrigin());
            stmt.setString(3, flight.getDestination());
            stmt.setString(4, flight.getDepartureTime());
            stmt.setString(5, flight.getArrivalTime());
            stmt.setInt(6, flight.getAirplaneId());

            stmt.executeUpdate();
        }
    }

    public List<Flight> findAll() throws Exception {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT f.*, a.*" + "FROM flights f JOIN airplanes a ON f.airplane_id = a.airplane_id";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Flight f = new Flight();
                f.setFlightId(rs.getInt("flight_id"));
                f.setFlightNumber(rs.getString("flight_number"));
                f.setOrigin(rs.getString("origin"));
                f.setDestination(rs.getString("destination"));
                f.setDepartureTime(rs.getString("departure_time"));
                f.setArrivalTime(rs.getString("arrival_time"));
                f.setAirplaneId(rs.getInt("airplane_id"));
                
                Airplane a = new Airplane();
                a.setAirplaneId(rs.getInt("airplane_id"));
                a.setRegNumber(rs.getString("reg_number"));
                a.setModel(rs.getString("model"));
                a.setCategory(rs.getString("category"));
                a.setCapacityFirst(rs.getInt("capacity_first"));
                a.setCapacityBusiness(rs.getInt("capacity_business"));
                a.setCapacityEconomy(rs.getInt("capacity_economy"));
                a.setManufacturer(rs.getString("manufacturer"));
                
                f.setAirplane(a);

                flights.add(f);
            }
        }

        return flights;
    }
}
