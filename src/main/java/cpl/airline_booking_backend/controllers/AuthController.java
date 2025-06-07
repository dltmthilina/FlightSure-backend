package cpl.airline_booking_backend.controllers;

import cpl.airline_booking_backend.dao.UserDAO;
import cpl.airline_booking_backend.model.User;
import cpl.airline_booking_backend.utils.JwtUtil;
import cpl.airline_booking_backend.utils.PasswordUtil;
import org.springframework.web.bind.annotation.*;
import cpl.airline_booking_backend.model.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserDAO userDAO = new UserDAO();

    @PostMapping("/register")
    public LoginResponse register(@RequestBody User user) {
    try {
        User existingUser = userDAO.findByEmail(user.getEmail());
        if (existingUser != null) {
            return new LoginResponse("Email is already registered.");
        }
        userDAO.save(user);
        String token = JwtUtil.generateToken(user.getEmail());
        return new LoginResponse("User registered successfully!", token, user);
    } catch (Exception e) {
        e.printStackTrace();
        return new LoginResponse("Register failed: " + e.getMessage());
        
    }
}

@PostMapping("/login")
public LoginResponse login(@RequestBody User loginUser) {
    try {
        User dbUser = userDAO.findByEmail(loginUser.getEmail());
        if (dbUser != null && PasswordUtil.checkPassword(loginUser.getPassword(), dbUser.getPassword())) {
            String token = JwtUtil.generateToken(dbUser.getEmail());
          
            dbUser.setPassword(null); // Hide password in response
 
            return new LoginResponse("Login successful.", token, dbUser);
        } else {
            return new LoginResponse("Invalid email or password");
        }

    } catch (Exception e) {
        e.printStackTrace();
        return new LoginResponse("Login failed: " + e.getMessage());
    }
}

}
