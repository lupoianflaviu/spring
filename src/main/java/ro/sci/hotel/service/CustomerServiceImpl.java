package ro.sci.hotel.service;

import org.springframework.stereotype.Service;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.repository.CustomerRepository;

import java.util.List;

/**
 * Customer service implementation
 */
@Service
public class CustomerServiceImpl implements CustomerService<Customer> {

    private CustomerRepository<Customer> customerRepository;

    @Override
    public List<Customer> getAll() {
        return this.customerRepository.getAll();
    }

    @Override
    public void create(Customer customer) {
        this.customerRepository.create(customer);
    }

    @Override
    public void delete(Customer customer) {
        this.customerRepository.delete(customer);
    }

    @Override
    public void update(Customer customer) {
        this.customerRepository.update(customer);
    }

    @Override
    public List<Customer> searchByCustomerId(int id) {
        return this.customerRepository.searchByCustomerId(id);
    }

    @Override
    public List<Customer> searchByFirstName(String firstName) {
        return this.customerRepository.searchByFirstName(firstName);
    }
}
