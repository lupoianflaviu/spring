package ro.sci.hotel.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.sci.hotel.model.employee.Address;
import ro.sci.hotel.model.employee.Employee;

import ro.sci.hotel.repository.EmployeeRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    private AddressService<Address> addressServiceMock;


    @InjectMocks
    private EmployeeServiceImpl employeeService;


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

    }
}