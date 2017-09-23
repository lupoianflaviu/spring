package ro.sci.hotel.repository;

import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;

/**
 * Booking repository interface for DAO
 */
public interface BookingRepository<T> {

    /**
     * Read all booking from db
     *
     * @return List<T> Booking lists
     */
    @Select("SELECT * FROM booking")
    List<T> getAll();

    /**
     * Create a new booking
     *
     * @param booking to be added in db
     * @param room that is booked
     * @param customer that is booking the room
     */
    void create(Booking booking, Room room, Customer customer);

    /**
     * Detele a booking entry from db
     *
     * @param t Booking to be deleted
     */
    void delete(T t);

    /**
     * Update a booking entry in db
     *
     * @param t Booking to pe updated
     */
    void update(T t);

    /**
     * Search bookings by customer id
     *
     * @param customerId searched
     * @return List<T> searched bookings list
     */
    List<T> searchByCustomerId(Integer customerId);

    /**
     * Search bookings by room number
     *
     * @param roomNumber searched
     * @return List<T> searched bookings list
     */
    List<T> searchByRoomNumber(Integer roomNumber);

    /**
     * Search booking by interval
     *
     * @param startDate date of arrival
     * @param endDate   date of departure
     * @return List<T> searched bookings list
     */
    List<T> searchByDate(Date startDate, Date endDate);

    /**
     * Search bookings by price
     *
     * @param price seached
     * @return List<T> searched bookings list
     */
    List<T> searchByPrice(Double price);

    /**
     * Search bookings by event id
     *
     * @param eventId searched
     * @return List<T> searched bookings list
     */
    List<T> searchByEvent(Integer eventId);

    /**
     * Search bookings by customer id and room number
     *
     * @param customerId searched
     * @param roomNumber searched
     * @return List<T> searched bookings list
     */
    List<T> searchByCustomerIdAndRoomNumber(Integer customerId, Integer roomNumber);
}
