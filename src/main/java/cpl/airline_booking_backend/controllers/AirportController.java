package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.AirportDAO;
import cpl.airline_booking_backend.model.Airport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportDAO airportDAO = new AirportDAO();

    @PostMapping
    public String addAirport(@RequestBody Airport airport) {
        try {
            airportDAO.save(airport);
            return "Airport added successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Airport> getAllAirports() {
        try {
            return airportDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
