package co.edu.uniandes.miso4208.mileage.model;

import java.util.LinkedList;
import java.util.List;

public class Vehicle {

    private String title;
    private Integer year;
    private String make;
    private String model;
    private String description;
    private VehicleType vehicleType;
    private DistanceUnit distanceUnit;
    private VolumenUnit volumeUnit;
    private EfficiencyUnit efficiencyUnit;
    private String currencySymbol;
    private List<FillUp> fillUps = new LinkedList <>();

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

    public VolumenUnit getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(VolumenUnit volumeUnit) {
        this.volumeUnit = volumeUnit;
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

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public EfficiencyUnit getEfficiencyUnit() {
        return efficiencyUnit;
    }

    public void setEfficiencyUnit(EfficiencyUnit efficiencyUnit) {
        this.efficiencyUnit = efficiencyUnit;
    }

    public List <FillUp> getFillUps() {
        return fillUps;
    }

    public void setFillUps(List <FillUp> fillUps) {
        this.fillUps = fillUps;
    }

}
