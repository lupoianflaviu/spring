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

import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.event.EventRoom;

@Repository
public class EventRepositoryImpl extends BaseRepository implements EventRepository {


    private static final String DATABASE_ERROR = "Database error!";

    private static final String EXCEPTION_THROWN = "Exception thrown";

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    private static final String WRITING_IN_DB_HAS_FINISHED = "Writing in db has finished!";

    private static final String SQL_INSERT_INTO_EVENT = "INSERT INTO event(id,startdate,enddate,eventroomid) values(nextval('event_id_seq'),?,?,?)";

    private static final String SQL_SELECT_ALL__FROM_EVENTS = "SELECT * FROM event";

    private static final String ID = "id";

    private static final String STARTDATE = "startdate";

    private static final String ENDDATE = "enddate";

    @Override
    public List<Event> getAll() {
        {
            List<Event> events = new ArrayList<>();

            try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL_SELECT_ALL__FROM_EVENTS)) {

                while (rs.next()) {

                    Event event = new Event();
                    EventRoom room = new EventRoom();
                    room.getRoomName();
                    event.setId(rs.getInt(ID));
                    event.setStartDate(rs.getDate(STARTDATE));
                    event.setEndDate(rs.getDate(ENDDATE));
                    room.setId(rs.getInt("eventroomid"));
                    event.setEventRoomId(room);

                    events.add(event);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                LOGGER.log(Level.WARNING, DATABASE_ERROR);
                throw new RuntimeException(EXCEPTION_THROWN);
            }

            return events;
        }

    }

    @Override
    public void createEvent(Event event, EventRoom eventRoom) {
        {
            try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_INSERT_INTO_EVENT)) {

                stm.setDate(1, event.getStartDate());
                stm.setDate(2, event.getEndDate());
                stm.setInt(3, event.getEventRoomId()
                                   .getId());
                stm.execute();

            } catch (SQLException ex) {
                LOGGER.log(Level.WARNING, DATABASE_ERROR);
                throw new RuntimeException(EXCEPTION_THROWN);
            }

            LOGGER.log(Level.INFO, WRITING_IN_DB_HAS_FINISHED);

        }
    }


    @Override
    public void delete(Object o) {
        LOGGER.log(Level.INFO, "NOT Implemented");
    }

    @Override
    public void update(Object o) {
        LOGGER.log(Level.INFO, "NOT Implemented");
    }

    @Override
    public List searchByEventRoomName(String eventRoomName) {
        LOGGER.log(Level.INFO, "NOT Implemented");

        return null;
    }

}
