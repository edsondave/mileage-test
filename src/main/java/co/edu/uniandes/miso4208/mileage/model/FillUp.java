package co.edu.uniandes.miso4208.mileage.model;

import java.math.BigDecimal;
import java.util.Date;

public class FillUp {

    BigDecimal pricePerVolume;
    BigDecimal volume;
    BigDecimal odometer;
    Date date;
    Boolean isPartial;
    String comment;

    public BigDecimal getPricePerVolume() {
        return pricePerVolume;
    }

    public void setPricePerVolume(BigDecimal pricePerVolume) {
        this.pricePerVolume = pricePerVolume;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Boolean getPartial() {
        return isPartial;
    }

    public void setPartial(Boolean full) {
        isPartial = full;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
