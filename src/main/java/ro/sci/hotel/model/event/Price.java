package ro.sci.hotel.model.event;

import ro.sci.hotel.model.room.Currency;

public class Price {
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
