package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;
import java.sql.Connection;
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

    private static final String WRITING_IN_DB_HAS_FINISHED = "Writing in db has finished!";

    private static final String SQL_INSERT_INTO_BOOKING_ROOMNUMBER_CUSTOMERID_STARTDATE_ENDDATE_VALUES =
            "INSERT INTO booking(roomnumber,customerid,startdate,enddate) values(?,?,?,?)";

    private static final String BOOKING_DELETE_HAS_COMPLETED = "Deletion of booking completed";

    private static final String SQL_UPDATE_BOOKING_WHERE_ID = "UPDATE booking " + "SET roomnumber=?, customerid=?, startdate=?, enddate=? WHERE id = ?";

    private static final String BOOKING_UPDATE_IN_DB_HAS_COMPLETED = "Booking update in db has completed";

    private static final String SQL_SELECT_ALL__FROM_ROOMS = "SELECT * FROM customers";

    private static final String ROOMNUMBER = "roomnumber";

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
                room.setRoomNumber(rs.getInt(ROOMNUMBER));
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
