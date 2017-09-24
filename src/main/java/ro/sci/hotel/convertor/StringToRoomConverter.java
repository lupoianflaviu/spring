package ro.sci.hotel.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.service.PriceService;
import ro.sci.hotel.service.RoomService;

/**
 * Covertor for submit form Converts room number string to Room type
 *
 * @author flaviu.lupoian@jpard.com
 *
 * date 2017.09.23
 */
@Configuration
public class StringToRoomConverter implements Converter<String, Room> {

    @Autowired
    private RoomService<Room> roomService;

    @Override
    public Room convert(String roomNumber) {

        return roomService.searchByRoomNumber(Integer.valueOf(roomNumber));
    }
}
