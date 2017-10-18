package ro.sci.hotel.model.event;


import java.sql.Date;

public class Event {
    private int id;
    private Date startdate;
    private Date enddate;
   // private double totalPrice;
    private EventRoom eventRoomId;

    public EventRoom getEventRoomId() {
        return eventRoomId;
    }

    public void setEventRoomId(EventRoom eventRoomId) {
        this.eventRoomId = eventRoomId;
    }

//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", eventRoomId=" + eventRoomId +
                '}';
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
