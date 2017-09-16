package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;

/**
 * Implementation of Booking Repository Interface
 */
@Repository
public class BookingRepositoryImpl extends BaseRepository implements BookingRepository<Booking> {

    private static final String DATABASE_ERROR = "Database error!";

    private static final String EXCEPTION_THROWN = "Exception thrown";

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    private static final String WRITING_IN_DB_HAS_FINISHED = "Writing in db has finished!";

    private static final String SQL_INSERT_INTO_BOOKING_ROOMNUMBER_CUSTOMERID_STARTDATE_ENDDATE_VALUES =
            "INSERT INTO booking(roomnumber,customerid,startdate,enddate) values(?,?,?,?)";

    private static final String SQL_UPDATE_ROOM_SET_STARTDATE_ENDDATE_WHERE_ID = "UPDATE room SET (startdate,enddate)=(?,?) WHERE id = ?";

    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public void create(Integer customerId, String startDate, String endDate, Integer roomNumber) {

        Room room = new Room();
        Customer customer = new Customer();

        try (Connection conn = newConnection();
                PreparedStatement stm = conn.prepareStatement(SQL_INSERT_INTO_BOOKING_ROOMNUMBER_CUSTOMERID_STARTDATE_ENDDATE_VALUES)) {


            stm.setInt(1, roomNumber);
            stm.setInt(2, customerId);
            stm.setDate(3, Date.valueOf(startDate));
            stm.setDate(4, Date.valueOf(endDate));

            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, WRITING_IN_DB_HAS_FINISHED);

        //ToDo check room is not booked by comparing enddate; needs refactoring

        //write booking interval to room
        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_UPDATE_ROOM_SET_STARTDATE_ENDDATE_WHERE_ID)) {

            stm.setDate(1, Date.valueOf(startDate));
            stm.setDate(2, Date.valueOf(endDate));
            stm.setInt(3, roomNumber);

            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }
    }

    @Override
    public void delete(Booking booking) {

    }

    @Override
    public void update(Booking booking) {

    }

    @Override
    public List<Booking> searchByCustomerId(Integer customerId) {
        return null;
    }

    @Override
    public List<Booking> searchByRoomNumber(Integer roomNumber) {
        return null;
    }

    @Override
    public List<Booking> searchByDate(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Booking> searchByPrice(Double price) {
        return null;
    }

    @Override
    public List<Booking> searchByEvent(Integer eventId) {
        return null;
    }

    @Override
    public List<Booking> searchByCustomerIdAndRoomNumber(Integer customerId, Integer roomNumber) {
        return null;
    }
}
