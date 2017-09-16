package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.hotel.model.booking.Booking;
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

    //not working
    @GetMapping(value = "/submit")
    public String createBooking(Model model) {
        init();

        model.addAttribute("booking", new Booking());

        return "submit";
    }

    //not working
    @PostMapping
    public String showBookingResult(@ModelAttribute Booking booking) {
        init();

        return "results";
    }
}
