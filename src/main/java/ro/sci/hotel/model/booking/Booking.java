package ro.sci.hotel.model.booking;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.util.Price;

/**
 * Customer Booking model
 */
@Entity
public class Booking {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Price pricePerDay;
    private Date startDate;
    private Date endDate;
    @OneToOne
    private Room room;
    @OneToOne
    private Customer customer;
    private Double totalBookingPrice;

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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return "\n" + id + " , " + pricePerDay + " , " + startDate + " , " + endDate + " , " + room + " , " + customer;
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
        if (pricePerDay != null ? !pricePerDay.equals(booking.pricePerDay) : booking.pricePerDay != null)
            return false;
        if (startDate != null ? !startDate.equals(booking.startDate) : booking.startDate != null)
            return false;
        if (endDate != null ? !endDate.equals(booking.endDate) : booking.endDate != null)
            return false;
        if (room != null ? !room.equals(booking.room) : booking.room != null)
            return false;
        if (customer != null ? !customer.equals(booking.customer) : booking.customer != null)
            return false;
        return totalBookingPrice != null ? totalBookingPrice.equals(booking.totalBookingPrice) : booking.totalBookingPrice == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pricePerDay != null ? pricePerDay.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (totalBookingPrice != null ? totalBookingPrice.hashCode() : 0);
        return result;
    }

    public Double getTotalBookingPrice() {
        return totalBookingPrice;
    }

    public void setTotalBookingPrice(Double totalBookingPrice) {
        this.totalBookingPrice = totalBookingPrice;
    }
}
