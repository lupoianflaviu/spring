package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

//    @Autowired
//    BookingService<Booking> bookingService;
//    BookingRepository<Booking> bookingRepository;

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public ModelAndView showBookings() {
        BookingRepository<Booking> bookingRepository = new BookingRepositoryImpl();
        BookingService<Booking> bookingService = new BookingServiceImpl();
        bookingService.setBookingRepository(bookingRepository);

        bookingService.setBookingRepository(bookingRepository);

        return new ModelAndView("bookings", "bookings", bookingService.getAll());
    }
}
