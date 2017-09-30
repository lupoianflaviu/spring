package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.BookingRepository;

/**
 * Implementation for Booking Service interface
 */
@Service("bookingService")
public class BookingServiceImpl implements BookingService<Booking> {

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    @Autowired
    private BookingRepository<Booking> bookingRepository;

    @Autowired
    private RoomService<Room> roomService;

    @Autowired
    private CustomerService<Customer> customerService;

    @Autowired
    private PriceService<Price> priceService;

    @Override
    public List<Booking> getAll() {


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

        bookings.sort(Comparator.comparing(Booking::getId));

        return bookings;
    }

    @Override
    public void create(Booking booking, Room room, Customer customer) {
        //to check logic
        List<Booking> bookings = getAll();

        for (Booking savedBooking : bookings) {

            if (booking.getRoom() == savedBooking.getRoom() && (booking.getStartDate()
                                                                       .compareTo(savedBooking.getEndDate()) <= 0 || booking.getEndDate()
                                                                                                                            .compareTo(
                                                                                                                                    savedBooking.getStartDate())
                    >= 0 || booking.getStartDate()
                                   .compareTo(booking.getEndDate()) >= 0)) {
                LOGGER.log(Level.WARNING, "Selected Room is already reserved OR wrong period is given!");
                return;
            }
        }

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

    @Override
    public Booking searchById(Integer bookingId) {

        Booking booking = this.bookingRepository.searchById(bookingId);
        int roomNumber = booking.getRoom()
                                .getRoomNumber();

        Room resultRoom = roomService.searchByRoomNumber(roomNumber);

        booking.setRoom(resultRoom);

        int customerId = booking.getCustomer()
                                .getId();
        Customer resultCustomer = customerService.searchByCustomerId(customerId);
        booking.setCustomer(resultCustomer);

        return booking;
    }

    public void setRoomService(RoomService<Room> roomService) {
        this.roomService = roomService;
    }

    public void setCustomerService(CustomerService<Customer> customerService) {
        this.customerService = customerService;
    }
}
