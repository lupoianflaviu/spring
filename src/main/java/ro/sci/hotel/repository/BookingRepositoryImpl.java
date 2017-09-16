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
    private static final String WRITING_IN_OUTCARS_HAS_FINISHED = "Writing in db has finished!";
    private static final String ROOM_IS_ALREADY_BOOKED = "Room is already booked";

    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public void create(Integer customerId, Date startDate, Date endDate, Integer roomNumber) {

        Room room = new Room();
        Customer customer = new Customer();

        try (Connection conn = newConnection();
                PreparedStatement stm = conn.prepareStatement("INSERT INTO booking(roomnumber,customerid,startdate,enddate) values(?,?,?,?)")) {


            stm.setInt(1, roomNumber);
            stm.setInt(2, customerId);
            stm.setDate(3, startDate);
            stm.setDate(4, endDate);

            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, WRITING_IN_OUTCARS_HAS_FINISHED);

        //ToDo check room is not booked by comparing enddate; needs refactoring
        if (Integer.valueOf(endDate.toString()) > Integer.valueOf(room.getEndDate()
                                                                      .toString())) {

            //write booking interval to room
            try (Connection conn = newConnection();
                    PreparedStatement stm = conn.prepareStatement("INSERT INTO room(startdate,enddate) values(?,?) WHERE id = ?")) {


                stm.setDate(1, startDate);
                stm.setDate(2, endDate);
                stm.setInt(3, roomNumber);

                stm.execute();

            } catch (SQLException ex) {
                LOGGER.log(Level.WARNING, DATABASE_ERROR);
                throw new RuntimeException(EXCEPTION_THROWN);
            }
        } else {
            LOGGER.log(Level.WARNING, ROOM_IS_ALREADY_BOOKED);
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
