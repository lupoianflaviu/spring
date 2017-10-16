package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.event.EventRoom;
import ro.sci.hotel.repository.EventRepository;
import ro.sci.hotel.repository.EventRoomRepository;

/**
 * EventService implementation
 */
@Repository
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository<Event> eventRepository;
    @Autowired
    private EventRoomService<EventRoom> eventRoomService;
    @Autowired
    private EventRoomRepository<EventRoom> eventRoomRepository;


    @Override
    public List<Event> getAll() {
        {
            List<Event> events = this.eventRepository.getAll();

            return events;
        }
    }

    @Override
    public void createEvent(Event event, EventRoom eventRoomId) {
        this.eventRepository.createEvent(event, eventRoomId);

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
    public Event searchById(Integer eventId) {

        return null;
    }
}

