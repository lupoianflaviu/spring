package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.hotel.model.employee.Address;
import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.repository.AddressRepository;
import ro.sci.hotel.repository.EmployeeRepository;
import ro.sci.hotel.repository.EmployeeRepositoryImpl;
import ro.sci.hotel.service.AddressService;
import ro.sci.hotel.service.EmployeeService;
import ro.sci.hotel.service.EmployeeServiceImpl;

import java.util.List;
import java.util.logging.Logger;

/**
 * Employee Model controller
 */
@Controller
public class EmployeeController {

    private static final Logger LOGGER = Logger.getLogger("Employee Controller");

    @Autowired
    private EmployeeRepository<Employee> employeeRepository;
    @Autowired
    private EmployeeService<Employee> employeeService;
    @Autowired
    private AddressRepository<Address> addressRepository;
    @Autowired
    private AddressService<Address> addressService;


    //Show all employees

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView showEmployees() {
        return new ModelAndView("employees", "employees", employeeService.getAll());
    }

    //Show an employee by a ID

    @RequestMapping(value = "/employee/search/{employeeId}", method = RequestMethod.GET)
    public ModelAndView showEmployeeById(@PathVariable("employeeId") Integer employeeId) {

        return new ModelAndView("viewEmployee", "employees", employeeService.searchByEmployeeId(employeeId));
    }

    //Show employees by First Name

    @RequestMapping(value = "/employee/search/firstname", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView employeesByFirstName(@RequestParam(value = "search", required = false, defaultValue = "") String firstName) {
        List<Employee>employees = employeeService.searchByFirstName(firstName);
        return new ModelAndView("viewEmployeeByFirstName", "search", employees);

    }
}
