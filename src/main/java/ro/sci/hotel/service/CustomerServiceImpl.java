package ro.sci.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.repository.CustomerRepository;

/**
 * Customer service implementation
 */
@Service
public class CustomerServiceImpl implements CustomerService<Customer> {

    @Autowired
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
    public Customer searchByCustomerId(int id) {
        return this.customerRepository.searchByCustomerId(id);
    }

    @Override
    public List<Customer> searchByLastName(String lastName) {
        return this.customerRepository.searchByLastName(lastName);
    }

    @Override
    public void setCustomerRepository(CustomerRepository<Customer> customerRepository) {
        this.customerRepository = customerRepository;
    }
}
