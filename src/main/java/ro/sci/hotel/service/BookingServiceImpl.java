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
            //set Room for Booking
            int roomNumber = booking.getRoom()
                                    .getRoomNumber();
            Room resultRoom = roomService.searchByRoomNumber(roomNumber);
            booking.setRoom(resultRoom);

            //set Customer for Booking
            int customerId = booking.getCustomer()
                                    .getId();
            Customer resultCustomer = customerService.searchByCustomerId(customerId);
            booking.setCustomer(resultCustomer);

            //set TotalBookingPrice for Booking
            booking.setTotalBookingPrice(booking.getTotalBookingPrice() * resultRoom.getPricePerNight()
                                                                                    .getValue());
        }

        bookings.sort(Comparator.comparing(Booking::getId));

        return bookings;
    }

    @Override
    public void create(Booking booking, Room room, Customer customer) {
        List<Booking> bookings = getAll();

        //check if same Room and is not booked
        for (Booking savedBooking : bookings) {

            if (isSameRoom(booking, savedBooking)) {

                if ((booking.getStartDate()
                            .compareTo(booking.getEndDate()) >= 0) || (searchByDate(booking.getStartDate(), booking.getEndDate()).size() > 0)) {

                    LOGGER.log(Level.WARNING, "Wrong period is given!");
                    throw new ValidationException("Wrong period is given!");
                }
            }
        }

        this.bookingRepository.create(booking, room, customer);
    }

    /**
     * Returns true if two bookings have the same room assigned. Used in create and update booking methods
     *
     * @param booking      First booking
     * @param savedBooking Second booking
     * @return true if it is the same room / false if the rooms are different
     */
    private boolean isSameRoom(Booking booking, Booking savedBooking) {
        return booking.getRoom()
                      .getRoomNumber() == savedBooking.getRoom()
                                                      .getRoomNumber();
    }

    @Override
    public void delete(Booking booking) {
        this.bookingRepository.delete(booking);
    }

    @Override
    public void update(Booking booking) {
        List<Booking> bookings = getAll();

        //check if same Room and is not booked
        for (Booking savedBooking : bookings) {

            if ((isSameRoom(booking, savedBooking))) {

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
            //Set Room for booking
            Room resultRoom = roomService.searchByRoomNumber(roomNumber);

            booking.setRoom(resultRoom);

            //Set Customer for booking
            int customerId = booking.getCustomer()
                                    .getId();
            Customer resultCustomer = customerService.searchByCustomerId(customerId);
            booking.setCustomer(resultCustomer);

            //Set TotalBookingPrice for booking
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

            //Set Room for booking
            int roomNumber = booking.getRoom()
                                    .getRoomNumber();
            Room resultRoom = roomService.searchByRoomNumber(roomNumber);
            booking.setRoom(resultRoom);

            //Set Customer for booking
            int customerId = booking.getCustomer()
                                    .getId();
            Customer resultCustomer = customerService.searchByCustomerId(customerId);
            booking.setCustomer(resultCustomer);

            //Set TotalBookingPrice for booking
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

            Price resultPrice = priceService.searchById(room.getPricePerNight()
                                                            .getId());
            room.setPricePerNight(resultPrice);
        }

        List<Room> availableRooms = (List<Room>) CollectionUtils.subtract(roomService.getAll(), reservedRooms);

        availableRooms.sort(Comparator.comparing(Room::getRoomNumber));

        return availableRooms;

    }

    @Override
    public Booking searchById(Integer bookingId) {

        Booking booking = this.bookingRepository.searchById(bookingId);
        //Set Room for booking
        int roomNumber = booking.getRoom()
                                .getRoomNumber();
        Room resultRoom = roomService.searchByRoomNumber(roomNumber);
        booking.setRoom(resultRoom);

        //Set Customer for booking
        int customerId = booking.getCustomer()
                                .getId();
        Customer resultCustomer = customerService.searchByCustomerId(customerId);
        booking.setCustomer(resultCustomer);

        //Set TotalBookingPrice for booking
        booking.setTotalBookingPrice(booking.getTotalBookingPrice() * resultRoom.getPricePerNight()
                                                                                .getValue());

        return booking;
    }
}
