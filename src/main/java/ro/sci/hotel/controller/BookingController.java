package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public ModelAndView showBookings() {

        return new ModelAndView("bookings", "bookings", bookingService.getAll());
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String bookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "submit";
    }

    //Room is null
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String createBooking(@ModelAttribute Booking booking, @ModelAttribute Room room, @ModelAttribute Customer customer, Model model) {

        bookingService.create(booking, room, customer);
        model.addAttribute("booking", booking);

        return "results";
    }

    @RequestMapping(value = "/bookings/delete/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String deleteBooking(@PathVariable("id") Integer id, Model model) {

        LOGGER.log(Level.INFO, "Deleting booking with id " + id);

        Booking booking = bookingService.searchById(id);
        bookingService.delete(booking);

        model.addAttribute("booking", booking);

        return "results";
    }

    @RequestMapping(value = "/bookings/update", method = RequestMethod.GET)
    public String updateBooking(Model model) {

        model.addAttribute("booking", new Booking());

        return "updatebooking";
    }

    @RequestMapping(value = "/bookings/update", method = RequestMethod.PUT)
    public String updateBooking(@ModelAttribute Booking booking, Model model) {

        LOGGER.log(Level.INFO, "Updating booking");
        
        bookingService.update(booking);

        model.addAttribute("booking", booking);

        return "results";
    }
}
