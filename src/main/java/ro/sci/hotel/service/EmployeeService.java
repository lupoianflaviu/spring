package ro.sci.hotel.service;

import java.util.List;

import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.model.employee.Login;
import ro.sci.hotel.repository.EmployeeRepository;

public interface EmployeeService<T> {
    /**
     *
     * @return List of all employees.
     */
    List<T> getAll();

    /**
     * Creates a new employee
     * @param employee Employee will be added to the DB.
     */
    void create(Employee employee);

    /**
     * Deletes an employee
     * @param t  Employee will be deleted from the DB.
     */
    void delete(T t);

    /**
     *
     * @param t
     */
    void update(T t);

    /**
     * Search by ID
     * @param employeeId
     * @return employee with a certain ID
     */
    T searchByEmployeeId(Integer employeeId);

    /**
     * Searches the DB by firstName;
     * @param firstName
     * @return list of employee with a certain firstName;
     */

    List<T>searchByFirstName(String firstName);

    Employee validateEmployee(Login login);

    void setEmployeeRepository(EmployeeRepository<Employee> employeeRepository);


}
