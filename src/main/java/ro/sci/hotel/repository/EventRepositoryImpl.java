package ro.sci.hotel.repository;

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.event.Event;
import ro.sci.hotel.model.event.EventRoom;

import java.util.Date;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {
    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void createEvent(Event event, EventRoom eventRoom, Customer customer) {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public List serachByEventRoomName(String eventRoomName) {
        return null;
    }

    @Override
    public void searchedByDate(Date date) {

    }
}
