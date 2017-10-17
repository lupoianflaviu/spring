package ro.sci.hotel.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.event.EventRoom;
import ro.sci.hotel.service.EventRoomService;
import ro.sci.hotel.service.EventService;

@Configuration
public class StringToEventRoomConverter implements Converter<String,EventRoom> {
    @Autowired
    public EventService<EventRoom> eventRoomService;

    @Override
    public EventRoom convert(String id) {
        return eventRoomService.searchById(Integer.valueOf(id));
    }
}
