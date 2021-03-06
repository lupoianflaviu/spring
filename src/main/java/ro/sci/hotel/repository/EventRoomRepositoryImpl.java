package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.sci.hotel.model.event.EventRoom;
import ro.sci.hotel.model.util.Price;

/**
 * EventRoomRepository implementation
 */
@Repository
public class EventRoomRepositoryImpl extends BaseRepository implements EventRoomRepository {
    private static final String DATABASE_ERROR = "Database error!";

    private static final String EXCEPTION_THROWN = "Exception thrown";

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    private static final String WRITING_IN_DB_HAS_FINISHED = "Writing in db has finished!";

    private static final String SQL_INSERT_INTO_EVENTROOM = "INSERT INTO eventroom(roomname,roomcapacity,floorroom,priceid) values(?,?,?,?)";

    @Override
    public List getAll() {
        List<EventRoom> eventRooms = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery("SELECT * FROM eventroom")) {

            while (rs.next()) {

                Price price = new Price();
                EventRoom room = new EventRoom();

                room.setRoomName(rs.getString("roomname"));
                room.setRoomCapacity(rs.getInt("roomcapacity"));
                room.setFloorRoom(rs.getInt("floorroom"));
                room.setId(rs.getInt("id"));
                price.setId(rs.getInt("priceid"));
                room.setPricePerDay(price);
                eventRooms.add(room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Data base error");
        }

        return eventRooms;
    }


    @Override
    public void createEventRoom(EventRoom eventRoom) {
        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_INSERT_INTO_EVENTROOM)) {


            stm.setString(1, eventRoom.getRoomName());
            stm.setInt(2, eventRoom.getRoomCapacity());
            stm.setInt(3, eventRoom.getFloorRoom());
            stm.setInt(4, eventRoom.getPricePerDay()
                                   .getId());
            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, WRITING_IN_DB_HAS_FINISHED);

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public List searchByEventRoomName(String eventRoomName) {
        return null;
    }

    @Override
    public void searchedByDate(Date date) {

    }

    @Override
    public Object searchByEventRoomId(Integer eventRoomId) {
        {

            EventRoom eventRoom = new EventRoom();

            try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement("SELECT * FROM eventroom WHERE id=?")) {

                stm.setInt(1, eventRoomId);

                ResultSet rs = stm.executeQuery();

                while (rs.next()) {

                    eventRoom.setId(rs.getInt("id"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return eventRoom;
        }
    }
}
