package ro.sci.hotel.model.room;

import java.util.Date;

/**
 * Hotel room model
 */
public class Room {

    private int roomNumber;
    private RoomType roomType;
    private BedType bedType;
    private int bedNumber = 1;
    private boolean oceanView = false;
    private boolean airConditioning = false;
    private boolean balcony = false;
    private boolean booked = false;
    private PricePerNight pricePerNight;
    private Date startDate;
    private Date endDate;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public boolean isOceanView() {
        return oceanView;
    }

    public void setOceanView(boolean oceanView) {
        this.oceanView = oceanView;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public PricePerNight getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(PricePerNight pricePerNight) {
        this.pricePerNight = pricePerNight;
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
