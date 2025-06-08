package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.AirportDAO;
import cpl.airline_booking_backend.model.Airport;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportDAO airportDAO = new AirportDAO();

    @PostMapping
    public ResponseEntity<Map<String, Object>> addAirport(@RequestBody Airport airport) {
    Map<String, Object> response = new HashMap<>();

    try {
        Airport existingAirport = airportDAO.findByCode(airport.getCode());
        System.out.print(existingAirport);
        if (existingAirport != null) {
            response.put("success", false);
            response.put("message", "Airport code already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        airportDAO.save(airport);
        response.put("success", true);
        response.put("message", "Airport added successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    } catch (Exception e) {
        e.printStackTrace();
        response.put("success", false);
        response.put("message", "Error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
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
