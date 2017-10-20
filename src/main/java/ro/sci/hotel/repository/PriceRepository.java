package ro.sci.hotel.repository;

import java.util.List;

/**
 * Repository interface for Price DAO
 */
public interface PriceRepository<T> {

    /**
     * Reads all prices in db
     *
     * @return List of all prices
     */
    List<T> getAll();

    /**
     * Create a price entry in db
     *
     * @param price to createEvent
     */
    void create(T price);

    /**
     * Update price in db
     *
     * @param price to be created
     */
    void update(T price);

    /**
     * Delete price in db
     *
     * @param price to be deleted
     */
    void delete(T price);

    /**
     * Search price by Id
     *
     * @param id price Id
     * @return Price
     */
    T searchById(Integer id);
}
