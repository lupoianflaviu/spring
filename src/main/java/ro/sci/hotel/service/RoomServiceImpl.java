package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.room.RoomType;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.RoomRepository;

/**
 * Room service implementation
 */
@Service
public class RoomServiceImpl implements RoomService<Room> {
    @Autowired
    private RoomRepository<Room> roomRepository;
    @Autowired
    private PriceService<Price> priceService;

    @Override
    public List<Room> getAll() {
        return this.roomRepository.getAll();
    }

    @Override
    public void create(Room room) {
        this.roomRepository.create(room);
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
        Price resultPrice = this.priceService.searchById(room.getPricePerNight()
                                                             .getId());

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
