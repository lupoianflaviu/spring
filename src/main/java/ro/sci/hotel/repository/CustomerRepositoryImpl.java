package ro.sci.hotel.repository;


import org.springframework.stereotype.Repository;
import ro.sci.hotel.model.customer.Customer;

import java.util.List;
import java.util.logging.Logger;

/**
 * Customer repository implementation
 */
@Repository
public class CustomerRepositoryImpl extends BaseRepository implements CustomerRepository<Customer>{

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void create(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public List<Customer> searchByCustomerId(int id) {
        return null;
    }

    @Override
    public List<Customer> searchByFirstName(String firstName) {
        return null;
    }
}
