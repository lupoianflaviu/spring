package ro.sci.hotel.service;

import java.util.List;

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.customer.CustomerAddress;
import ro.sci.hotel.repository.CustomerRepository;

/**
 * Customer service interface for repository manipulation
 */
public interface CustomerService<T> {

    /**
     * Get all customers from DB
     *
     * @return List<T> Customer list
     */
    List<T> getAll();

    /**
     * Create a customer
     *
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
     * @param id searched
     * @return T searched customer
     */
    T searchByCustomerId(int id);

    /**
     * Search room by price
     * @param lastName searched
     * @return List<T> searched customer list
     */
    List<T> searchByLastName(String lastName);

    void setCustomerRepository(CustomerRepository<T> customerRepository);
}
