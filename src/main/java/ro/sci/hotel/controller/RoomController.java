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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.room.RoomType;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.RoomRepository;
import ro.sci.hotel.service.PriceService;
import ro.sci.hotel.service.RoomService;

/**
 * Controller for room model
 */
@Controller
public class RoomController {

    private static final Logger LOGGER = Logger.getLogger("Hotel");

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

        return new ModelAndView("updateroom", "room", room);
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

    //delete a room
    @RequestMapping(value = "/rooms/delete/{id}", method = RequestMethod.GET)
    public String deleteRoomForm(@PathVariable("id") Integer id, Model model) {

        Room currentRoom = roomService.searchByRoomNumber(id);
        model.addAttribute("room", currentRoom);

        return "deleteroom";
    }

    @RequestMapping(value = "/rooms/delete/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView deleteRoom(@PathVariable("id") Integer id, Model model) {

        LOGGER.log(Level.INFO, "Deleting room with id " + id);

        Room room = roomService.searchByRoomNumber(id);
        roomService.delete(room);

        model.addAttribute("room", room);

        return new ModelAndView("rooms", "rooms", roomService.getAll());
    }

    //update a room
    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.POST)
    public ModelAndView updateRoom(@PathVariable("id") Integer id, @ModelAttribute Room room) {

        LOGGER.log(Level.INFO, "Updating room");
        Room updatedRoom = roomService.searchByRoomNumber(id);

        updatedRoom.setRoomType(room.getRoomType());
        updatedRoom.setBedType(room.getBedType());
        updatedRoom.setBedNumber(room.getBedNumber());
        updatedRoom.setOceanView(room.isOceanView());
        updatedRoom.setAirConditioning(room.isAirConditioning());
        updatedRoom.setBalcony(room.isBalcony());
        updatedRoom.setPricePerNight(room.getPricePerNight());

        roomService.update(updatedRoom);

        return new ModelAndView("rooms", "rooms", roomService.getAll());
    }

    //show rooms by room type
    @RequestMapping(value = "/rooms/search/roomtype", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView roomsByRoomType(@RequestParam(value = "search", required = false, defaultValue = "") RoomType roomType) {

        List<Room> rooms = roomService.searchByType(roomType);

        return new ModelAndView("roomsbyroomtype", "search", rooms);
    }
}
