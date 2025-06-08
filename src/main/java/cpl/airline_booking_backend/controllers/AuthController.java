package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.UserDAO;
import cpl.airline_booking_backend.model.User;
import cpl.airline_booking_backend.utils.JwtUtil;
import cpl.airline_booking_backend.utils.PasswordUtil;
import org.springframework.web.bind.annotation.*;
import cpl.airline_booking_backend.model.LoginResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserDAO userDAO = new UserDAO();

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        
        Map<String, Object> response = new HashMap<>();
    try {
        User existingUser = userDAO.findByEmail(user.getEmail());
        if (existingUser != null) {
            response.put("success", false);
            response.put("message", "Email is already registered.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        
          if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("CUSTOMER"); // default role
        }
        if (user.getStatus() == null || user.getStatus().isEmpty()) {
            user.setStatus("ACTIVE"); // default status
        }
        userDAO.save(user);
        String token = JwtUtil.generateToken(user.getEmail());
        response.put("success", true);
            response.put("user", user);
            response.put("token", token);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        
        
    } catch (Exception e) {
        e.printStackTrace();
                response.put("success", false);
        response.put("message", "Register failed: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    .body(response);
          
    }
}

@PostMapping("/login")
public ResponseEntity<Map<String, Object>> login(@RequestBody User loginUser) {
    
    Map<String, Object> response = new HashMap<>();
    try {
        User dbUser = userDAO.findByEmail(loginUser.getEmail());
        if (dbUser != null && PasswordUtil.checkPassword(loginUser.getPassword(), dbUser.getPassword())) {
            String token = JwtUtil.generateToken(dbUser.getEmail());
          
            dbUser.setPassword(null); // Hide password in response
 
            response.put("success", true);
            response.put("user", dbUser);
            response.put("token", token);
            return ResponseEntity.status(HttpStatus.OK)
    .body(response);
            
        } else {
            response.put("success", false);
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

    } catch (Exception e) {
        e.printStackTrace();
        response.put("success", false);
        response.put("message", "Login failed " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    .body(response);
    }
}

}
