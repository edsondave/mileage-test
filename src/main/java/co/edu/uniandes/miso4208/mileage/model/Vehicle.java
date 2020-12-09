package co.edu.uniandes.miso4208.mileage.model;

public class Vehicle {

    private String title;
    private Integer year;
    private String make;
    private String model;
    private String description;
    private DistanceUnit distanceUnit;
    private VolumenUnit volumenUnit;
    private String currencySymbol;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public DistanceUnit getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(DistanceUnit distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public VolumenUnit getVolumenUnit() {
        return volumenUnit;
    }

    public void setVolumenUnit(VolumenUnit volumenUnit) {
        this.volumenUnit = volumenUnit;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
