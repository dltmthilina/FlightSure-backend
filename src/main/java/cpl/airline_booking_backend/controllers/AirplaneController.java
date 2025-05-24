package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.AirplaneDAO;
import cpl.airline_booking_backend.model.Airplane;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

    private final AirplaneDAO airplaneDAO = new AirplaneDAO();

    @PostMapping
    public String addAirplane(@RequestBody Airplane airplane) {
        try {
            airplaneDAO.save(airplane);
            return "Airplane added successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Airplane> getAllAirplanes() {
        try {
            return airplaneDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
