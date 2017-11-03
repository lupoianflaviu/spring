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


import ro.sci.hotel.model.employee.Address;
import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.repository.AddressRepository;
import ro.sci.hotel.repository.EmployeeRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    private Employee employee1;
    private Employee employee2;

    private Address address1;
    private Address address2;

    private List<Employee> employeeList = new ArrayList<>();
    private List<Address> addressList = new ArrayList<>();

    @Mock
    private EmployeeRepository<Employee> employeeRepositoryMock;

    @Mock
    private AddressRepository<Address> addressRepositoryMock;

    @Mock
    private EmployeeService<Employee> employeeServiceMock;

    @Mock
    private AddressService<Address> addressServiceMock;


    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private  AddressServiceImpl addressService;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        employee1 = new Employee();
        employee1.setEmployeeId(1);
        employee1.setFirstName("Tudor");
        employee1.setLastName("Radovici");
        employee1.setEmail("tudor@gmail.com");
        employee1.setUsername("tradov22");
        employee1.setPassword("1234");
        employee1.setEmployeePhoneNumber("0740300364");
        employee1.setEmploymentDate(Date.valueOf("2017-01-01"));
        employee1.setEmployeeRole("admin");

        employee2 = new Employee();
        employee2.setEmployeeId(2);
        employee2.setFirstName("Tudor");
        employee2.setLastName("Cristian");
        employee2.setEmail("tudorel@gmail.com");
        employee2.setUsername("tradov12");
        employee2.setPassword("12345");
        employee2.setEmployeePhoneNumber("0740300464");
        employee2.setEmploymentDate(Date.valueOf("2015-01-01"));
        employee2.setEmployeeRole("admin");

        address1 = new Address();
        address1.setEmployeeId(1);
        address1.setCountry("romania");
        address1.setCity("cluj");
        address1.setStreetAddress("soporului 9/120");


        address2 = new Address();
        address2.setEmployeeId(2);
        address2.setCountry("austria");
        address2.setCity("vienna");
        address2.setStreetAddress("karl 5");

        employee1.setEmployeeAddress(address1);
        employee2.setEmployeeAddress(address2);

        employeeList.add(employee1);
        employeeList.add(employee2);

        addressList.add(address1);
        addressList.add(address2);

        when(employeeRepositoryMock.getAll()).thenReturn(employeeList);
        when(employeeServiceMock.searchByEmployeeId(anyInt())).thenReturn(employee1);
        when(employeeRepositoryMock.searchByEmployeeId(anyInt())).thenReturn(employee1);

    }
    @Test
    public void testGetAllEmployees() throws Exception {

        assertEquals(2, employeeService.getAll()
                .size());
        verify(employeeRepositoryMock, times(1)).getAll();
    }

    @Test
    public void testGetAllAddresses() throws Exception {

        assertEquals(2, addressService.getAll()
                .size());
        verify(addressRepositoryMock, times(1)).getAll();
    }

    @Test
    public void testGetAllNotEqualsEmployees() throws Exception {
        assertNotEquals(0, employeeService.getAll().size());
    }

    @Test
    public void testGetAllNotEqualsAddresses() throws Exception {
        assertNotEquals(0, addressService.getAll().size());
    }

    @Test
    public void testCreateAddress() throws Exception {
        addressList.add(address1);

        assertEquals(3, addressService.getAll().size());
    }

    @Test
    public void testCreateEmployee() throws Exception {
        employeeList.add(employee1);

        assertEquals(3, employeeService.getAll().size());
    }



    @Test
    public void testDeleteEmployee() throws Exception {
        employeeService.delete(employee1);
        employeeService.delete(employee2);

        verify(employeeRepositoryMock, times(1)).delete(employee1);
        verify(employeeRepositoryMock, times(1)).delete(employee2);
    }


    @Test
    public void testDeleteNotUsed() throws Exception {
        employeeService.delete(employee1);

        verify(employeeRepositoryMock, times(0)).delete(employee2);
    }

    @Test
    public void testUpdate() throws Exception {
        employee2.setEmployeeId(4);
        employeeService.update(employee2);

        when(employeeRepositoryMock.searchByEmployeeId(4)).thenReturn(employee2);

        assertEquals(4, employeeRepositoryMock.searchByEmployeeId(4).getEmployeeId());
    }

}