package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;
import ro.sci.hotel.model.room.BedType;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.room.RoomType;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Room repository implementation
 */
@Repository
public class RoomRepositoryImpl extends BaseRepository implements RoomRepository<Room>{

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    @Override
    public List<Room> getAll() {
        return null;
    }

    @Override
    public void create(Room room) {

    }

    @Override
    public void delete(Room room) {

    }

    @Override
    public void update(Room room) {

    }

    @Override
    public List<Room> searchByRoomNumber(Integer roomNumber) {
        return null;
    }

    @Override
    public List<Room> searchByDate(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Room> searchByPrice(Double price) {
        return null;
    }

    @Override
    public List<Room> searchByType(RoomType roomType) {
        return null;
    }

    @Override
    public List<Room> searchByBedType(BedType bedType) {
        return null;
    }

    @Override
    public List<Room> searchByBedNumber(Integer bedNumber) {
        return null;
    }

    @Override
    public List<Room> searchByAC(boolean isAirConditioning) {
        return null;
    }

    @Override
    public List<Room> searchByBalcony(boolean isBalcony) {
        return null;
    }

    @Override
    public List<Room> searchByBooked(boolean isBooked) {
        return null;
    }
}
