package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.BookingRepository;
import ro.sci.hotel.repository.BookingRepositoryImpl;
import ro.sci.hotel.repository.RoomRepository;
import ro.sci.hotel.repository.RoomRepositoryImpl;
import ro.sci.hotel.service.*;

/**
 * Controller for room model
 */
@Controller
public class RoomController {

    @Autowired
    private RoomRepository<Room> roomRepository;
    @Autowired
    private RoomService<Room> roomService;
    @Autowired
    private PriceService<Price> priceService;

    // show all rooms
    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public ModelAndView showRooms() {

        return new ModelAndView("rooms", "rooms", roomService.getAll());
    }

    // show room by room number
    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET)
    public ModelAndView roomForm(@PathVariable("id") Integer id) {

        Room room = roomService.searchByRoomNumber(id);

        return new ModelAndView("viewroom", "room", room);
    }

//submit a new room

    @RequestMapping(value = "/rooms/submit", method = RequestMethod.GET)
    public String roomForm(Model model) {
        model.addAttribute("room", new Room());
        return "submitroom";
    }

    @RequestMapping(value = "/rooms/submit", method = RequestMethod.POST)
    public String createRoom(@ModelAttribute Room room, @ModelAttribute Price price, Model model) {

        roomService.create(room, price);
        model.addAttribute("room", room);

        return "resultsroom";
    }
}
