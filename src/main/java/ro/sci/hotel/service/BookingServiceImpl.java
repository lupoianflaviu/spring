package ro.sci.hotel.service;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.repository.BookingRepository;

/**
 * Implementation for Booking Service interface
 */
@Service
public class BookingServiceImpl implements BookingService<Booking> {

    private BookingRepository<Booking> bookingRepository;

    @Override
    public List<Booking> getAll() {
        return this.bookingRepository.getAll();
    }

    @Override
    public void create(Integer customerId, Date startDate, Date endDate, Integer roomNumber) {
        this.bookingRepository.create(customerId, startDate, endDate, roomNumber);
    }

    @Override
    public void delete(Booking booking) {
        this.bookingRepository.delete(booking);
    }

    @Override
    public void update(Booking booking) {
        this.bookingRepository.update(booking);
    }

    @Override
    public List<Booking> searchByCustomerId(Integer customerId) {
        return this.bookingRepository.searchByCustomerId(customerId);
    }

    @Override
    public List<Booking> searchByRoomNumber(Integer roomNumber) {
        return this.bookingRepository.searchByRoomNumber(roomNumber);
    }

    @Override
    public List<Booking> searchByDate(Date startDate, Date endDate) {
        return this.bookingRepository.searchByDate(startDate, endDate);
    }

    @Override
    public List<Booking> searchByPrice(Double price) {
        return this.bookingRepository.searchByPrice(price);
    }

    @Override
    public List<Booking> searchByEvent(Integer eventId) {
        return this.bookingRepository.searchByEvent(eventId);
    }

    @Override
    public List<Booking> searchByCustomerIdAndRoomNumber(Integer customerId, Integer roomNumber) {
        return this.bookingRepository.searchByCustomerIdAndRoomNumber(customerId, roomNumber);
    }

    public void setBookingRepository(BookingRepository<Booking> bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
}
