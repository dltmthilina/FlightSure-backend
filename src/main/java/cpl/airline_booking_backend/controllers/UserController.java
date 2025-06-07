package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.UserDAO;
import cpl.airline_booking_backend.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserDAO userDAO = new UserDAO();

    @PostMapping
    public String registerUser(@RequestBody User user) {
        try {
            userDAO.save(user);
            return " User saved successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return " Error saving user: " + e.getMessage();
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        try {
            return userDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
