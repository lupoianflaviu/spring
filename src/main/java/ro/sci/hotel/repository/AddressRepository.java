package ro.sci.hotel.repository;

import ro.sci.hotel.model.employee.Address;

import java.util.List;

public interface AddressRepository<T> {

    /**
     *
     * @return List of all addresses
     */
    List<T> getAll();

    /**
     * Creates a new address
     * @param address
     */
    void create(Address address);

    /**
     * Deletes an address
     * @param t
     */
    void delete(T t);

    /**
     * Update the information of an address
     * @param t
     */
    void update(T t);

    /**
     * Searches for an address of an employee
     * @param EmployeeId
     * @return address of an employee
     */
    T searchByEmployeeId(Integer EmployeeId);
}
