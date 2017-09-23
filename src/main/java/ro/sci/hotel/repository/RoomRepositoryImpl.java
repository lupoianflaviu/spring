package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.sci.hotel.model.room.BedType;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.room.RoomType;
import ro.sci.hotel.model.util.Price;

/**
 * Room repository implementation
 */
@Repository("roomRepository")
public class RoomRepositoryImpl extends BaseRepository implements RoomRepository<Room>{

    private static final String DATABASE_ERROR = "Database error!";

    private static final String EXCEPTION_THROWN = "Exception thrown";

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    private static final String SQL_SELECT_ALL__FROM_ROOMS = "SELECT * FROM rooms";

    private static final String id = "id";

    private static final String ROOMTYPE = "roomtype";

    private static final String BEDTYPE = "bedtype";

    private static final String BEDNUMBER = "bednumber";

    private static final String OCEANVIEW = "oceanview";

    private static final String AIRCONDITIONING = "airconditioning";

    private static final String BALCONY = "balcony";

    private static final String SQL_DELETE_FROM_BOOKING_WHERE_ID = "DELETE FROM booking where id=?";

    private static final String PRICEID = "priceid";

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL_SELECT_ALL__FROM_ROOMS)) {

            while (rs.next()) {

                Room room = new Room();
                Price price = new Price();
                room.setRoomNumber(rs.getInt(id));
                room.setRoomType(RoomType.valueOf(rs.getString(ROOMTYPE)));
                room.setBedType(BedType.valueOf(rs.getString(BEDTYPE)));
                room.setBedNumber(rs.getInt(BEDNUMBER));
                room.setOceanView(rs.getBoolean(OCEANVIEW));
                room.setAirConditioning(rs.getBoolean(AIRCONDITIONING));
                room.setBalcony(rs.getBoolean(BALCONY));
                price.setValue(rs.getInt(PRICEID));
                room.setPricePerNight(price);
                rooms.add(room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return rooms;
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
    public Room searchByRoomNumber(Integer roomNumber) {
        Room room = new Room();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement("SELECT * FROM room WHERE id=?")) {
                stm.setInt(1, roomNumber);

                ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Price price = new Price();
                room.setRoomNumber(rs.getInt("id"));
                room.setRoomType(RoomType.valueOf(rs.getString(ROOMTYPE)));
                room.setBedType(BedType.valueOf(rs.getString(BEDTYPE)));
                room.setBedNumber(rs.getInt(BEDNUMBER));
                room.setOceanView(rs.getBoolean(OCEANVIEW));
                room.setAirConditioning(rs.getBoolean(AIRCONDITIONING));
                room.setBalcony(rs.getBoolean(BALCONY));
                price.setId(rs.getInt(PRICEID));
                room.setPricePerNight(price);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return room;
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
