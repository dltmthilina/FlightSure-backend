package cpl.airline_booking_backend.dao;

import cpl.airline_booking_backend.config.DatabaseConfig;
import cpl.airline_booking_backend.model.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO {

    public void save(Airport airport) throws Exception {
        String sql = "INSERT INTO airports (code, name, city, country, time_zone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, airport.getCode());
            stmt.setString(2, airport.getName());
            stmt.setString(3, airport.getCity());
            stmt.setString(4, airport.getCountry());
            stmt.setString(5, airport.getTimeZone());

            stmt.executeUpdate();
        }
    }

    public List<Airport> findAll() throws Exception {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT * FROM airports";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Airport a = new Airport();
                a.setAirportId(rs.getInt("airport_id"));
                a.setCode(rs.getString("code"));
                a.setName(rs.getString("name"));
                a.setCity(rs.getString("city"));
                a.setCountry(rs.getString("country"));
                a.setTimeZone(rs.getString("time_zone"));

                airports.add(a);
            }
        }

        return airports;
    }
    
public Airport findByCode(String code) {
    String sql = "SELECT * FROM airport WHERE code = ?";
    
    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, code);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Airport airport = new Airport();
            airport.setAirportId(rs.getInt("airport_id"));
            airport.setCode(rs.getString("code"));
            airport.setName(rs.getString("name"));
            airport.setCity(rs.getString("city"));
            airport.setCountry(rs.getString("country"));
            airport.setTimeZone(rs.getString("time_zone"));
            return airport;
        }
    } catch (Exception e) {
        e.printStackTrace(); // You could also use a logger here
    }

    return null;
}


}
