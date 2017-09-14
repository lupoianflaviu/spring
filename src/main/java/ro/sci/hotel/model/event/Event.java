package ro.sci.hotel.model.event;

import java.util.Date;

public class Event {
    private String roomnName;
    private int roomCapacity;
    private Price pricePerDay;
    private Date startDate;
    private Date endDate;


    public String getRoomnName() {
        return roomnName;
    }

    public void setRoomnName(String roomnName) {
        this.roomnName = roomnName;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
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

}
