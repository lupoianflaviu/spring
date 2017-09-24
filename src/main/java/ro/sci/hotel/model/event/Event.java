package ro.sci.hotel.model.event;

import ro.sci.hotel.model.util.Price;

import java.util.Date;

public class Event {

    private Date startDate;
    private Date endDate;
    private double totalPrice;
    private EventRoom eventRoom;

    public EventRoom getEventRoom() {
        return eventRoom;
    }

    public void setEventRoom(EventRoom eventRoom) {
        this.eventRoom = eventRoom;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

}
