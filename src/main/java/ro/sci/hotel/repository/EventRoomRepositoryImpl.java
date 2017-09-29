package ro.sci.hotel.repository;

import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.event.EventRoom;
import ro.sci.hotel.model.util.Price;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class EventRoomRepositoryImpl extends BaseRepository implements EventRoomRepository {
    @Override
    public List getAll() {
        List<EventRoom> eventRooms = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery("SELECT * FROM eventroom")) {

            while (rs.next()) {

                Price price = new Price();
                EventRoom room = new EventRoom();

                room.setRoomnName(rs.getString("roomname"));
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
    public void createEvent(Object eventRoom, Price price) {

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
            Event event = new Event();

            try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement("SELECT * FROM eventroom WHERE id=?")) {

                stm.setDouble(1, eventRoomId);

                ResultSet rs = stm.executeQuery();

                while (rs.next()) {

                    EventRoom room = new EventRoom();

                    room.setId(rs.getInt("eventid"));
                    room.setRoomnName(rs.getString("roomname"));


                    event.setEventRoom(room);
                    event.setStartDate(rs.getDate("startdate"));
                    event.setEndDate(rs.getDate("enddate"));
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }


            return event;
        }
    }
}
