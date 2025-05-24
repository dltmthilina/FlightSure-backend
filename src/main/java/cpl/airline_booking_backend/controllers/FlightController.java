package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.FlightDAO;
import cpl.airline_booking_backend.model.Flight;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightDAO flightDAO = new FlightDAO();

    @PostMapping
    public String createFlight(@RequestBody Flight flight) {
        try {
            flightDAO.save(flight);
            return "Flight added successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        try {
            return flightDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
