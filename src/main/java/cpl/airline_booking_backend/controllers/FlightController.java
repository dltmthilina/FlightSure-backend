package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.FlightDAO;
import cpl.airline_booking_backend.model.Flight;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightDAO flightDAO = new FlightDAO();

    @PostMapping
    public ResponseEntity<Map<String, Object>> createFlight(@RequestBody Flight flight) {
        
         Map<String, Object> response = new HashMap<>();
         
        try {
            flightDAO.save(flight);
            
            response.put("success", true);
            response.put("message", "Flight added successfully!");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
        response.put("message", "Error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllFlights() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Flight> flights = flightDAO.findAll();
            response.put("success", true);
           response.put("data", flights);
           return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
        response.put("message", "Error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
