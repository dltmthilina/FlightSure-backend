package cpl.airline_booking_backend.model;

public class Booking {
    private int bookingId;
    private int userId;
    private int flightId;
    private String bookingDate;
    private String seatNumber;
    private String status;
    private String seatClass;
    

    public Booking() {}

    public Booking(int bookingId, int userId, int flightId, String bookingDate, String seatNumber, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.flightId = flightId;
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.status = status;
        this.seatClass = seatClass;
    }

    // Getters and setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getSeatClass() {
    return seatClass;
}
public void setSeatClass(String seatClass) {
    this.seatClass = seatClass;
}
}
