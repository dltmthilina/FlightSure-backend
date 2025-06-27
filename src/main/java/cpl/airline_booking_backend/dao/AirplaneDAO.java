package cpl.airline_booking_backend.dao;

import cpl.airline_booking_backend.config.DatabaseConfig;
import cpl.airline_booking_backend.model.Airplane;
import cpl.airline_booking_backend.model.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirplaneDAO {

    public void save(Airplane airplane) throws Exception {
        String sql = "INSERT INTO airplanes (reg_number, model, category, capacity_first, capacity_business, capacity_economy, initial_location, manufacturer) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, airplane.getRegNumber());
            stmt.setString(2, airplane.getModel());
            stmt.setString(3, airplane.getCategory());
            stmt.setInt(4, airplane.getCapacityFirst());
            stmt.setInt(5, airplane.getCapacityBusiness());
            stmt.setInt(6, airplane.getCapacityEconomy());
            stmt.setString(7, airplane.getInitialLocationId());
            stmt.setString(8, airplane.getManufacturer());

            stmt.executeUpdate();
        }
    }

    public void update(Airplane airplane) throws Exception {
        // filepath: src/main/java/cpl/airline_booking_backend/dao/AirplaneDAO.java
        String sql = "UPDATE airplanes SET reg_number = ?, model = ?, category = ?, capacity_first = ?, capacity_business = ?, capacity_economy = ?, initial_location = ?, manufacturer = ? WHERE airplane_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, airplane.getRegNumber());
            stmt.setString(2, airplane.getModel());
            stmt.setString(3, airplane.getCategory());
            stmt.setInt(4, airplane.getCapacityFirst());
            stmt.setInt(5, airplane.getCapacityBusiness());
            stmt.setInt(6, airplane.getCapacityEconomy());
            stmt.setString(7, airplane.getInitialLocationId());
            stmt.setString(8, airplane.getManufacturer());
            stmt.setInt(9, airplane.getAirplaneId());
            stmt.executeUpdate();
        }
    }

    public List<Airplane> findAll() throws Exception {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "SELECT a.*, ap.* FROM airplanes a LEFT JOIN airports ap ON a.initial_location = ap.airport_id";

        try (Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Airplane a = new Airplane();
                a.setAirplaneId(rs.getInt("airplane_id"));
                a.setRegNumber(rs.getString("reg_number"));
                a.setModel(rs.getString("model"));
                a.setCategory(rs.getString("category"));
                a.setCapacityFirst(rs.getInt("capacity_first"));
                a.setCapacityBusiness(rs.getInt("capacity_business"));
                a.setCapacityEconomy(rs.getInt("capacity_economy"));
                a.setInitialLocationId(rs.getString("initial_location"));
                a.setManufacturer(rs.getString("manufacturer"));

                int airportId = rs.getInt("ap.airport_id");

                if (!rs.wasNull()) {
                    Airport airport = new Airport();
                    airport.setAirportId(airportId);
                    airport.setName(rs.getString("name"));
                    airport.setCity(rs.getString("city"));
                    airport.setCountry(rs.getString("country"));
                    airport.setCode(rs.getString("code"));
                    airport.setTimeZone(rs.getString("time_zone"));

                    a.setInitialLocation(airport);

                }
                airplanes.add(a);
            }
        }

        return airplanes;
    }

    public Optional<Airplane> findByRegNumber(String regNumber) throws Exception {
        String sql = "SELECT * FROM airplanes WHERE reg_number = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, regNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Airplane a = new Airplane();
                    a.setAirplaneId(rs.getInt("airplane_id"));
                    a.setModel(rs.getString("model"));
                    a.setRegNumber(rs.getString("reg_number"));
                    a.setCategory(rs.getString("category"));
                    a.setCapacityFirst(rs.getInt("capacity_first"));
                    a.setCapacityBusiness(rs.getInt("capacity_business"));
                    a.setCapacityEconomy(rs.getInt("capacity_economy"));
                    a.setInitialLocationId(rs.getString("initial_location"));
                    a.setManufacturer(rs.getString("manufacturer"));

                    return Optional.of(a);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public Optional<Airplane> findById(int airplaneId) throws Exception {
        String sql = "SELECT * FROM airplanes WHERE airplane_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, airplaneId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Airplane a = new Airplane();
                    a.setAirplaneId(rs.getInt("airplane_id"));
                    a.setModel(rs.getString("model"));
                    a.setRegNumber(rs.getString("reg_number"));
                    a.setCategory(rs.getString("category"));
                    a.setCapacityFirst(rs.getInt("capacity_first"));
                    a.setCapacityBusiness(rs.getInt("capacity_business"));
                    a.setCapacityEconomy(rs.getInt("capacity_economy"));
                    a.setInitialLocationId(rs.getString("initial_location"));
                    a.setManufacturer(rs.getString("manufacturer"));

                    return Optional.of(a);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public Airport getCurrentLocation(int airplaneId) throws Exception {
        String sql = "SELECT ap.* FROM flights f " +
                "JOIN airports ap ON f.destination_id = ap.airport_id " +
                "WHERE f.airplane_id = ? AND f.arrival_time <= NOW() " +
                "ORDER BY f.arrival_time DESC LIMIT 1";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, airplaneId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Airport a = new Airport();
                    a.setAirportId(rs.getInt("airport_id"));
                    a.setCode(rs.getString("code"));
                    a.setName(rs.getString("name"));
                    a.setCity(rs.getString("city"));
                    a.setCountry(rs.getString("country"));
                    a.setTimeZone(rs.getString("time_zone"));

                    return a;
                }
            }
        }

        Optional<Airplane> airplaneOpt = findById(airplaneId);
        if (airplaneOpt.isPresent()) {
            Airplane airplane = airplaneOpt.get();
            String initialLocationId = airplane.getInitialLocationId();
            String airportSql = "SELECT * FROM airports WHERE airport_id = ?";
            try (Connection conn = DatabaseConfig.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(airportSql)) {
                stmt.setString(1, initialLocationId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Airport a = new Airport();
                        a.setAirportId(rs.getInt("airport_id"));
                        a.setCode(rs.getString("code"));
                        a.setName(rs.getString("name"));
                        a.setCity(rs.getString("city"));
                        a.setCountry(rs.getString("country"));
                        a.setTimeZone(rs.getString("time_zone"));
                        return a;
                    }
                }
            }
        }
        return null;
    }

    public String getLocationAtTime(int airplaneId, String departureTime) throws Exception {
        // Find the latest flight that ends before the new departure time
        String sql = "SELECT destination FROM flights " +
                "WHERE airplane_id = ? AND arrival_time <= ? " +
                "ORDER BY arrival_time DESC LIMIT 1";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, airplaneId);
            stmt.setString(2, departureTime);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("destination");
                }
            }
        }
        // If no flights, return initial location
        Optional<Airplane> airplaneOpt = findById(airplaneId);
        return airplaneOpt.map(Airplane::getInitialLocationId).orElse(null);
    }

    
}
