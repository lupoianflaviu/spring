package ro.sci.hotel.model.event;

import ro.sci.hotel.model.util.Price;

public class EventRoom {

    private String roomnName;
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
}
