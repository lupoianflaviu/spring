package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.util.Currency;
import ro.sci.hotel.model.util.Price;

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
            "INSERT INTO booking(roomnumber,customerid,startdate,enddate,price,currency) values(?,?,?,?,?,?)";

    private static final String SQL_UPDATE_ROOM_SET_STARTDATE_ENDDATE_WHERE_ID = "UPDATE room SET (startdate,enddate)=(?,?) WHERE id = ?";

    private static final String BOOKING_DELETE_HAS_COMPLETED = "Deletion of booking completed";

    private static final String SQL_UPDATE_BOOKING_WHERE_ID =
            "UPDATE booking " + "SET roomnumber=?, customerid=?, startdate=?, enddate=?, price=?, currency=? WHERE id = ?";

    private static final String BOOKING_UPDATE_IN_DB_HAS_COMPLETED = "Booking update in db has completed";

    private static final String SQL_SELECT_ALL__FROM_BOOKING = "SELECT * FROM booking";

    private static final String ID = "id";

    private static final String ROOMNUMBER = "roomnumber";

    private static final String CUSTOMERID = "customerid";

    private static final String STARTDATE = "startdate";

    private static final String ENDDATE = "enddate";

    private static final String PRICE = "price";

    private static final String CURRENCY = "currency";

    @Override
    public List<Booking> getAll() {
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL_SELECT_ALL__FROM_BOOKING)) {

            while (rs.next()) {

                Booking booking = new Booking();
                booking.setId(rs.getInt(ID));
                booking.setRoomNumber(rs.getInt(ROOMNUMBER));
                booking.setCustomerId(rs.getInt(CUSTOMERID));
                booking.setStartDate(rs.getDate(STARTDATE));
                booking.setEndDate(rs.getDate(ENDDATE));
                booking.setPricePerDay(new Price(rs.getDouble(PRICE), Currency.valueOf(rs.getString(CURRENCY))));

                bookings.add(booking);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return bookings;
    }

    @Override
    public void create(Booking booking) {

        try (Connection conn = newConnection();
                PreparedStatement stm = conn.prepareStatement(SQL_INSERT_INTO_BOOKING_ROOMNUMBER_CUSTOMERID_STARTDATE_ENDDATE_VALUES)) {


            stm.setInt(1, booking.getRoomNumber());
            stm.setInt(2, booking.getCustomerId());
            stm.setDate(3, booking.getStartDate());
            stm.setDate(4, booking.getEndDate());
            stm.setDouble(5, booking.getPricePerDay()
                                    .getValue());
            stm.setString(6, booking.getPricePerDay()
                                    .getCurrency()
                                    .toString());

            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, WRITING_IN_DB_HAS_FINISHED);

        //write booking interval to room
        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_UPDATE_ROOM_SET_STARTDATE_ENDDATE_WHERE_ID)) {

            stm.setDate(1, booking.getStartDate());
            stm.setDate(2, booking.getEndDate());
            stm.setInt(3, booking.getRoomNumber());

            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }
    }

    @Override
    public void delete(Booking booking) {
        //delete by id
        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement("DELETE FROM booking where id=?")) {

            stm.setInt(1, booking.getId());
            stm.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, BOOKING_DELETE_HAS_COMPLETED);
    }

    @Override
    public void update(Booking booking) {
        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_UPDATE_BOOKING_WHERE_ID)) {

            stm.setInt(1, booking.getRoomNumber());
            stm.setInt(2, booking.getRoomNumber());
            stm.setDate(3, booking.getStartDate());
            stm.setDate(4, booking.getEndDate());
            stm.setDouble(5, booking.getPricePerDay()
                                    .getValue());
            stm.setString(6, booking.getPricePerDay()
                                    .getCurrency()
                                    .toString());

            stm.setInt(7, booking.getId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, BOOKING_UPDATE_IN_DB_HAS_COMPLETED);
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
