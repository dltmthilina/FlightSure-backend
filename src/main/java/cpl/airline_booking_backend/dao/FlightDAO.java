package cpl.airline_booking_backend.dao;

import cpl.airline_booking_backend.config.DatabaseConfig;
import cpl.airline_booking_backend.model.Flight;
import cpl.airline_booking_backend.model.Airplane;
import cpl.airline_booking_backend.model.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    public void save(Flight flight) throws Exception {
        String sql = "INSERT INTO flights (flight_id, flight_number, airplane_id, origin_id, destination_id, departure_time, arrival_time, duration, status, economy_seats, business_seats, first_seats, economy_price, business_price, first_price) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, flight.getFlightId());
            stmt.setString(2, flight.getFlightNumber());
            stmt.setString(3, flight.getAirplaneId());
            stmt.setString(4, flight.getOriginId());
            stmt.setString(5, flight.getDestinationId());
            stmt.setString(6, flight.getDepartureTime());
            stmt.setString(7, flight.getArrivalTime());
            stmt.setInt(8, flight.getDuration());
            stmt.setString(9, flight.getStatus());
            stmt.setInt(10, flight.getEconomySeats());
            stmt.setInt(11, flight.getBusinessSeats());
            stmt.setInt(12, flight.getFirstSeats());
            stmt.setBigDecimal(13, flight.getEconomyPrice());
            stmt.setBigDecimal(14, flight.getBusinessPrice());
            stmt.setBigDecimal(15, flight.getFirstPrice());
            stmt.executeUpdate();
        }
    }

    public List<Flight> findAll() throws Exception {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT f.*, " +
                "a.*, " +
                "ao.airport_id AS origin_airport_id, ao.code AS origin_code, ao.name AS origin_name, ao.city AS origin_city, ao.country AS origin_country, ao.time_zone AS origin_time_zone, "
                +
                "ad.airport_id AS dest_airport_id, ad.code AS dest_code, ad.name AS dest_name, ad.city AS dest_city, ad.country AS dest_country, ad.time_zone AS dest_time_zone "
                +
                "FROM flights f " +
                "JOIN airplanes a ON f.airplane_id = a.airplane_id " +
                "LEFT JOIN airports ao ON f.origin_id = ao.airport_id " +
                "LEFT JOIN airports ad ON f.destination_id = ad.airport_id";

        try (Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Flight f = new Flight();
                f.setFlightId(rs.getString("flight_id"));
                f.setFlightNumber(rs.getString("flight_number"));
                f.setAirplaneId(rs.getString("airplane_id"));
                f.setOriginId(rs.getString("origin_id"));
                f.setDestinationId(rs.getString("destination_id"));
                f.setDepartureTime(rs.getString("departure_time"));
                f.setArrivalTime(rs.getString("arrival_time"));
                f.setDuration(rs.getInt("duration"));
                f.setStatus(rs.getString("status"));
                f.setEconomySeats(rs.getInt("economy_seats"));
                f.setBusinessSeats(rs.getInt("business_seats"));
                f.setFirstSeats(rs.getInt("first_seats"));
                f.setEconomyPrice(rs.getBigDecimal("economy_price"));
                f.setBusinessPrice(rs.getBigDecimal("business_price"));
                f.setFirstPrice(rs.getBigDecimal("first_price"));

                Airplane a = new Airplane();
                a.setAirplaneId(rs.getString("airplane_id"));
                a.setRegNumber(rs.getString("reg_number"));
                a.setModel(rs.getString("model"));
                a.setCategory(rs.getString("category"));
                a.setCapacityFirst(rs.getInt("capacity_first"));
                a.setCapacityBusiness(rs.getInt("capacity_business"));
                a.setCapacityEconomy(rs.getInt("capacity_economy"));
                a.setManufacturer(rs.getString("manufacturer"));
                f.setAirplane(a);

                // Origin Airport
                Airport origin = new Airport();
                origin.setAirportId(rs.getInt("origin_airport_id"));
                origin.setCode(rs.getString("origin_code"));
                origin.setName(rs.getString("origin_name"));
                origin.setCity(rs.getString("origin_city"));
                origin.setCountry(rs.getString("origin_country"));
                origin.setTimeZone(rs.getString("origin_time_zone"));
                f.setOrigin(origin);

                Airport dest = new Airport();
                dest.setAirportId(rs.getInt("dest_airport_id"));
                dest.setCode(rs.getString("dest_code"));
                dest.setName(rs.getString("dest_name"));
                dest.setCity(rs.getString("dest_city"));
                dest.setCountry(rs.getString("dest_country"));
                dest.setTimeZone(rs.getString("dest_time_zone"));
                f.setDestination(dest);

                flights.add(f);
            }
        }

        return flights;
    }

    public Flight findById(String flightId) throws Exception {
        String sql = "SELECT f.*, a.* FROM flights f JOIN airplanes a ON f.airplane_id = a.airplane_id WHERE f.flight_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, flightId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Flight f = new Flight();
                    f.setFlightId(rs.getString("flight_id"));
                    f.setFlightNumber(rs.getString("flight_number"));
                    f.setAirplaneId(rs.getString("airplane_id"));
                    f.setOriginId(rs.getString("origin_id"));
                    f.setDestinationId(rs.getString("destination_id"));
                    f.setDepartureTime(rs.getString("departure_time"));
                    f.setArrivalTime(rs.getString("arrival_time"));
                    f.setDuration(rs.getInt("duration"));
                    f.setStatus(rs.getString("status"));
                    f.setEconomySeats(rs.getInt("economy_seats"));
                    f.setBusinessSeats(rs.getInt("business_seats"));
                    f.setFirstSeats(rs.getInt("first_seats"));
                    f.setEconomyPrice(rs.getBigDecimal("economy_price"));
                    f.setBusinessPrice(rs.getBigDecimal("business_price"));
                    f.setFirstPrice(rs.getBigDecimal("first_price"));

                    Airplane airplane = new Airplane();
                    airplane.setAirplaneId(rs.getString("airplane_id"));
                    airplane.setRegNumber(rs.getString("reg_number"));
                    airplane.setModel(rs.getString("model"));
                    airplane.setCategory(rs.getString("category"));
                    airplane.setCapacityFirst(rs.getInt("capacity_first"));
                    airplane.setCapacityBusiness(rs.getInt("capacity_business"));
                    airplane.setCapacityEconomy(rs.getInt("capacity_economy"));
                    airplane.setManufacturer(rs.getString("manufacturer"));

                    f.setAirplane(airplane);

                    return f;
                } else {
                    return null;
                }
            }
        }
    }

    public boolean hasTimeConflict(String airplaneId, String newDepartureTime, String newArrivalTime) throws Exception {
        String sql = "SELECT COUNT(*) FROM flights " +
                "WHERE airplane_id = ? " +
                "AND arrival_time > NOW() " + // Only consider not completed flights
                "AND ((departure_time < ? AND arrival_time > ?) " + // Overlaps with new flight
                "OR (departure_time < ? AND arrival_time > ?))";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, airplaneId);
            stmt.setString(2, newArrivalTime);
            stmt.setString(3, newDepartureTime);
            stmt.setString(4, newArrivalTime);
            stmt.setString(5, newDepartureTime);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}
