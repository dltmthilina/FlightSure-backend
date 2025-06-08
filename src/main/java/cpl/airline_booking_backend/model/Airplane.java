package cpl.airline_booking_backend.model;

public class Airplane {
    private int airplaneId;
    private String regNumber;
    private String model;
    private String category;
    private int capacityFirst;
    private int capacityBusiness;
    private int capacityEconomy;
    private String manufacturer;

    public Airplane() {}

    public Airplane(int airplaneId, String regNumber, String model, String category, int capacityFirst, int capacityBusiness, int capacityEconomy, String manufacturer) {
        this.airplaneId = airplaneId;
        this.regNumber = regNumber;
        this.model = model;
        this.category = category;
        this.capacityFirst = capacityFirst;
        this.capacityBusiness = capacityBusiness;
        this.capacityEconomy = capacityEconomy;
        this.manufacturer = manufacturer;
    }

    public int getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(int airplaneId) {
        this.airplaneId = airplaneId;
    }
    
    public String getRegNumber(){
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

    // Optional: total capacity
    public int getTotalCapacity() {
        return capacityFirst + capacityBusiness + capacityEconomy;
    }
}
