package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.room.RoomType;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.RoomRepository;

import java.util.List;

/**
 * Room service implementation
 */
@Service()
public class RoomServiceImpl implements RoomService<Room> {

    @Autowired
    private PriceService<Price> priceService;

    @Autowired
    private RoomRepository<Room> roomRepository;

    @Override
    public List<Room> getAll() {
        List<Room> rooms = this.roomRepository.getAll();

        for (Room room : rooms) {
            int priceId = room.getPricePerNight().getId();
            Price resultPrice = priceService.searchById(room.getPricePerNight().getId());
            room.setPricePerNight(resultPrice);
        }

        return rooms;
    }

    @Override
    public void create(Room room, Price price) {
        this.roomRepository.create(room, price);
    }

    @Override
    public void delete(Room room) {
        this.roomRepository.delete(room);
    }

    @Override
    public void update(Room room) {
        this.roomRepository.update(room);
    }

    @Override
    public Room searchByRoomNumber(Integer roomNumber) {
        Room room = this.roomRepository.searchByRoomNumber(roomNumber);
        Price resultPrice = priceService.searchById(room.getPricePerNight().getId());
        room.setPricePerNight(resultPrice);

        return room;
    }

    @Override
    public List<Room> searchByPrice(Double price) {
        return this.roomRepository.searchByPrice(price);
    }

    @Override
    public List<Room> searchByType(RoomType roomType) {
        return this.roomRepository.searchByType(roomType);
    }

    @Override
    public void setRoomRepository(RoomRepository<Room> roomRepository) {
        this.roomRepository = roomRepository;
    }
}
