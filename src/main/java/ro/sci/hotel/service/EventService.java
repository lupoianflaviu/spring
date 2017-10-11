package ro.sci.hotel.service;

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.event.Event;

import java.util.Date;
import java.util.List;

/**
 * Service interface for Event Repository manipulation
 */

public interface EventService<T> {

        /**
         * Read all events from repository
         *
         * @return List<T> Booking lists
         */
        List<T> getAll();

        /**
         * Create a new event and set startDAte and endDate in desired eventroom
         *
         * @param event to be added in db
         */
        void createEvent(Event event);

        /**
         * Detele an event entry from repository
         *
         * @param t event to be deleted
         */
        void delete(T t);

        /**
         * Update an event entry in repository
         *
         * @param t Event to pe updated
         */
        void update(T t);

        /**
         * Search events by customer id
         *
         * @param customerId searched
         * @return List<T> searched events list
         */
        List<T> searchByCustomerId(Integer customerId);

        /**
         * Search events by eventroom name
         *
         * @param roomName searched
         * @return List<T> searched bookings list
         */
        List<T> searchByRoomName(String roomName);

        /**
         * Search event by interval
         *
         * @param startDate startdate
         * @param endDate enddate
         * @return List<T> searched events list
         */
        List<T> searchByDate(Date startDate, Date endDate);

        /**
         * Search eventRoom by price
         *
         * @param price searched
         * @return List<T> searched eventRooms list
         */
        List<T> searchByPrice(Double price);

        /**
         * Setter for booking repository injection
         *
         * @param eventRepository Booking repository pe care trebuie sa il creez
         */
     //   void setEventRoomRepository(EventRepository<Event> eventRepository);

        T searchById(Integer eventId);
    }

