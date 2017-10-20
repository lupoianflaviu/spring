package ro.sci.hotel.model.event;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Event entity, represents a reservation of an EventRoom
 */
@Entity
public class Event {
    @Id
    @GeneratedValue
    private int id;

    private Date startDate;
    private Date endDate;

    @OneToOne
    private EventRoom eventRoomId;

    public EventRoom getEventRoomId() {
        return eventRoomId;
    }

    public void setEventRoomId(EventRoom eventRoomId) {
        this.eventRoomId = eventRoomId;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", eventRoomId=" + eventRoomId + '}';
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
