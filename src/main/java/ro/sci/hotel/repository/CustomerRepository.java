package ro.sci.hotel.repository;


import java.util.List;

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.customer.CustomerAddress;

/**
 * Customer repository interface for DAO
 */
public interface CustomerRepository<T> {

    /**
     * Get all customers from DB
     *
     * @return List<T> Customer list
     */
    List<T> getAll();

    /**
     * Create a customer
     *
     * @param t new customer
     */
    void create(Customer customer, CustomerAddress customerAddress);

    /**
     * Detele a customer entry from db
     *
     * @param t customer to be deleted
     */
    void delete(T t);

    /**
     * Update a customer entry in db
     *
     * @param t customer to pe updated
     */
    void update(T t);

    /**
     * Search customer by id
     *
     * @param id searched
     * @return T searched customer
     */
    T searchByCustomerId(int id);

    /**
     * Search room by price
     *
     * @param lastName searched
     * @return List<T> searched customer list
     */
    List<T> searchByLastName(String lastName);
}
