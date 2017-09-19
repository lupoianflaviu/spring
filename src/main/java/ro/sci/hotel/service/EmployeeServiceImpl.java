package ro.sci.hotel.service;

import org.springframework.stereotype.Service;
import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.repository.EmployeeRepository;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService<Employee>{
    private EmployeeRepository<Employee> employeeRepository;

    @Override
    public List<Employee> getAll() {
        return this.employeeRepository.getAll();
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
        this.employeeRepository.update(employee);
    }

    @Override
    public List<Employee> searchByEmployeeId(Integer employeeId) {
        return this.employeeRepository.searchByEmployeeId(employeeId);
    }

    @Override
    public List<Employee> searchByFirstName(String firstName) {
        return this.employeeRepository.searchByFirstName(firstName);
    }
}
