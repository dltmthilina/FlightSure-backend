package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.AirplaneDAO;
import cpl.airline_booking_backend.model.Airplane;
import cpl.airline_booking_backend.model.Airport;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

    private final AirplaneDAO airplaneDAO = new AirplaneDAO();

    @PostMapping
    public ResponseEntity<Map<String, Object>> addAirplane(@RequestBody Airplane airplane) {

        Map<String, Object> response = new HashMap<>();

        try {

            Optional<Airplane> existingAirplane = airplaneDAO.findByRegNumber(airplane.getRegNumber());
            if (existingAirplane.isPresent()) {
                response.put("success", false);
                response.put("message",
                        "An airplane with registration number '" + airplane.getRegNumber() + "' already exists.");
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            airplaneDAO.save(airplane);
            response.put("success", true);
            response.put("message", "Airplane added successfully!");
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
    public ResponseEntity<Map<String, Object>> getAllAirplanes() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Airplane> airplanes = airplaneDAO.findAll();
            for (Airplane airplane : airplanes) {
                Airport currentLocation = airplaneDAO.getCurrentLocation(airplane.getAirplaneId());
                if (currentLocation == null) {
                    currentLocation = airplane.getInitialLocation();
                }
                airplane.setCurrentLocation(currentLocation); // Add this setter to your model
            }
            response.put("success", true);
            response.put("data", airplanes);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAirplaneById(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Airplane> airplane = airplaneDAO.findById(id);
            if (airplane.isPresent()) {
                response.put("success", true);
                response.put("data", airplane.get());
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(response);
            } else {
                response.put("success", false);
                response.put("message", "Airplane not found with ID: " + id);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateAirplane(@PathVariable String id, @RequestBody Airplane airplane) {

        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Airplane> existingAirplane = airplaneDAO.findById(id);
            if (existingAirplane.isPresent()) {
                Airplane airplaneToUpdate = existingAirplane.get();

                // Update the fields
                airplaneToUpdate.setRegNumber(airplane.getRegNumber());
                airplaneToUpdate.setModel(airplane.getModel());
                airplaneToUpdate.setCategory(airplane.getCategory());
                airplaneToUpdate.setCapacityFirst(airplane.getCapacityFirst());
                airplaneToUpdate.setCapacityBusiness(airplane.getCapacityBusiness());
                airplaneToUpdate.setCapacityEconomy(airplane.getCapacityEconomy());
                airplaneToUpdate.setManufacturer(airplane.getManufacturer());
                airplaneToUpdate.setInitialLocationId(airplane.getInitialLocationId());

                airplaneDAO.update(airplaneToUpdate);
                response.put("data", airplaneToUpdate);
                response.put("success", true);
                response.put("message", "Airplane updated successfully!");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("success", false);
                response.put("message", "Airplane not found with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            response.put("success", false);
            response.put("message", "Duplicate entry for registration number: " + airplane.getRegNumber());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}/current-location")
    public ResponseEntity<Map<String, Object>> getCurrentLocation(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Airport currentLocation = airplaneDAO.getCurrentLocation(id);
            if (currentLocation != null) {
                response.put("success", true);
                response.put("currentLocation", currentLocation);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("success", false);
                response.put("message", "Current location not found for airplane ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}