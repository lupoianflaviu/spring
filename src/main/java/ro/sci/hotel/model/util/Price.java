package ro.sci.hotel.model.util;

import ro.sci.hotel.model.room.*;

/**
 * Created by tudorradovici on 14/09/17.
 */
public class Price {
    private double value = 0;
    private Currency currency;

    public Price(Currency currency) {
        this.currency = currency;
    }

    public Price(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ro.sci.hotel.model.util.Currency getCurrency() {
        return currency;
    }

    public void setCurrency(ro.sci.hotel.model.util.Currency currency) {
        this.currency = currency;
    }
}
