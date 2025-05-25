package cpl.airline_booking_backend.dao;

import cpl.airline_booking_backend.config.DatabaseConfig;
import cpl.airline_booking_backend.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public void save(Booking booking) throws Exception {
        String sql = "INSERT INTO bookings (user_id, flight_id, booking_date, seat_number, status, seat_class) " +
             "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getFlightId());
            stmt.setString(3, booking.getBookingDate());
            stmt.setString(4, booking.getSeatNumber());
            stmt.setString(5, booking.getStatus());
            stmt.setString(6, booking.getSeatClass());
            stmt.executeUpdate();
        }
    }

    public List<Booking> findAll() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingId(rs.getInt("booking_id"));
                b.setUserId(rs.getInt("user_id"));
                b.setFlightId(rs.getInt("flight_id"));
                b.setBookingDate(rs.getString("booking_date"));
                b.setSeatNumber(rs.getString("seat_number"));
                b.setStatus(rs.getString("status"));
                b.setSeatClass(rs.getString("seat_class"));
                bookings.add(b);
            }
        }

        return bookings;
    }
}
