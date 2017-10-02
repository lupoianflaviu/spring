package ro.sci.hotel.model.event;

import java.util.Date;

public class Event {
    private int id;


    private Date startdate;
    private Date enddate;
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

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getId() {  return id;    }

    public void setId(int id) { this.id = id;    }

}
