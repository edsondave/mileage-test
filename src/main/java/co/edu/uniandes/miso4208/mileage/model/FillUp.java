package co.edu.uniandes.miso4208.mileage.model;

import java.math.BigDecimal;

public class FillUp {

    Vehicle vehicle;
    BigDecimal pricePerGallons;
    BigDecimal gallons;
    BigDecimal odometer;
    Boolean isFull;
    String comment;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public BigDecimal getPricePerGallons() {
        return pricePerGallons;
    }

    public void setPricePerGallons(BigDecimal pricePerGallons) {
        this.pricePerGallons = pricePerGallons;
    }

    public BigDecimal getGallons() {
        return gallons;
    }

    public void setGallons(BigDecimal gallons) {
        this.gallons = gallons;
    }

    public Boolean getFull() {
        return isFull;
    }

    public void setFull(Boolean full) {
        isFull = full;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getOdometer() {
        return odometer;
    }

    public void setOdometer(BigDecimal odometer) {
        this.odometer = odometer;
    }
}
