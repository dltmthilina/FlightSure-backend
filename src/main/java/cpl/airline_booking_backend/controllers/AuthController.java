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
    public String register(@RequestBody User user) {
        try {
            userDAO.save(user);
            return "User registered successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
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
