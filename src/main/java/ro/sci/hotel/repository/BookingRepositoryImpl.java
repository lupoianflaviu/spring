package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import ro.sci.hotel.model.booking.Booking;

/**
 * Implementation of Booking Repository Interface
 */
@Repository
public class BookingRepositoryImpl extends BaseRepository implements BookingRepository<Booking> {

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public void create(Booking booking) {

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
