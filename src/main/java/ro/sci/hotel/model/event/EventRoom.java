package ro.sci.hotel.model.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ro.sci.hotel.model.util.Price;

@Entity
public class EventRoom {
    @Id
    @GeneratedValue
    private int id;
    private String roomName;
    private int roomCapacity;
    private String roomOrientation;
    private int floorRoom;
    @OneToOne
    private Price pricePerDay;

    @Override
    public String toString() {
        return "EventRoom{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", roomCapacity=" + roomCapacity +
                ", roomOrientation='" + roomOrientation + '\'' +
                ", floorRoom=" + floorRoom +
                ", pricePerDay=" + pricePerDay +
                '}';
    }

    public int getFloorRoom() {
        return floorRoom;
    }

    public void setFloorRoom(int floorRoom) {
        this.floorRoom = floorRoom;
    }

    public String getRoomOrientation() {
        return roomOrientation;
    }

    public void setRoomOrientation(String roomOrientation) {
        this.roomOrientation = roomOrientation;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EventRoom{" + "id=" + id + ", roomName='" + roomName + '\'' + ", roomCapacity=" + roomCapacity + ", roomOrientation='" + roomOrientation + '\''
                + ", floorRoom=" + floorRoom + ", pricePerDay=" + pricePerDay + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EventRoom eventRoom = (EventRoom) o;

        if (id != eventRoom.id)
            return false;
        if (roomCapacity != eventRoom.roomCapacity)
            return false;
        if (floorRoom != eventRoom.floorRoom)
            return false;
        if (roomName != null ? !roomName.equals(eventRoom.roomName) : eventRoom.roomName != null)
            return false;
        if (roomOrientation != null ? !roomOrientation.equals(eventRoom.roomOrientation) : eventRoom.roomOrientation != null)
            return false;
        return pricePerDay != null ? pricePerDay.equals(eventRoom.pricePerDay) : eventRoom.pricePerDay == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roomName != null ? roomName.hashCode() : 0);
        result = 31 * result + roomCapacity;
        result = 31 * result + (roomOrientation != null ? roomOrientation.hashCode() : 0);
        result = 31 * result + floorRoom;
        result = 31 * result + (pricePerDay != null ? pricePerDay.hashCode() : 0);
        return result;
    }
}

