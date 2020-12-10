package co.edu.uniandes.miso4208.mileage.model;

public enum EfficiencyUnit {
    MILES_PER_GALLON("Miles / Gallon"),
    KILOMETERS_PER_GALLON("Km / Gallon"),
    MILES_PER_IMPERIAL_GALLON("Miles / Imp. Gal."),
    KILOMETERS_PER_IMPERIAL_GALLON("Km / Imp. Gal."),
    MILES_PER_LITER("Miles / Litre"),
    KILOMETERS_PER_LITRE("Km / Litre"),
    GALLONS_PER_100_KILOMETERS("Gallons / 100 km"),
    LITER_PER_100_KILOMETERS("Litres / 100 km"),
    IMPERIAL_GALLONS_PER_100_KILOMETERS("Imp. Gal. / 100 km");

    String label;

    EfficiencyUnit(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
