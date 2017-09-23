package ro.sci.hotel.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.service.CustomerService;

/**
 * Covertor for submit form Converts customer id string to Customer type
 *
 * @author flaviu.lupoian@jpard.com
 *
 * date 2017.09.23
 */
@Configuration
public class StringToCustomerConverter implements Converter<String, Customer> {

    @Autowired
    private CustomerService<Customer> customerService;

    @Override
    public Customer convert(String id) {

        return customerService.searchByCustomerId(Integer.valueOf(id));
    }
}
