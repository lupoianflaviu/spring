package ro.sci.hotel.repository;

import org.apache.ibatis.annotations.Select;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.event.EventRoom;

import java.util.Date;
import java.util.List;

/**
 * Booking repository interface for DAO
 */
public interface EventRepository<T> {
    /**
     * read all the events from DB
     * @return List<T> events list
     */
    @Select("SELECT * FROM event")
    List<T> getAll();

    /**
     * @param event
     * @param eventRoom
     * @param customer
     */
    void createEvent(Event event, EventRoom eventRoom, Customer customer);

    /**
     *  delete an event entry from DB
     * @param t Event to be deleted
     */
    void delete(T t);

    /**
     * update an event in the DB
     * @param t Event to be updated
     */
    void update(T t);

    /**
     * search by event room name
     * @param eventRoomName
     * @return List<T> searched events List
     *     */
    List<T> serachByEventRoomName(String eventRoomName);

    /**
     * search an event Room by date
     * @param date search on a given date if the room is free or not
     * @return true or false
     */
     void searchedByDate(Date date);


}