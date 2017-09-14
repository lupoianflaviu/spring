package ro.sci.hotel.model.room;

/**
 * Price per night model
 */
public class PricePerNight {

    private double value = 0;
    private Currency currency;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
