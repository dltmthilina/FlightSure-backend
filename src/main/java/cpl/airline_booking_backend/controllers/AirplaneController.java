package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.AirplaneDAO;
import cpl.airline_booking_backend.model.Airplane;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
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
            response.put("message", "An airplane with registration number '" + airplane.getRegNumber() + "' already exists.");
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
}
