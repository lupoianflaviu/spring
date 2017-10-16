package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ro.sci.hotel.model.employee.Address;
import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.model.employee.Login;
import ro.sci.hotel.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService<Employee>{

    @Autowired
    private EmployeeRepository<Employee> employeeRepository;


    @Autowired
    private AddressService<Address> addressService;

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = this.employeeRepository.getAll();

        for (Employee employee : employees) {

            Address address = addressService.searchByEmployeeId(employee.getEmployeeId());
            employee.setEmployeeAddress(address);
        }
        return employees;
    }

    @Override
    public void create(Employee employee) {
        this.employeeRepository.create(employee);
    }

    @Override
    public void delete(Employee employee) {
        this.employeeRepository.delete(employee);
    }

    @Override
    public void update(Employee employee) {
        List<Employee> employees = this.employeeRepository.getAll();

        for (Employee savedEmployees : employees) {


            Address address = addressService.searchByEmployeeId(savedEmployees.getEmployeeId());
            employee.setEmployeeAddress(address);
        }
        this.employeeRepository.update(employee);
    }

    @Override
    public Employee searchByEmployeeId(Integer employeeId) {

        Employee employee = this.employeeRepository.searchByEmployeeId(employeeId);

        Address address = addressService.searchByEmployeeId(employee.getEmployeeId());

        employee.setEmployeeAddress(address);

        return employee;
    }

    @Override
    public List<Employee> searchByFirstName(String firstName) {
        return this.employeeRepository.searchByFirstName(firstName);

    }

    @Override
    public Employee validateEmployee(Login login) {
        return this.employeeRepository.validateEmployee(login);
    }

    @Override
    public void setEmployeeRepository(EmployeeRepository<Employee> employeeRepository) {
        this.employeeRepository =employeeRepository;
    }
}
