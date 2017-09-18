package ro.sci.hotel.repository;

import ro.sci.hotel.model.room.BedType;
import ro.sci.hotel.model.room.RoomType;

import java.sql.Date;
import java.util.List;

/**
 * Room repository interface for DAO
 */
public interface RoomRepository<T> {

    /**
     * Get all rooms from DB
     *
     * @return List<T> Room list
     */
    List<T> getAll();

    /**
     * Create a room
     *
     * @param t new room
     */
    void create(T t);

    /**
     * Detele a room entry from db
     *
     * @param t room to be deleted
     */
    void delete(T t);

    /**
     * Update a room entry in db
     *
     * @param t room to pe updated
     */
    void update(T t);

    /**
     * Search room by room number
     * @param roomNumber searched
     * @return List<T> searched room list
     */
    List<T> searchByRoomNumber(Integer roomNumber);

    /**
     * Search room by interval
     * @param startDate date of arrival
     * @param endDate date of departure
     * @return List<T> searched room list
     */
    List<T> searchByDate(Date startDate, Date endDate);

    /**
     * Search room by price
     * @param price seached
     * @return List<T> searched room list
     */
    List<T> searchByPrice(Double price);

    /**
     * Search room by type
     * @param roomType seached
     * @return List<T> searched room list
     */
    List<T> searchByType(RoomType roomType);

    /**
     * Search room by bed type
     * @param bedType seached
     * @return List<T> searched room list
     */
    List<T> searchByBedType(BedType bedType);

    /**
     * Search room by bed number
     * @param bedNumber seached
     * @return List<T> searched room list
     */
    List<T> searchByBedNumber(Integer bedNumber);

    /**
     * Search room by air conditioning
     * @param isAirConditioning seached
     * @return List<T> searched room list
     */
    List<T> searchByAC(boolean isAirConditioning);

    /**
     * Search room by balcony
     * @param isBalcony seached
     * @return List<T> searched room list
     */
    List<T> searchByBalcony(boolean isBalcony);

    /**
     * Search room by booked
     * @param isBooked seached
     * @return List<T> searched room list
     */
    List<T> searchByBooked(boolean isBooked);
}
