package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import ro.sci.hotel.model.event.EventRoom;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.EventRoomRepository;

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
        this.eventRoomRepository.createEventRoom(eventRoom);
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
    public Integer searchByEventRoomId(Integer eventRoomId) {
        {

            EventRoom eventRoom = this.eventRoomRepository.searchByEventRoomId(eventRoomId);


         int eventRoomId1 = eventRoom.getId();



             return eventRoomId1;
        }
    }

    @Override
    public void searchedByDate(Date date) {

    }
}
