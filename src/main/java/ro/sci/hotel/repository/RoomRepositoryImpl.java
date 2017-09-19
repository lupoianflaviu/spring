package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;
import ro.sci.hotel.model.room.BedType;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.room.RoomType;

import java.sql.Date;
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
    public List<Room> searchByPrice(Double price) {
        return null;
    }

    @Override
    public List<Room> searchByType(RoomType roomType) {
        return null;
    }
}
