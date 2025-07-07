package cpl.airline_booking_backend.model;

public class Airplane {
    private String airplaneId;
    private String regNumber;
    private String model;
    private String category;
    private int capacityFirst;
    private int capacityBusiness;
    private int capacityEconomy;
    private String manufacturer;
    private String initialLocationId;
    private Airport currentLocation;
    private Airport initialLocation;

    public Airplane() {
    }

    public Airplane(
                    String airplaneId, String regNumber, String model, String category, int capacityFirst,
            int capacityBusiness, int capacityEconomy, String manufacturer, String initialLocationId) {
        this.airplaneId = airplaneId;
        this.regNumber = regNumber;
        this.model = model;
        this.category = category;
        this.capacityFirst = capacityFirst;
        this.capacityBusiness = capacityBusiness;
        this.capacityEconomy = capacityEconomy;
        this.manufacturer = manufacturer;
        this.initialLocationId = initialLocationId;

    }

    public Airport getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Airport location) {
        this.currentLocation = location;
    }

    public String getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(String airplaneId) {
        this.airplaneId = airplaneId;
    }

    public String getInitialLocationId() {
        return initialLocationId;
    }

    public void setInitialLocationId(String initialLocationId) {
        this.initialLocationId = initialLocationId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCapacityFirst() {
        return capacityFirst;
    }

    public void setCapacityFirst(int capacityFirst) {
        this.capacityFirst = capacityFirst;
    }

    public int getCapacityBusiness() {
        return capacityBusiness;
    }

    public void setCapacityBusiness(int capacityBusiness) {
        this.capacityBusiness = capacityBusiness;
    }

    public int getCapacityEconomy() {
        return capacityEconomy;
    }

    public void setCapacityEconomy(int capacityEconomy) {
        this.capacityEconomy = capacityEconomy;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Airport getInitialLocation() {
        return initialLocation;
    }

    public void setInitialLocation(Airport initialLocation) {
        this.initialLocation = initialLocation;
    }

    // Optional: total capacity
    public int getTotalCapacity() {
        return capacityFirst + capacityBusiness + capacityEconomy;
    }
}
