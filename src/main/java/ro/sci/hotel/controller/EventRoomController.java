package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.hotel.model.event.EventRoom;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.service.EventRoomService;
import ro.sci.hotel.service.PriceService;

@Controller
public class EventRoomController {


    @Autowired
    private EventRoomService<EventRoom> eventRoomService;
    @Autowired
    private PriceService<Price> priceService;


    // ------------------- Show All EventRooms ------------------------------------------------
    @RequestMapping(value = "/eventrooms", method = RequestMethod.GET)
    public ModelAndView showEventRooms() {

        return new ModelAndView("eventrooms", "eventrooms", eventRoomService.getAll());
    }
    // ------------------- Submit New EventRoom ------------------------------------------------
    @RequestMapping(value = "/eventrooms/submit", method = RequestMethod.GET)
    public String bookingForm(Model model) {
        model.addAttribute("eventroom", new EventRoom());
        return "submiteventroom";
    }
    @RequestMapping(value = "/eventrooms/submit", method = RequestMethod.POST)
    public String createEventRoom(@ModelAttribute EventRoom eventroom, Model model) {

        eventRoomService.createEventRoom(eventroom);
        model.addAttribute("eventroom",eventroom);

        return "resultseventroom";
}
}
