package ro.sci.hotel.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.service.BookingService;

/**
 * Convertor for booking id into a Booking.
 *
 * @author flaviu.lupoian@jpard.com
 *
 * date 2017.09.24
 */
@Configuration
public class StringToBookingConverter implements Converter<String, Booking> {

    @Autowired
    private BookingService<Booking> bookingService;

    @Override
    public Booking convert(String id) {

        return bookingService.searchById(Integer.valueOf(id));
    }
}
