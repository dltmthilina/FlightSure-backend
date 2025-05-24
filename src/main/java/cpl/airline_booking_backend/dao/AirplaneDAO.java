package cpl.airline_booking_backend.dao;

import cpl.airline_booking_backend.config.DatabaseConfig;
import cpl.airline_booking_backend.model.Airplane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO {

    public void save(Airplane airplane) throws Exception {
        String sql = "INSERT INTO airplanes (model, category, capacity_first, capacity_business, capacity_economy, manufacturer) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, airplane.getModel());
            stmt.setString(2, airplane.getCategory());
            stmt.setInt(3, airplane.getCapacityFirst());
            stmt.setInt(4, airplane.getCapacityBusiness());
            stmt.setInt(5, airplane.getCapacityEconomy());
            stmt.setString(6, airplane.getManufacturer());

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
                a.setModel(rs.getString("model"));
                a.setCategory(rs.getString("category"));
                a.setCapacityFirst(rs.getInt("capacity_first"));
                a.setCapacityBusiness(rs.getInt("capacity_business"));
                a.setCapacityEconomy(rs.getInt("capacity_economy"));
                a.setManufacturer(rs.getString("manufacturer"));

                airplanes.add(a);
            }
        }

        return airplanes;
    }
}
