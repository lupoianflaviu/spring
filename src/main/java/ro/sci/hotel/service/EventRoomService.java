package ro.sci.hotel.service;

import ro.sci.hotel.model.util.Price;

import java.util.Date;
import java.util.List;

public interface EventRoomService <T> {
    /**
     * read all the events from DB
     * @return List<T> events list
     */

    List<T> getAll();

    /**
     * @param price
     *  @param eventRoom
     */
    void createEvent(T eventRoom, Price price);

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
    List<T> searchByEventRoomName(String eventRoomName);

    /**
     * @param eventRoomId
     * @return T searched event by Id
     * *     */
    T searchByEventRoomId(Integer eventRoomId);
    /**
     * search an event Room by date
     * @param date search on a given date if the room is free or not
     * @return true or false
     */
    void searchedByDate(Date date);
}
