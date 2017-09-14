package ro.sci.hotel.model.util;

import ro.sci.hotelmanagementsystem.model.room.*;

/**
 * Created by tudorradovici on 14/09/17.
 */
public class Price {
    private double value = 0;
    private ro.sci.hotelmanagementsystem.model.room.Currency currency;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ro.sci.hotelmanagementsystem.model.room.Currency getCurrency() {
        return currency;
    }

    public void setCurrency(ro.sci.hotelmanagementsystem.model.room.Currency currency) {
        this.currency = currency;
    }
}
