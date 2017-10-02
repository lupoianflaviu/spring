package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.service.EventRoomService;
import ro.sci.hotel.service.EventService;
import ro.sci.hotel.service.PriceService;

import java.util.logging.Logger;

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
}
