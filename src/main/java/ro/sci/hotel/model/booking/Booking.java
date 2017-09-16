package ro.sci.hotel.model.booking;

import java.sql.Date;

import ro.sci.hotel.model.util.Currency;
import ro.sci.hotel.model.util.Price;

/**
 * Customer Booking model
 */
public class Booking {

    private int id;
    private Price pricePerDay;
    private Date startDate;
    private Date endDate;
    private int roomNumber;
    private int customerId;
    private Currency currency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Price getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Price pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Booking booking = (Booking) o;

        if (id != booking.id)
            return false;
        if (roomNumber != booking.roomNumber)
            return false;
        if (customerId != booking.customerId)
            return false;
        if (pricePerDay != null ? !pricePerDay.equals(booking.pricePerDay) : booking.pricePerDay != null)
            return false;
        if (startDate != null ? !startDate.equals(booking.startDate) : booking.startDate != null)
            return false;
        if (endDate != null ? !endDate.equals(booking.endDate) : booking.endDate != null)
            return false;
        return currency == booking.currency;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pricePerDay != null ? pricePerDay.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + roomNumber;
        result = 31 * result + customerId;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\n" + id + " , " + pricePerDay + " , " + startDate + " , " + endDate + " , " + roomNumber + " , " + customerId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
