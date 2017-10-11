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

import ro.sci.hotel.constants.BookingFlowConstants;
import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;

/**
 * Implementation of Booking Repository Interface
 */
@Repository()
public class BookingRepositoryImpl extends BaseRepository implements BookingRepository<Booking> {

    @Override
    public List<Booking> getAll() {
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = newConnection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(BookingFlowConstants.SQL_SELECT_ALL__FROM_BOOKING)) {

            while (rs.next()) {

                initializeBookingFields(bookings, rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            BookingFlowConstants.LOGGER.log(Level.WARNING, BookingFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(BookingFlowConstants.EXCEPTION_THROWN);
        }

        return bookings;
    }


    /**
     * Provides common initialization of booking fields
     *
     * @param bookings List of bookings result
     * @param rs       ResultSet
     */
    private void initializeBookingFields(List<Booking> bookings, ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        Room room = new Room();
        Customer customer = new Customer();
        booking.setId(rs.getInt(BookingFlowConstants.ID));
        room.setRoomNumber(rs.getInt(BookingFlowConstants.ROOMNUMBER));
        customer.setId(rs.getInt(BookingFlowConstants.CUSTOMERID));
        booking.setRoom(room);
        booking.setCustomer(customer);
        booking.setStartDate(rs.getDate(BookingFlowConstants.STARTDATE));
        booking.setEndDate(rs.getDate(BookingFlowConstants.ENDDATE));
        booking.setTotalBookingPrice(calculateDays(booking.getId()));
        bookings.add(booking);
    }

    @Override
    public void create(Booking booking, Room room, Customer customer) {

        try (Connection conn = newConnection();
                PreparedStatement stm = conn.prepareStatement(BookingFlowConstants.SQL_INSERT_INTO_BOOKING_ROOMNUMBER_CUSTOMERID_STARTDATE_ENDDATE_VALUES)) {


            stm.setInt(1, room.getRoomNumber());
            stm.setInt(2, customer.getId());
            stm.setDate(3, booking.getStartDate());
            stm.setDate(4, booking.getEndDate());

            stm.execute();

        } catch (SQLException ex) {
            BookingFlowConstants.LOGGER.log(Level.WARNING, BookingFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(BookingFlowConstants.EXCEPTION_THROWN);
        }

        BookingFlowConstants.LOGGER.log(Level.INFO, BookingFlowConstants.WRITING_IN_DB_HAS_FINISHED);
    }

    @Override
    public void delete(Booking booking) {

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(BookingFlowConstants.SQL_DELETE_FROM_BOOKING_WHERE_ID)) {

            stm.setInt(1, booking.getId());
            stm.executeUpdate();

        } catch (SQLException ex) {
            BookingFlowConstants.LOGGER.log(Level.WARNING, BookingFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(BookingFlowConstants.EXCEPTION_THROWN);
        }

        BookingFlowConstants.LOGGER.log(Level.INFO, BookingFlowConstants.BOOKING_DELETE_HAS_COMPLETED);
    }

    @Override
    public void update(Booking booking) {
        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(BookingFlowConstants.SQL_UPDATE_BOOKING_WHERE_ID)) {

            stm.setInt(1, booking.getRoom()
                                 .getRoomNumber());
            stm.setInt(2, booking.getCustomer()
                                 .getId());
            stm.setDate(3, booking.getStartDate());
            stm.setDate(4, booking.getEndDate());

            stm.setInt(5, booking.getId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            BookingFlowConstants.LOGGER.log(Level.WARNING, BookingFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(BookingFlowConstants.EXCEPTION_THROWN);
        }

        BookingFlowConstants.LOGGER.log(Level.INFO, BookingFlowConstants.BOOKING_UPDATE_IN_DB_HAS_COMPLETED);
    }

    @Override
    public List<Booking> searchByCustomerId(Integer customerId) {

        List<Booking> searchedBookings = new ArrayList<>();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(BookingFlowConstants.SQL_SELECT_FROM_BOOKING_WHERE_CUSTOMERID)) {

            stm.setInt(1, customerId);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                initializeBookingFields(searchedBookings, rs);
            }


        } catch (SQLException ex) {
            BookingFlowConstants.LOGGER.log(Level.WARNING, BookingFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(BookingFlowConstants.EXCEPTION_THROWN);
        }

        return searchedBookings;
    }

    @Override
    public Double calculateDays(Integer id) {
        Double result = 0d;

        try (Connection conn = newConnection();
                PreparedStatement stm = conn.prepareStatement(BookingFlowConstants.SQL_SELECT_ENDDATE_STARTDATE_FROM_BOOKING_WHERE_ID)) {

            stm.setDouble(1, id);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                result = rs.getDouble("?column?");
            }
        } catch (SQLException ex) {
            BookingFlowConstants.LOGGER.log(Level.WARNING, BookingFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(BookingFlowConstants.EXCEPTION_THROWN);
        }

        return result;
    }

    @Override
    public List<Booking> searchByRoomNumber(Integer roomNumber) {
        List<Booking> searchedBookings = new ArrayList<>();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(BookingFlowConstants.SQL_SELECT_FROM_BOOKING_WHERE_ROOMNUMBER)) {

            stm.setInt(1, roomNumber);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                initializeBookingFields(searchedBookings, rs);
            }


        } catch (SQLException ex) {
            BookingFlowConstants.LOGGER.log(Level.WARNING, BookingFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(BookingFlowConstants.EXCEPTION_THROWN);
        }

        return searchedBookings;
    }

    @Override
    public List<Booking> searchByDate(Date startDate, Date endDate) {
        List<Booking> searchedBookings = new ArrayList<>();

        try (Connection conn = newConnection();
                PreparedStatement stm = conn.prepareStatement(BookingFlowConstants.SQL_SELECT_FROM_BOOKING_WHERE_STARTDATE_AND_ENDDATE)) {

            stm.setDate(1, startDate);
            stm.setDate(2, endDate);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                initializeBookingFields(searchedBookings, rs);
            }


        } catch (SQLException ex) {
            BookingFlowConstants.LOGGER.log(Level.WARNING, BookingFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(BookingFlowConstants.EXCEPTION_THROWN);
        }

        return searchedBookings;
    }

    @Override
    public List<Booking> searchByPrice(Double price) {
        List<Booking> searchedBookings = new ArrayList<>();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(BookingFlowConstants.SQL_SELECT_FROM_BOOKING_WHERE_PRICE)) {

            stm.setDouble(1, price);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                initializeBookingFields(searchedBookings, rs);
            }


        } catch (SQLException ex) {
            BookingFlowConstants.LOGGER.log(Level.WARNING, BookingFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(BookingFlowConstants.EXCEPTION_THROWN);
        }

        return searchedBookings;
    }

    @Override
    public Booking searchById(Integer bookingId) {
        Booking booking = new Booking();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(BookingFlowConstants.SQL_SELECT_FROM_BOOKING_WHERE_ID)) {

            stm.setDouble(1, bookingId);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Room room = new Room();
                Customer customer = new Customer();
                booking.setId(rs.getInt(BookingFlowConstants.ID));
                room.setRoomNumber(rs.getInt(BookingFlowConstants.ROOMNUMBER));
                customer.setId(rs.getInt(BookingFlowConstants.CUSTOMERID));
                booking.setRoom(room);
                booking.setCustomer(customer);
                booking.setStartDate(rs.getDate(BookingFlowConstants.STARTDATE));
                booking.setEndDate(rs.getDate(BookingFlowConstants.ENDDATE));
                booking.setTotalBookingPrice(calculateDays(booking.getId()));
            }


        } catch (SQLException ex) {
            BookingFlowConstants.LOGGER.log(Level.WARNING, BookingFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(BookingFlowConstants.EXCEPTION_THROWN);
        }

        return booking;
    }
}
