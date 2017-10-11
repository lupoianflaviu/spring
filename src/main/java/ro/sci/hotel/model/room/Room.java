package ro.sci.hotel.model.room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ro.sci.hotel.model.util.Price;

/**
 * Hotel room model
 */
@Entity
public class Room {
    @Id
    @GeneratedValue
    private int roomNumber;
    private RoomType roomType;
    private BedType bedType;
    private int bedNumber = 1;
    private boolean oceanView = false;
    private boolean airConditioning = false;
    private boolean balcony = false;
    @OneToOne
    private Price pricePerNight;

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

    public Price getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Price pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return roomNumber + " , " + roomType + " , " + bedType + " , " + bedNumber + " , " + oceanView + " , " + airConditioning + " , " + balcony + " , "
                + pricePerNight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Room room = (Room) o;

        if (roomNumber != room.roomNumber)
            return false;
        if (bedNumber != room.bedNumber)
            return false;
        if (oceanView != room.oceanView)
            return false;
        if (airConditioning != room.airConditioning)
            return false;
        if (balcony != room.balcony)
            return false;
        if (roomType != room.roomType)
            return false;
        return bedType == room.bedType;
    }

    @Override
    public int hashCode() {
        int result = roomNumber;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (bedType != null ? bedType.hashCode() : 0);
        result = 31 * result + bedNumber;
        result = 31 * result + (oceanView ? 1 : 0);
        result = 31 * result + (airConditioning ? 1 : 0);
        result = 31 * result + (balcony ? 1 : 0);
        return result;
    }
}
