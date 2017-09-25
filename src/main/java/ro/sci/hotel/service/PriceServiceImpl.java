package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.repository.PriceRepository;

/**
 * Implementation for PriceService interface
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService<Price> {

    @Autowired
    private PriceRepository<Price> priceRepository;

    @Override
    public List<Price> getAll() {

        return priceRepository.getAll();
    }

    @Override
    public void create(Price price) {

        priceRepository.create(price);
    }

    @Override
    public void update(Price price) {

        priceRepository.update(price);
    }

    @Override
    public void delete(Price price) {

        priceRepository.delete(price);
    }

    @Override
    public Price searchById(Integer id) {

        return priceRepository.searchById(id);
    }

    @Override
    public void setPriceRepository(PriceRepository<Price> priceRepository) {
        this.priceRepository = priceRepository;
    }
}
