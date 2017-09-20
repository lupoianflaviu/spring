package ro.sci.hotel.model.util;

/**
 * Price model
 */
public class Price {
    private int id;
    private double value = 0;
    private Currency currency;

    public Price() {
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(ro.sci.hotel.model.util.Currency currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n" + id + " , " + value + " , " + currency;
    }
}
