package ro.sci.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.room.BedType;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.room.RoomType;
import ro.sci.hotel.model.util.Currency;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.BookingRepository;
import ro.sci.hotel.repository.BookingRepositoryImpl;
import ro.sci.hotel.service.BookingService;
import ro.sci.hotel.service.BookingServiceImpl;

/**
 * Hotel Management
 *
 * @author Andrei Vintila, Darius Petricele, Flaviu Lupoian, Tudor Radovici
 * @version 1.0
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        BookingRepository bookingRepository = new BookingRepositoryImpl();
        BookingService bookingService = new BookingServiceImpl();
        bookingService.setBookingRepository(bookingRepository);

        Room room = new Room();
        room.setAirConditioning(false);
        room.setBedNumber(1);
        room.setBedType(BedType.DOUBLE);
        room.setBalcony(false);
        room.setOceanView(false);
        room.setPricePerNight(new Price(20, Currency.EUR));
        room.setRoomType(RoomType.DOUBLE);
        room.setStartDate(Date.valueOf("2017-01-01"));
        room.setEndDate(Date.valueOf("2017-01-01"));

        //        Customer customer = new Customer();
        //        customer.setId(3);
        //        customer.setFirstName("sdsd");
        //        customer.setLastName("sdsdsd");
        //        customer.setEmail("sdsdsdsd");
        //        customer.setPhoneNumber("223232323");
        //        customer.setPaymentMethod(PaymentMethod.CREDITCARD);
        //        customer.setCustomerAddress(new CustomerAddress("afadfas", "Cluj-Napoca", "Romania"));

        Booking booking = new Booking();
        booking.setId(8);
        booking.setCustomerId(2);
        booking.setPricePerDay(new Price(20, Currency.EUR));
        booking.setRoomNumber(2);
        booking.setStartDate(Date.valueOf("2017-11-11"));
        booking.setEndDate(Date.valueOf("2017-12-11"));

        bookingService.update(booking);
        System.out.println(bookingService.getAll().toString());
    }
}
