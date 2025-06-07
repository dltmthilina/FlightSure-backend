package cpl.airline_booking_backend.model;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passportNumber;
    private String password;
    private String role;
    private String nationality;
    private String status;

    // No-argument constructor
    public User() {
    }

    // All-argument constructor
    public User(int userId, String firstName String lastName, String email, String phoneNumber, String passportNumber, String password, String role, String status) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getPassportNumber() {
        return PassportNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String setFirstName() {
        this.firstName = firstName;
    }
    
    public String setLastName() {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String setPhoneNumber() {
        this.phoneNumber = phoneNumber;
    }
    
    public String setPassportNumber(){
        this.PassportNumber = PassportNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString() (optional, useful for debugging)
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
