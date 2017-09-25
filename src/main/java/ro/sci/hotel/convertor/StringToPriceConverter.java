package ro.sci.hotel.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import ro.sci.hotel.model.util.Price;
import ro.sci.hotel.service.PriceService;

/**
 * @author flaviu.lupoian@jpard.com
 *
 * date 2017.09.23
 */
@Configuration
public class StringToPriceConverter implements Converter<String, Price> {

    @Autowired
    private PriceService<Price> priceService;

    @Override
    public Price convert(String id) {

        return priceService.searchById(Integer.valueOf(id));
    }
}
