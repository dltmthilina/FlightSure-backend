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
            String currentLocation = flightDAO.getCurrentLocation(flight.getAirplaneId());

            if (currentLocation == null) {
                currentLocation = flight.getAirplane().getInitialLocation();
            }

            if (!currentLocation.equals(flight.getOrigin())) {
                response.put("success", false);
                response.put("message", "Airplane is not at the origin location");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
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

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateFlight(@PathVariable int id, @RequestBody Flight flight) {

        Map<String, Object> response = new HashMap<>();
        if (flight == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Flight existingFlight = null;
        try {
            existingFlight = flightDAO.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        if (existingFlight == null) {
            response.put("success", false);
            response.put("message", "Flight not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setOrigin(flight.getOrigin());
        existingFlight.setDestination(flight.getDestination());
        existingFlight.setDepartureTime(flight.getDepartureTime());
        existingFlight.setArrivalTime(flight.getArrivalTime());
        existingFlight.setAirplaneId(flight.getAirplaneId());

        try {
            flightDAO.save(existingFlight);
            response.put("success", true);
            response.put("message", "Flight updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
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
