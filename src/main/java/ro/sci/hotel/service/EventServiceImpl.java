package ro.sci.hotel.service;

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.event.Event;

import java.util.Date;
import java.util.List;

public class EventServiceImpl implements EventService {
    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void create(Event event, Customer customer) {

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
    public Object searchById(Integer eventId) {
        return null;
    }
}
