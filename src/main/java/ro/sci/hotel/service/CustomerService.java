package ro.sci.hotel.service;

import java.util.List;

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
     * @param t new customer
     */
    void create(T t);

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
     * @return List<T> searched customer list
     */
    List<T> searchByCustomerId(int id);

    /**
     * Search room by price
     * @param firstName searched
     * @return List<T> searched customer list
     */
    List<T> searchByFirstName(String firstName);
}
