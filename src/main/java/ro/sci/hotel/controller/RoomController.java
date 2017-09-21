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
import ro.sci.hotel.repository.RoomRepository;
import ro.sci.hotel.repository.RoomRepositoryImpl;
import ro.sci.hotel.service.BookingService;
import ro.sci.hotel.service.BookingServiceImpl;
import ro.sci.hotel.service.RoomService;
import ro.sci.hotel.service.RoomServiceImpl;

/**
 * Controller for room model
 */
@Controller
public class RoomController {

    private RoomRepository<Room> roomRepository;
    private RoomService<Room> roomService;

    private void init() {
        this.roomRepository = new RoomRepositoryImpl();
        this.roomService = new RoomServiceImpl();
        this.roomService.setRoomRepository(roomRepository);
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public ModelAndView showRooms() {
        init();

        return new ModelAndView("rooms", "rooms", roomService.getAll());
    }

//    //not tested
//    @RequestMapping(value = "/submit", method = RequestMethod.GET)
//    public String bookingForm(Model model) {
//        model.addAttribute("booking", new Booking());
//        model.addAttribute("room", new Room());
//        model.addAttribute("customer", new Customer());
//        return "submit";
//    }
//
//    //not tested
//    @RequestMapping(value = "/submit", method = RequestMethod.POST)
//    public String createBooking(@ModelAttribute Booking booking, @ModelAttribute Room room, @ModelAttribute Customer customer, Model model) {
//        model.addAttribute("booking", booking);
//        model.addAttribute("room", room);
//        model.addAttribute("customer", customer);
//        roomService.create(booking, room, customer);
//
//        return "results";
//    }
}
