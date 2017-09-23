package ro.sci.hotel.service;

import java.util.List;

import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.PriceRepository;

/**
 * Service Interface for Price DAO
 * @param <T> generic
 */
public interface PriceService<T> {

    /**
     * Reads all prices in db
     * @return List of all prices
     */
    List<T> getAll();

    /**
     * Create a price entry in db
     * @param price to create
     */
    void create(T price);

    /**
     * Update price in db
     * @param price to be created
     */
    void update(T price);

    /**
     * Delete price in db
     * @param price to be deleted
     */
    void delete(T price);

    /**
     * Search price by Id
     * @param id price Id
     * @return Price
     */
    T searchById(Integer id);

    void setPriceRepository(PriceRepository<Price> priceRepository);
}
