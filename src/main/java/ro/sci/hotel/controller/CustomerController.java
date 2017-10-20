package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.customer.CustomerAddress;
import ro.sci.hotel.repository.CustomerRepository;
import ro.sci.hotel.service.CustomerService;

/**
 * Controller for customer model
 */
@Controller
public class CustomerController {

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    @Autowired
    private CustomerRepository<Customer> customerRepository;

    @Autowired
    private CustomerService<Customer> customerService;

    //show all customers
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView showCustomers() {

        return new ModelAndView("customers", "customers", customerService.getAll());
    }

    //show customer by id
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ModelAndView customerForm(@PathVariable("id") Integer id) {

        Customer customer = customerService.searchByCustomerId(id);

        return new ModelAndView("updatecustomer", "customer", customer);
    }

    //create new customer
    @RequestMapping(value = "/customers/submit", method = RequestMethod.GET)
    public String customerForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("customerAddress", new CustomerAddress());

        return "submitcustomer";
    }

    @RequestMapping(value = "/customers/submit", method = RequestMethod.POST)
    public String createCustomer(@ModelAttribute Customer customer, @ModelAttribute CustomerAddress customerAddress, Model model) {

        customerService.create(customer, customerAddress);
        model.addAttribute("customer", customer);
        model.addAttribute("customerAddress", customerAddress);

        return "resultscustomer";
    }

    //delete a customer
    @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.GET)
    public String deleteCustomerForm(@PathVariable("id") Integer id, Model model) {

        Customer currentCustomer = customerService.searchByCustomerId(id);
        model.addAttribute("customer", currentCustomer);

        return "deletecustomer";
    }

    @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView deleteCustomer(@PathVariable("id") Integer id, Model model) {

        LOGGER.log(Level.INFO, "Deleting customer with id " + id);

        Customer customer = customerService.searchByCustomerId(id);
        customerService.delete(customer);

        model.addAttribute("customer", customer);

        return new ModelAndView("customers", "customers", customerService.getAll());
    }

    //update a customer
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.POST)
    public ModelAndView updateCustomer(@PathVariable("id") Integer id, @ModelAttribute Customer customer) {

        LOGGER.log(Level.INFO, "Updating customer");
        Customer updatedCustomer = customerService.searchByCustomerId(id);

        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setEmail(customer.getEmail());
        updatedCustomer.setPhoneNumber(customer.getPhoneNumber());
        updatedCustomer.setCustomerAddress(customer.getCustomerAddress());
        updatedCustomer.setPaymentMethod(customer.getPaymentMethod());

        customerService.update(updatedCustomer);

        return new ModelAndView("customers", "customers", customerService.getAll());
    }

    //show customers by last name
    @RequestMapping(value = "/customers/search/lastname", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView customersByLastName(@RequestParam(value = "search", required = false, defaultValue = "") String lastName) {

        List<Customer> customers = customerService.searchByLastName(lastName);

        return new ModelAndView("customersbylastname", "search", customers);

    }
}
