package cpl.airline_booking_backend.dao;

import cpl.airline_booking_backend.config.DatabaseConfig;
import cpl.airline_booking_backend.model.Airplane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirplaneDAO {

    public void save(Airplane airplane) throws Exception {
        String sql = "INSERT INTO airplanes (reg_number, model, category, capacity_first, capacity_business, capacity_economy, initial_location, manufacturer) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

           
            stmt.setString(1, airplane.getRegNumber());
            stmt.setString(2, airplane.getModel());
            stmt.setString(3, airplane.getCategory());
            stmt.setInt(4, airplane.getCapacityFirst());
            stmt.setInt(5, airplane.getCapacityBusiness());
            stmt.setInt(6, airplane.getCapacityEconomy());
            stmt.setString(7, airplane.getInitialLocation());
            stmt.setString(8, airplane.getManufacturer());

            stmt.executeUpdate();
        }
    }

    public List<Airplane> findAll() throws Exception {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "SELECT * FROM airplanes";

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
                a.setInitialLocation(rs.getString("initial_location"));
                a.setManufacturer(rs.getString("manufacturer"));

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
                    a.setInitialLocation(rs.getString("initial_location"));
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
                    a.setInitialLocation(rs.getString("initial_location"));
                    a.setManufacturer(rs.getString("manufacturer"));

                    return Optional.of(a);
                } else {
                    return Optional.empty();
                }
            }
        }
     }
}
