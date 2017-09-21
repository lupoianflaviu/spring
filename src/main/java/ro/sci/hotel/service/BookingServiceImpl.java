package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.repository.BookingRepository;

/**
 * Implementation for Booking Service interface
 */
@Service("bookingService")
public class BookingServiceImpl implements BookingService<Booking> {

    @Autowired
    private BookingRepository<Booking> bookingRepository;

    @Autowired
    private RoomService<Room> roomService;

    @Autowired
    private CustomerService<Customer> customerService;



    @Override
    public List<Booking> getAll() {
        //add room and customer

        List<Booking> bookings = this.bookingRepository.getAll();

        for (Booking booking : bookings) {

            int roomNumber = booking.getRoom()
                                    .getRoomNumber();

            Room resultRoom = roomService.searchByRoomNumber(roomNumber);
            booking.setRoom(resultRoom);

            int customerId = booking.getCustomer()
                                    .getId();
            Customer resultCustomer = customerService.searchByCustomerId(customerId);
            booking.setCustomer(resultCustomer);
        }

        return bookings;
    }

    @Override
    public void create(Booking booking, Room room, Customer customer) {
        this.bookingRepository.create(booking, room, customer);
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

    @Override
    public void setBookingRepository(BookingRepository<Booking> bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
}
