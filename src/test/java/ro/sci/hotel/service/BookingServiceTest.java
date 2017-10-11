package ro.sci.hotel.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import ro.sci.hotel.model.booking.Booking;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.room.Room;
import ro.sci.hotel.model.util.Currency;
import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.BookingRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for all BookingService methods
 */
public class BookingServiceTest {

    private Booking booking1;
    private Booking booking2;
    private Customer customer1;
    private Customer customer2;
    private Room room1;
    private Room room2;
    private List<Booking> bookingList = new ArrayList<>();
    private List<Room> roomList = new ArrayList<>();

    @Mock
    private BookingRepository<Booking> bookingRepositoryMock;

    @Mock
    private RoomService<Room> roomServiceMock;

    @Mock
    private CustomerService<Customer> customerServiceMock;

    @Mock
    private PriceService<Price> priceServiceMock;

    @InjectMocks
    private BookingServiceImpl bookingService;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        booking1 = new Booking();
        customer1 = new Customer();
        room1 = new Room();
        booking1.setId(1);
        customer1.setId(1);
        booking1.setCustomer(customer1);
        room1.setRoomNumber(1);
        room1.setPricePerNight(new Price(30, Currency.EUR));
        booking1.setRoom(room1);
        booking1.setStartDate(Date.valueOf("2017-01-01"));
        booking1.setEndDate(Date.valueOf("2017-01-05"));
        booking1.setPricePerDay(room1.getPricePerNight());
        booking1.setTotalBookingPrice(90d);

        booking2 = new Booking();
        customer2 = new Customer();
        room2 = new Room();
        booking2.setId(2);
        customer2.setId(2);
        booking2.setCustomer(customer2);
        room2.setRoomNumber(2);
        room2.setPricePerNight(new Price(50, Currency.EUR));
        booking2.setRoom(room2);
        booking2.setStartDate(Date.valueOf("2017-02-01"));
        booking2.setEndDate(Date.valueOf("2017-02-05"));
        booking2.setPricePerDay(room2.getPricePerNight());
        booking2.setTotalBookingPrice(100d);

        bookingList.add(booking1);
        bookingList.add(booking2);
        when(bookingRepositoryMock.getAll()).thenReturn(bookingList);
        when(bookingRepositoryMock.calculateDays(anyInt())).thenReturn(100d);
        when(roomServiceMock.searchByRoomNumber(anyInt())).thenReturn(room1);
        when(customerServiceMock.searchByCustomerId(anyInt())).thenReturn(customer1);

    }

    @Test
    public void testGetAll() throws Exception {

        assertEquals(2, bookingService.getAll()
                                      .size());
        verify(bookingRepositoryMock, times(1)).getAll();
    }

    @Test
    public void testGetAllNotEquals() throws Exception {
        assertNotEquals(0, bookingService.getAll()
                                         .size());
    }

    @Test
    public void testCreate() throws Exception {
        bookingList.add(booking1);

        assertEquals(3, bookingService.getAll()
                                      .size());
    }

    @Test(expected = ValidationException.class)
    public void testCreateExceptionWhenWrongPeriodIsGiven() {
        Booking booking3 = new Booking();
        booking3.setStartDate(Date.valueOf("2017-01-01"));
        booking3.setEndDate(Date.valueOf("2017-01-01"));
        booking3.setId(2);
        booking3.setRoom(room1);
        booking3.setCustomer(customer2);
        bookingService.create(booking3, room1, customer2);
    }

    @Test
    public void testDelete() throws Exception {
        bookingService.delete(booking1);
        bookingService.delete(booking2);

        verify(bookingRepositoryMock, times(1)).delete(booking1);
        verify(bookingRepositoryMock, times(1)).delete(booking2);
    }

    @Test
    public void testDeleteNotUsed() throws Exception {
        bookingService.delete(booking1);

        verify(bookingRepositoryMock, times(0)).delete(booking2);
    }

    @Test
    public void testUpdate() throws Exception {
        booking2.setId(3);
        bookingService.update(booking2);

        when(bookingRepositoryMock.searchById(3)).thenReturn(booking2);

        assertEquals(3, bookingRepositoryMock.searchById(3)
                                             .getId());
    }

    @Test(expected = ValidationException.class)
    public void testUpdateWithZeroNights() throws Exception {
        Booking booking3 = new Booking();
        booking3.setId(2);
        booking3.setRoom(room1);
        booking3.setCustomer(customer2);
        booking3.setStartDate(Date.valueOf("2017-01-01"));
        booking3.setEndDate(Date.valueOf("2017-01-01"));
        bookingService.update(booking3);
    }

    @Test
    public void testSearchByCustomerId() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);

        when(bookingRepositoryMock.searchByCustomerId(1)).thenReturn(bookings);

        assertEquals(1, bookingService.searchByCustomerId(1)
                                      .size());
        assertEquals(true, bookingService.searchByCustomerId(1)
                                         .contains(booking1));
    }

    @Test
    public void testSearchByCustomerIdReturnsOnlyCorrectBookings() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);

        when(bookingRepositoryMock.searchByCustomerId(1)).thenReturn(bookings);

        assertEquals(true, bookingService.searchByCustomerId(1)
                                         .contains(booking1));
        assertEquals(0, bookingService.searchByCustomerId(2)
                                      .size());
    }

    @Test
    public void testSearchByRoomNumber() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);

        when(bookingRepositoryMock.searchByRoomNumber(1)).thenReturn(bookings);

        assertEquals(1, bookingService.searchByRoomNumber(1)
                                      .size());
        assertEquals(true, bookingService.searchByRoomNumber(1)
                                         .contains(booking1));
    }

    @Test
    public void testSearchByRoomNumberOnlyReturnsCorrectBookings() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);

        when(bookingRepositoryMock.searchByRoomNumber(1)).thenReturn(bookings);

        assertEquals(0, bookingService.searchByRoomNumber(2)
                                      .size());
        assertEquals(false, bookingService.searchByRoomNumber(2)
                                          .contains(booking2));
    }

    @Test
    public void testSearchByDate() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);

        when(bookingRepositoryMock.searchByDate(anyObject(), anyObject())).thenReturn(bookings);

        assertEquals(1, bookingService.searchByDate(Date.valueOf("2017-01-01"), Date.valueOf("2017-01-05"))
                                      .size());
        assertEquals(true, bookingService.searchByDate(Date.valueOf("2017-01-01"), Date.valueOf("2017-01-05"))
                                         .contains(booking1));
    }

    @Test
    public void testSearchByDateReturnsOnlyCorrectBookings() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking2);

        when(bookingRepositoryMock.searchByDate(anyObject(), anyObject())).thenReturn(bookings);

        assertEquals(1, bookingService.searchByDate(Date.valueOf("2017-01-01"), Date.valueOf("2017-01-05"))
                                      .size());
        assertEquals(false, bookingService.searchByDate(Date.valueOf("2017-01-01"), Date.valueOf("2017-01-05"))
                                          .contains(booking1));
    }

    @Test
    public void testSearchByPrice() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);

        when(bookingRepositoryMock.searchByPrice(30d)).thenReturn(bookings);

        assertEquals(1, bookingService.searchByPrice(booking1.getPricePerDay()
                                                             .getValue())
                                      .size());
        assertEquals(true, bookingService.searchByPrice(booking1.getPricePerDay()
                                                                .getValue())
                                         .contains(booking1));
    }

    @Test
    public void testSearchByPriceReturnsOnlyCorrectBookings() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);

        when(bookingRepositoryMock.searchByPrice(30d)).thenReturn(bookings);

        assertEquals(0, bookingService.searchByPrice(booking2.getPricePerDay()
                                                             .getValue())
                                      .size());
        assertEquals(false, bookingService.searchByPrice(booking2.getPricePerDay()
                                                                 .getValue())
                                          .contains(booking1));
    }

    @Test
    public void testSearchAvailableRoomsByDate() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);

        roomList.add(room1);
        roomList.add(room2);

        when(roomServiceMock.getAll()).thenReturn(roomList);
        when(bookingRepositoryMock.searchByDate(anyObject(), anyObject())).thenReturn(bookings);


        assertEquals(1, bookingService.searchAvailableRoomsByDate(Date.valueOf("2017-01-01"), Date.valueOf("2017-01-05"))
                                      .size());
    }

    @Test
    public void testSearchAvailableRoomsByDateReturnCorrectRooms() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        roomList.add(room1);

        when(roomServiceMock.getAll()).thenReturn(roomList);
        when(priceServiceMock.searchById(anyInt())).thenReturn(new Price(50, Currency.EUR));
        when(bookingRepositoryMock.searchByDate(anyObject(), anyObject())).thenReturn(bookings);

        assertEquals(0, bookingService.searchAvailableRoomsByDate(Date.valueOf("2017-01-01"), Date.valueOf("2017-01-05"))
                                      .size());
    }

    @Test
    public void testSearchById() throws Exception {

        when(bookingRepositoryMock.searchById(1)).thenReturn(booking1);

        assertEquals(1, bookingService.searchById(1)
                                      .getId());
    }

    @Test
    public void testSearchByIdNotEqual() throws Exception {

        when(bookingRepositoryMock.searchById(1)).thenReturn(booking1);

        assertNotEquals(2, bookingService.searchById(1)
                                         .getId());
    }

}