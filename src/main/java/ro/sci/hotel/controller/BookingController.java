package ro.sci.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.repository.BookingRepository;
import ro.sci.hotel.repository.BookingRepositoryImpl;
import ro.sci.hotel.service.BookingService;
import ro.sci.hotel.service.BookingServiceImpl;

/**
 * Booking model controller
 */
@Controller
public class BookingController {

    private BookingRepository<Booking> bookingRepository;
    private BookingService<Booking> bookingService;

    private void init() {
        this.bookingRepository = new BookingRepositoryImpl();
        this.bookingService = new BookingServiceImpl();
        this.bookingService.setBookingRepository(bookingRepository);
    }

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public ModelAndView showBookings() {
        init();

        return new ModelAndView("bookings", "bookings", bookingService.getAll());
    }

    //not tested
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String bookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("room", new Room());
        model.addAttribute("customer", new Customer());
        return "submit";
    }

    //not tested
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String createBooking(@ModelAttribute Booking booking, @ModelAttribute Room room, @ModelAttribute Customer customer, Model model) {
        model.addAttribute("booking", booking);
        model.addAttribute("room", room);
        model.addAttribute("customer", customer);
        bookingService.create(booking, room, customer);

        return "results";
    }
}
