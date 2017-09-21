package ro.sci.hotel.model.room;

import ro.sci.hotel.model.util.Price;

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
    private int priceId;

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

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomType=" + roomType +
                ", bedType=" + bedType +
                ", bedNumber=" + bedNumber +
                ", oceanView=" + oceanView +
                ", airConditioning=" + airConditioning +
                ", balcony=" + balcony +
                ", priceId=" + priceId +
                '}';
    }
}
