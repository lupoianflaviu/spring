package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.event.EventRoom;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.EventRoomRepository;

import java.util.Date;
import java.util.List;

@Service
public class EventRoomServiceImpl implements EventRoomService {
    @Autowired
    private EventRoomRepository<EventRoom> eventRoomRepository;
    @Autowired
    private PriceService<Price> priceService;

    @Override
    public List getAll() {
        List<EventRoom> eventRooms = this.eventRoomRepository.getAll();

        for (EventRoom eventRoom : eventRooms) {

            int priceId = eventRoom.getPricePerDay().getId();

            Price price = priceService.searchById(priceId);


            eventRoom.setPricePerDay(price);

        }

        return eventRooms;
    }

    @Override
    public void createEventRoom(EventRoom eventRoom) {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public List searchByEventRoomName(String eventRoomName) {
        return null;
    }

    @Override
    public EventRoom searchByEventRoomId(Integer eventRoomId) {
        {

            EventRoom eventRoom = this.eventRoomRepository.searchByEventRoomId(eventRoomId);



            int evenRoomId = eventRoom.getId();



             return eventRoom;
        }
    }

    @Override
    public void searchedByDate(Date date) {

    }
}
