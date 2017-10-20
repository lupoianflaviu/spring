package ro.sci.hotel.repository;

import java.util.List;

import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.room.RoomType;
import ro.sci.hotel.model.util.Price;

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
     * @param room  to be added in db
     * @param price that is in db
     */
    void create(Room room, Price price);

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
     *
     * @param roomNumber searched
     * @return T searched room
     */
    T searchByRoomNumber(Integer roomNumber);

    /**
     * Search room by price
     *
     * @param price seached
     * @return List<T> searched room list
     */
    List<T> searchByPrice(Double price);

    /**
     * Search room by type
     *
     * @param roomType seached
     * @return List<T> searched room list
     */
    List<T> searchByType(RoomType roomType);


}
