package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.ValidationException;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.service.BookingService;
import ro.sci.hotel.service.CustomerService;
import ro.sci.hotel.service.PriceService;
import ro.sci.hotel.service.RoomService;

/**
 * Booking model controller
 */
@Controller
public class BookingController {

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    @Autowired
    private BookingService<Booking> bookingService;

    @Autowired
    private CustomerService<Customer> customerService;

    @Autowired
    private PriceService<Price> priceService;

    @Autowired
    private RoomService<Room> roomService;

    /**
     * Show all bookings in database
     *
     * @return bookings.html
     */
    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public ModelAndView showBookings() {

        return new ModelAndView("bookings", "bookings", bookingService.getAll());
    }

    /**
     * Show booking by id
     *
     * @param id number of booking
     * @return updatebooking.html
     */
    @RequestMapping(value = "/bookings/{id}", method = RequestMethod.GET)
    public ModelAndView showForm(@PathVariable("id") Integer id) {

        Booking booking = bookingService.searchById(id);

        return new ModelAndView("updatebooking", "booking", booking);
    }

    /**
     * Submit new booking request method GET
     *
     * @param model booking
     * @return submit.html
     */
    @RequestMapping(value = "/bookings/submit", method = RequestMethod.GET)
    public String bookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "submit";
    }

    /**
     * Post new booking details
     *
     * @param booking  new booking
     * @param room     booking room
     * @param customer booking customer
     * @param model    booking
     * @return results.html
     */
    @RequestMapping(value = "/bookings/submit", method = RequestMethod.POST)
    public String createBooking(@ModelAttribute Booking booking, @ModelAttribute Room room, @ModelAttribute Customer customer, Model model)
            throws ValidationException {

        bookingService.create(booking, room, customer);
        model.addAttribute("booking", booking);

        return "results";
    }

    /**
     * Delete selected booking, GET booking by ID
     *
     * @param id    booking id
     * @param model booking, room, customer
     * @return deletebooking.html
     */
    @RequestMapping(value = "/bookings/delete/{id}", method = RequestMethod.GET)
    public String deleteBookingForm(@PathVariable("id") Integer id, Model model) {

        Booking currentBooking = bookingService.searchById(id);
        Room currentRoom = roomService.searchByRoomNumber(currentBooking.getRoom()
                                                                        .getRoomNumber());
        Customer currentCustomer = customerService.searchByCustomerId(currentBooking.getCustomer()
                                                                                    .getId());

        model.addAttribute("booking", currentBooking);
        model.addAttribute("room", currentRoom);
        model.addAttribute("customer", currentCustomer);

        return "deletebooking";
    }

    /**
     * Delete selected booking, request method POST
     *
     * @param id    booking id
     * @param model booking
     * @return bookings.html
     */
    @RequestMapping(value = "/bookings/delete/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView deleteBooking(@PathVariable("id") Integer id, Model model) {

        LOGGER.log(Level.INFO, "Deleting booking with id " + id);

        Booking booking = bookingService.searchById(id);
        bookingService.delete(booking);

        model.addAttribute("booking", booking);

        return new ModelAndView("bookings", "bookings", bookingService.getAll());
    }

    /**
     * Update selected booking's room and starDate and endDate
     *
     * @param id      booking id
     * @param booking booking to be updated
     * @return bookings.html
     */
    @RequestMapping(value = "/bookings/{id}", method = RequestMethod.POST)
    public ModelAndView updateBooking(@PathVariable("id") Integer id, @ModelAttribute Booking booking) {

        LOGGER.log(Level.INFO, "Updating booking");
        Booking updatedBooking = bookingService.searchById(id);
        updatedBooking.setRoom(roomService.searchByRoomNumber(booking.getRoom()
                                                                     .getRoomNumber()));
        updatedBooking.setStartDate(booking.getStartDate());
        updatedBooking.setEndDate(booking.getEndDate());

        bookingService.update(updatedBooking);

        return new ModelAndView("bookings", "bookings", bookingService.getAll());
    }

    /**
     * Search bookings by roomNumber
     *
     * @param roomNumber the number of room
     * @return bookingsbyroomnumber.html
     */
    @RequestMapping(value = "/bookings/search/roomnumber", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView bookingsByRoomNumber(@RequestParam(value = "search", required = false, defaultValue = "0") Integer roomNumber) {

        List<Booking> bookings = bookingService.searchByRoomNumber(roomNumber);

        return new ModelAndView("bookingsbyroomnumber", "search", bookings);

    }

    /**
     * Show available rooms by Date
     *
     * @param startDate begining of stay
     * @param endDate   end of stay
     * @return availablerooms.html
     */
    @RequestMapping(value = "/bookings/search/availablerooms", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView availableRooms(@RequestParam(value = "search", required = false, defaultValue = "2017-01-01") String startDate,
            @RequestParam(value = "search2", required = false, defaultValue = "2017-01-01") String endDate) {

        List<Room> rooms = bookingService.searchAvailableRoomsByDate(Date.valueOf(startDate), Date.valueOf(endDate));

        return new ModelAndView("availablerooms", "search", rooms);
    }
}
