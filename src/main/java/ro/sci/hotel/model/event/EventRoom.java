package ro.sci.hotel.model.event;

import ro.sci.hotel.model.util.Price;

public class EventRoom {
    private int id;
    private String roomName;
    private int roomCapacity;
    private String roomOrientation;
    private int floorRoom;
    private Price pricePerDay;


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
}

