package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.BookingDAO;
import cpl.airline_booking_backend.model.Booking;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingDAO bookingDAO = new BookingDAO();

    @PostMapping
    public String createBooking(@RequestBody Booking booking) {
        try {
            bookingDAO.save(booking);
            return "Booking created successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        try {
            return bookingDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
