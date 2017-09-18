package ro.sci.hotel.service;

import org.springframework.stereotype.Service;

import java.util.List;

import ro.sci.hotel.model.util.Price;

/**
 * Implementation for PriceService interface
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService<Price>{

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
