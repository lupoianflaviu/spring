package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.event.EventRoom;
import ro.sci.hotel.repository.EventRepository;

import java.util.Date;
import java.util.List;

@Repository
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository<Event> eventRepository;
    @Autowired
    private EventRoomService<EventRoom> eventRoomService;

    @Override
    public List<Event> getAll() {
        {
            List<Event> events = this.eventRepository.getAll();

//            for (Event event : events) {
//
//                int eventRoomId = event.getEventRoomId().getId();
//
//                EventRoom eventRoom = eventRoomService.searchByEventRoomId(eventRoomId);
//
//                event.setEventRoomId(eventRoom);
//            }

            return events;
        }
    }

    @Override
    public void createEvent(Event event,EventRoom eventRoom) {   this.eventRepository.createEvent(event,eventRoom);

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public List searchByCustomerId(Integer customerId) {
        return null;
    }

    @Override
    public List searchByRoomName(String roomName) {
        return null;
    }

    @Override
    public List searchByDate(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List searchByPrice(Double price) {
        return null;
    }

    @Override
    public Event searchById(Integer eventId) { return null;    }
    }

