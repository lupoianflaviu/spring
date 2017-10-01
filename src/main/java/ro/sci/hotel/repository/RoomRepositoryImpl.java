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

    private static final String WRITING_IN_DB_HAS_FINISHED = "Writing in db has finished!";

    private static final String SQL_SELECT_ALL__FROM_ROOMS = "SELECT * FROM room";

    private static final String ID = "id";

    private static final String ROOMTYPE = "roomtype";

    private static final String BEDTYPE = "bedtype";

    private static final String BEDNUMBER = "bednumber";

    private static final String OCEANVIEW = "oceanview";

    private static final String AIRCONDITIONING = "airconditioning";

    private static final String BALCONY = "balcony";

    private static final String PRICEID = "priceid";

    private static final String SQL_INSERT_INTO_ROOMS_ID_ROOMTYPE_BEDTYPE_BEDNUMBER_OCEANVIEW_AIRCONDITIONING_BALCONY_PRICEID_VALUES =
            "INSERT INTO room(id,roomtype,bedtype,bednumber,oceanview,airconditioning,balcony,priceid) values(?,?,?,?,?,?,?,?)";

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL_SELECT_ALL__FROM_ROOMS)) {

            while (rs.next()) {

                Room room = new Room();
                Price price = new Price();
                room.setRoomNumber(rs.getInt(ID));
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
    public void create(Room room, Price price) {

        try (Connection conn = newConnection();
             PreparedStatement stm = conn.prepareStatement(SQL_INSERT_INTO_ROOMS_ID_ROOMTYPE_BEDTYPE_BEDNUMBER_OCEANVIEW_AIRCONDITIONING_BALCONY_PRICEID_VALUES)) {


            stm.setInt(1, room.getRoomNumber());
            stm.setString(2, String.valueOf(room.getRoomType()));
            stm.setString(3, String.valueOf(room.getBedType()));
            stm.setInt(4, room.getBedNumber());
            stm.setBoolean(5, room.isBalcony());
            stm.setBoolean(6, room.isAirConditioning());
            stm.setBoolean(7, room.isBalcony());
            stm.setInt(8, room.getPricePerNight().getId());


            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, WRITING_IN_DB_HAS_FINISHED);
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
                room.setRoomNumber(rs.getInt(ID));
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
