package ro.sci.hotel.model.event;


import java.sql.Date;

public class Event {
    private int id;
    private Date startDate;
    private Date endDate;
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
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", eventRoomId=" + eventRoomId +
                '}';
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
