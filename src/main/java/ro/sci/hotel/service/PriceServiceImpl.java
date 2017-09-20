package ro.sci.hotel.service;

import org.springframework.stereotype.Service;

import java.util.List;

import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.PriceRepository;
import ro.sci.hotel.repository.PriceRepositoryImpl;

/**
 * Implementation for PriceService interface
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService<Price> {

    PriceRepository<Price> pricePriceRepository = new PriceRepositoryImpl();

    @Override
    public List<Price> getAll() {

        return pricePriceRepository.getAll();
    }

    @Override
    public void create(Price price) {

        pricePriceRepository.create(price);
    }

    @Override
    public void update(Price price) {

        pricePriceRepository.update(price);
    }

    @Override
    public void delete(Price price) {

        pricePriceRepository.delete(price);
    }

    @Override
    public Price searchById(Integer id) {

        return pricePriceRepository.searchById(id);
    }
}
