package ro.sci.hotel.service;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ValidationException;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.BookingRepository;

/**
 * Implementation for Booking Service interface
 */
@Service()
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

            booking.setTotalBookingPrice(booking.getTotalBookingPrice() * resultRoom.getPricePerNight()
                                                                                    .getValue());
        }

        bookings.sort(Comparator.comparing(Booking::getId));

        return bookings;
    }

    @Override
    public void create(Booking booking, Room room, Customer customer) {
        List<Booking> bookings = getAll();

        for (Booking savedBooking : bookings) {
            if ((booking.getRoom()
                        .getRoomNumber() == savedBooking.getRoom()
                                                        .getRoomNumber())) {
                if ((booking.getStartDate()
                            .compareTo(booking.getEndDate()) >= 0) || (searchByDate(booking.getStartDate(), booking.getEndDate()).size() > 0)) {

                    LOGGER.log(Level.WARNING, "Wrong period is given!");
                    throw new ValidationException("Wrong period is given!");
                }
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
        List<Booking> bookings = getAll();

        for (Booking savedBooking : bookings) {
            if ((booking.getRoom()
                        .getRoomNumber() == savedBooking.getRoom()
                                                        .getRoomNumber())) {
                if ((booking.getStartDate()
                            .compareTo(booking.getEndDate()) >= 0) || (searchByDate(booking.getStartDate(), booking.getEndDate()).size() > 0)) {

                    LOGGER.log(Level.WARNING, "Wrong period is given!");
                    throw new ValidationException("Wrong period is given!");
                }
            }
        }

        this.bookingRepository.update(booking);
    }

    @Override
    public List<Booking> searchByCustomerId(Integer customerId) {
        return this.bookingRepository.searchByCustomerId(customerId);
    }

    @Override
    public List<Booking> searchByRoomNumber(Integer roomNumber) {

        List<Booking> bookings = this.bookingRepository.searchByRoomNumber(roomNumber);

        for (Booking booking : bookings) {

            Room resultRoom = roomService.searchByRoomNumber(roomNumber);

            booking.setRoom(resultRoom);

            int customerId = booking.getCustomer()
                                    .getId();
            Customer resultCustomer = customerService.searchByCustomerId(customerId);
            booking.setCustomer(resultCustomer);

            booking.setTotalBookingPrice(booking.getTotalBookingPrice() * resultRoom.getPricePerNight()
                                                                                    .getValue());
        }

        bookings.sort(Comparator.comparing(Booking::getId));

        return bookings;
    }

    @Override
    public List<Booking> searchByDate(Date startDate, Date endDate) {
        List<Booking> bookings = this.bookingRepository.searchByDate(startDate, endDate);

        for (Booking booking : bookings) {

            int roomNumber = booking.getRoom()
                                    .getRoomNumber();

            Room resultRoom = roomService.searchByRoomNumber(roomNumber);

            booking.setRoom(resultRoom);

            int customerId = booking.getCustomer()
                                    .getId();
            Customer resultCustomer = customerService.searchByCustomerId(customerId);
            booking.setCustomer(resultCustomer);

            booking.setTotalBookingPrice(booking.getTotalBookingPrice() * resultRoom.getPricePerNight()
                                                                                    .getValue());
        }

        bookings.sort(Comparator.comparing(Booking::getId));

        return bookings;
    }

    @Override
    public List<Booking> searchByPrice(Double price) {
        return this.bookingRepository.searchByPrice(price);
    }

    @Override
    public List<Room> searchAvailableRoomsByDate(Date startDate, Date endDate) {

        List<Booking> bookings = searchByDate(startDate, endDate);
        List<Room> reservedRooms = new ArrayList<>();

        for (Booking booking : bookings) {

            reservedRooms.add(booking.getRoom());
        }

        for (Room room : reservedRooms) {

            Price resultPrice = priceService.searchById(room.getPricePerNight().getId());
            room.setPricePerNight(resultPrice);
        }

        List<Room> availableRooms = (List<Room>) CollectionUtils.subtract(roomService.getAll(), reservedRooms);

        availableRooms.sort(Comparator.comparing(Room::getRoomNumber));
        return availableRooms;

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

        booking.setTotalBookingPrice(booking.getTotalBookingPrice() * resultRoom.getPricePerNight()
                                                                                .getValue());

        return booking;
    }

    public void setRoomService(RoomService<Room> roomService) {
        this.roomService = roomService;
    }

    public void setCustomerService(CustomerService<Customer> customerService) {
        this.customerService = customerService;
    }
}
