package ro.sci.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.repository.CustomerRepository;
import ro.sci.hotel.repository.CustomerRepositoryImpl;
import ro.sci.hotel.service.CustomerService;
import ro.sci.hotel.service.CustomerServiceImpl;

/**
 * Controller for customer model
 */
@Controller
public class CustomerController {

    private CustomerRepository<Customer> customerRepository;
    private CustomerService<Customer> customerService;

    private void init() {
        this.customerRepository = new CustomerRepositoryImpl();
        this.customerService = new CustomerServiceImpl();
        this.customerService.setCustomerRepository(customerRepository);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView showCustomers() {
        init();

        return new ModelAndView("customers", "customers", customerService.getAll());
    }
}
