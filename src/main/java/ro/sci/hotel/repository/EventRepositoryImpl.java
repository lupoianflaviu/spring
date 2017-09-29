package ro.sci.hotel.repository;

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.event.EventRoom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventRepositoryImpl extends BaseRepository implements EventRepository {


    private static final String DATABASE_ERROR = "Database error!";

    private static final String EXCEPTION_THROWN = "Exception thrown";

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    private static final String WRITING_IN_DB_HAS_FINISHED = "Writing in db has finished!";

    private static final String SQL_INSERT_INTO_EVENTROOM_VALUES =
            "INSERT INTO eventroom(roomname,roomcapacity,floorroom,priceid) values(?,?,?,?)";

    private static final String EVENT_DELETE_IS_COMPLETED = "Deletion of booking completed";

    private static final String SQL_UPDATE_EVENT_WHERE_ID = "UPDATE event " + "SET startdate=?, enddate=?, WHERE id = ?";

    private static final String EVENT_UPDATE_IN_DB_IS_COMPLETED = "Event update in db has completed";

    private static final String SQL_SELECT_ALL__FROM_EVENTS = "SELECT * FROM events";

    private static final String ID = "id";

    private static final String ROOMNAME = "roomname";

    private static final String CUSTOMERID = "customerid";

    private static final String STARTDATE = "startdate";

    private static final String ENDDATE = "enddate";

    private static final String SQL_DELETE_FROM_EVENT_WHERE_ID = "DELETE FROM event where id=?";


    @Override
    public List getAll() {
        {
            List<Event> events = new ArrayList<>();

            try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL_SELECT_ALL__FROM_EVENTS)) {

                while (rs.next()) {

                    Event event = new Event();
                    EventRoom room = new EventRoom();
                    room.getRoomnName();
                    event.setId(rs.getInt(ID));
                    event.setStartDate(rs.getDate(STARTDATE));
                    event.setEndDate(rs.getDate(ENDDATE));
                    room.setId(rs.getInt("eventroomid"));
                    event.setEventRoom(room);

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
    public void createEvent(Event event, EventRoom eventRoom, Customer customer) {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public List serachByEventRoomName(String eventRoomName) {
        return null;
    }

    @Override
    public void searchedByDate(Date date) {

    }
}
