package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        model.addAttribute("booking", booking);

        bookingService.create(booking, room, customer);

        return "results";
    }

    //
    //    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    //    public String deleteForm(@ModelAttribute Booking booking, Model model) {
    //        model.addAttribute("booking", booking);
    //
    //        return "deletebooking";
    //    }
    //    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    //    public ModelAndView deleteBooking(@ModelAttribute Booking booking, Model model) {
    //
    //        model.addAttribute("booking", booking);
    //
    //        bookingService.delete(booking);
    //
    //        return new ModelAndView("bookings", "bookings", bookingService.getAll());
    //    }
}
