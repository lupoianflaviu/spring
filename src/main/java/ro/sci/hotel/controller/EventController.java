package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.event.EventRoom;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.service.EventRoomService;
import ro.sci.hotel.service.EventService;
import ro.sci.hotel.service.PriceService;

@Controller
public class EventController {
    private static final Logger LOGGER = Logger.getLogger("Hotel");
    @Autowired
    private EventRoomService<Event> eventRoomService;
    @Autowired
    private EventService<Event>eventService;
    @Autowired
    private PriceService<Price> priceService;


    // ------------------- Show All Events ------------------------------------------------
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView showEvents() {

        return new ModelAndView("events", "events", eventService.getAll());
    }
    // ------------------- Submit New Events ------------------------------------------------
    @RequestMapping(value = "/events/submit", method = RequestMethod.GET)
    public String bookingForm(Model model) {
        model.addAttribute("event", new Event());
        return "submitevent";
    }

    @RequestMapping(value = "/events/submit", method = RequestMethod.POST)
    public String createEventRoom(@ModelAttribute Event event, @ModelAttribute EventRoom eventRoomId , Model model) {

        eventService.createEvent(event,eventRoomId);
        model.addAttribute("event",event);

        return "resultsevent";
    }
}
