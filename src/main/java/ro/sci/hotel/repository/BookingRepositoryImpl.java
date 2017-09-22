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
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;

/**
 * Implementation of Booking Repository Interface
 */
@Repository("bookingRepository")
public class BookingRepositoryImpl extends BaseRepository implements BookingRepository<Booking> {

    private static final String DATABASE_ERROR = "Database error!";

    private static final String EXCEPTION_THROWN = "Exception thrown";

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    private static final String WRITING_IN_DB_HAS_FINISHED = "Writing in db has finished!";

    private static final String SQL_INSERT_INTO_BOOKING_ROOMNUMBER_CUSTOMERID_STARTDATE_ENDDATE_VALUES =
            "INSERT INTO booking(roomnumber,customerid,startdate,enddate) values(?,?,?,?)";

    private static final String BOOKING_DELETE_HAS_COMPLETED = "Deletion of booking completed";

    private static final String SQL_UPDATE_BOOKING_WHERE_ID = "UPDATE booking " + "SET roomnumber=?, customerid=?, startdate=?, enddate=? WHERE id = ?";

    private static final String BOOKING_UPDATE_IN_DB_HAS_COMPLETED = "Booking update in db has completed";

    private static final String SQL_SELECT_ALL__FROM_BOOKING = "SELECT * FROM booking";

    private static final String ID = "id";

    private static final String ROOMNUMBER = "roomnumber";

    private static final String CUSTOMERID = "customerid";

    private static final String STARTDATE = "startdate";

    private static final String ENDDATE = "enddate";

    private static final String SQL_DELETE_FROM_BOOKING_WHERE_ID = "DELETE FROM booking where id=?";


    @Override
    public List<Booking> getAll() {
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL_SELECT_ALL__FROM_BOOKING)) {

            while (rs.next()) {

                Booking booking = new Booking();
                Room room = new Room();
                Customer customer = new Customer();
                booking.setId(rs.getInt(ID));
                room.setRoomNumber(rs.getInt(ROOMNUMBER));
                customer.setId(rs.getInt(CUSTOMERID));
                booking.setRoom(room);
                booking.setCustomer(customer);
                booking.setStartDate(rs.getDate(STARTDATE));
                booking.setEndDate(rs.getDate(ENDDATE));

                bookings.add(booking);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return bookings;
    }

    @Override
    public void create(Booking booking, Room room, Customer customer) {

        try (Connection conn = newConnection();
                PreparedStatement stm = conn.prepareStatement(SQL_INSERT_INTO_BOOKING_ROOMNUMBER_CUSTOMERID_STARTDATE_ENDDATE_VALUES)) {


            stm.setInt(1, room.getRoomNumber());
            stm.setInt(2, customer.getId());
            stm.setDate(3, booking.getStartDate());
            stm.setDate(4, booking.getEndDate());

            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, WRITING_IN_DB_HAS_FINISHED);
    }

    @Override
    public void delete(Booking booking) {
        //delete by id
        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_DELETE_FROM_BOOKING_WHERE_ID)) {

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

            stm.setInt(1, booking.getRoom()
                                 .getRoomNumber());
            stm.setInt(2, booking.getCustomer()
                                 .getId());
            stm.setDate(3, booking.getStartDate());
            stm.setDate(4, booking.getEndDate());

            stm.setInt(5, booking.getId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, BOOKING_UPDATE_IN_DB_HAS_COMPLETED);
    }

    @Override //to update
    public List<Booking> searchByCustomerId(Integer customerId) {

        List<Booking> searchedBookings = new ArrayList<>();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement("SELECT * FROM booking WHERE customerid=?")) {

            stm.setInt(1, customerId);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking();
                Room room = new Room();
                Customer customer = new Customer();
                booking.setId(rs.getInt(ID));
                //to verify these 2 initializations
                room.setRoomNumber(rs.getInt(ROOMNUMBER));
                customer.setId(rs.getInt(CUSTOMERID));
                booking.setStartDate(rs.getDate(STARTDATE));
                booking.setEndDate(rs.getDate(ENDDATE));

                searchedBookings.add(booking);
            }


        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return searchedBookings;
    }

    @Override //to update
    public List<Booking> searchByRoomNumber(Integer roomNumber) {
        List<Booking> searchedBookings = new ArrayList<>();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement("SELECT * FROM booking WHERE roomnumber=?")) {

            stm.setInt(1, roomNumber);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking();
                Room room = new Room();
                Customer customer = new Customer();
                booking.setId(rs.getInt(ID));
                //to verify these 2 initializations
                room.setRoomNumber(rs.getInt(ROOMNUMBER));
                customer.setId(rs.getInt(CUSTOMERID));
                booking.setStartDate(rs.getDate(STARTDATE));
                booking.setEndDate(rs.getDate(ENDDATE));

                searchedBookings.add(booking);
            }


        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return searchedBookings;
    }

    @Override //to update
    public List<Booking> searchByDate(Date startDate, Date endDate) {
        List<Booking> searchedBookings = new ArrayList<>();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement("SELECT * FROM booking WHERE startdate=? AND enddate=?")) {

            stm.setDate(1, startDate);
            stm.setDate(2, endDate);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking();
                Room room = new Room();
                Customer customer = new Customer();
                booking.setId(rs.getInt(ID));
                //to verify these 2 initializations
                room.setRoomNumber(rs.getInt(ROOMNUMBER));
                customer.setId(rs.getInt(CUSTOMERID));
                booking.setStartDate(rs.getDate(STARTDATE));
                booking.setEndDate(rs.getDate(ENDDATE));

                searchedBookings.add(booking);
            }


        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return searchedBookings;
    }

    @Override //to update
    public List<Booking> searchByPrice(Double price) {
        List<Booking> searchedBookings = new ArrayList<>();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement("SELECT * FROM booking WHERE price=?")) {

            stm.setDouble(1, price);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking();
                Room room = new Room();
                Customer customer = new Customer();
                booking.setId(rs.getInt(ID));
                //to verify these 2 initializations
                room.setRoomNumber(rs.getInt(ROOMNUMBER));
                customer.setId(rs.getInt(CUSTOMERID));
                booking.setStartDate(rs.getDate(STARTDATE));
                booking.setEndDate(rs.getDate(ENDDATE));

                searchedBookings.add(booking);
            }


        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return searchedBookings;
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
