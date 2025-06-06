package cpl.airline_booking_backend.model;

public class LoginResponse {
    private String message;
    private String token;
    private User user;

    public LoginResponse() {}

    public LoginResponse(String message, String token, User user) {
        this.message = message;
        this.token = token;
        this.user = user;
    }
    
    public LoginResponse(String message) {
    this.message = message;
    this.token = null;
    this.user = null;
}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
