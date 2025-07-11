package cpl.airline_booking_backend.model;

public class Airport {
    private int airportId;
    private String code;
    private String name;
    private String city;
    private String country;
    private String timeZone;

    public Airport() {}

    public Airport(int airportId, String code, String name, String city, String country, String timeZone) {
        this.airportId = airportId;
        this.code = code;
        this.name = name;
        this.city = city;
        this.country = country;
        this.timeZone = timeZone;
    }
    
    public String getTimeZone(){
    return timeZone;
    }
    
    public void setTimeZone(String timeZone){
        this.timeZone = timeZone;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
