package ro.sci.hotel.service;

import org.springframework.stereotype.Service;
import ro.sci.hotel.model.room.BedType;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.room.RoomType;
import ro.sci.hotel.repository.RoomRepository;

import java.sql.Date;
import java.util.List;

/**
 * Room service implementation
 */
@Service
public class RoomServiceImpl implements RoomService<Room> {

    private RoomRepository<Room> roomRepository;

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
    public List<Room> searchByRoomNumber(Integer roomNumber) {
        return this.roomRepository.searchByRoomNumber(roomNumber);
    }

    @Override
    public List<Room> searchByDate(Date startDate, Date endDate) {
        return this.roomRepository.searchByDate(startDate, endDate);
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
    public List<Room> searchByBedType(BedType bedType) {
        return this.roomRepository.searchByBedType(bedType);
    }

    @Override
    public List<Room> searchByBedNumber(Integer bedNumber) {
        return this.roomRepository.searchByBedNumber(bedNumber);
    }

    @Override
    public List<Room> searchByAC(boolean isAirConditioning) {
        return this.roomRepository.searchByAC(isAirConditioning);
    }

    @Override
    public List<Room> searchByBalcony(boolean isBalcony) {
        return this.roomRepository.searchByBalcony(isBalcony);
    }

    @Override
    public List<Room> searchByBooked(boolean isBooked) {
        return this.roomRepository.searchByBooked(isBooked);
    }
}
