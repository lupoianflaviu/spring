package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Level;
import java.util.logging.Logger;

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

    // ------------------- Show All Bookings ------------------------------------------------
    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public ModelAndView showBookings() {

        return new ModelAndView("bookings", "bookings", bookingService.getAll());
    }

    // ------------------- Show Desired Bookings ------------------------------------------------
    @RequestMapping(value = "/bookings/{id}", method = RequestMethod.GET)
    public ModelAndView showForm(@PathVariable("id") Integer id) {

        Booking booking = bookingService.searchById(id);

        return new ModelAndView("viewbooking", "booking", booking);
    }

    // ------------------- Submit New Booking ------------------------------------------------
    @RequestMapping(value = "/bookings/submit", method = RequestMethod.GET)
    public String bookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "submit";
    }

    @RequestMapping(value = "/bookings/submit", method = RequestMethod.POST)
    public String createBooking(@ModelAttribute Booking booking, @ModelAttribute Room room, @ModelAttribute Customer customer, Model model) {

        bookingService.create(booking, room, customer);
        model.addAttribute("booking", booking);

        return "results";
    }

    // ------------------- Delete a Booking ------------------------------------------------
    //not working
    @RequestMapping(value = "/bookings/delete/{id}", method = RequestMethod.GET)
    public String deleteBookingForm(@PathVariable("id") Integer id,  Model model) {

        Booking currentBooking = bookingService.searchById(id);
        Room currentRoom = roomService.searchByRoomNumber(currentBooking.getRoom().getRoomNumber());
        Customer currentCustomer = customerService.searchByCustomerId(currentBooking.getCustomer().getId());

        model.addAttribute("booking", currentBooking);
        model.addAttribute("room", currentRoom);
        model.addAttribute("customer", currentCustomer);

        return "deletebooking";
    }


    //not working
    @RequestMapping(value = "/bookings/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteBooking(@PathVariable("id") Integer id, Model model) {

        LOGGER.log(Level.INFO, "Deleting booking with id " + id);

        Booking booking = bookingService.searchById(id);
        bookingService.delete(booking);

        model.addAttribute("booking", booking);

        return "deletebooking";
    }

    // ------------------- Update a Booking ------------------------------------------------
    //needs testing
    @RequestMapping(value = "/bookings/update/{id}", method = RequestMethod.GET)
    public String updateBookingForm(@PathVariable("id") Integer id,  Model model) {

        Booking currentBooking = bookingService.searchById(id);
        Room currentRoom = roomService.searchByRoomNumber(currentBooking.getRoom().getRoomNumber());
        Customer currentCustomer = customerService.searchByCustomerId(currentBooking.getCustomer().getId());

        model.addAttribute("booking", currentBooking);

        return "updatebooking";
    }
//needs testing
        @RequestMapping(value = "/bookings/update/{id}", method = RequestMethod.PUT)
        public String updateBooking(@PathVariable("id") Integer id, @RequestBody Booking booking) {
            LOGGER.log(Level.INFO, "Updating booking");

            Booking updatedBooking = new Booking();
            updatedBooking.setId(booking.getId());
            updatedBooking.setCustomer(booking.getCustomer());
            updatedBooking.setRoom(roomService.searchByRoomNumber(booking.getRoom().getRoomNumber()));
//            updatedBooking.setRoom(booking.getRoom());
            updatedBooking.setStartDate(booking.getStartDate());
            updatedBooking.setEndDate(booking.getEndDate());
            updatedBooking.setPricePerDay(booking.getPricePerDay());

            bookingService.update(updatedBooking);

            return "updatebooking";
        }
}
