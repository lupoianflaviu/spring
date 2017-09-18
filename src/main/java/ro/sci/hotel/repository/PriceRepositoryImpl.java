package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import ro.sci.hotel.model.util.Price;

/**
 * Implementation for PriceRepository DAO
 */
@Repository("priceRepository")
public class PriceRepositoryImpl implements PriceRepository<Price>{

    @Override
    public List<Price> getAll() {
        return null;
    }

    @Override
    public void create(Price price) {

    }

    @Override
    public void update(Price price) {

    }

    @Override
    public void delete(Price price) {

    }

    @Override
    public Price searchById(Integer id) {
        return null;
    }
}
