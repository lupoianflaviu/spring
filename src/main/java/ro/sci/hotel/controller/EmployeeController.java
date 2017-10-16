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

import ro.sci.hotel.model.employee.Address;
import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.repository.AddressRepository;
import ro.sci.hotel.repository.EmployeeRepository;
import ro.sci.hotel.service.AddressService;
import ro.sci.hotel.service.EmployeeService;

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

    //Create a new Employee
    @RequestMapping(value = "/employee/create/newemployee", method = RequestMethod.GET)
    public String bookingForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("address", new Address());
        return "newemployee";
    }

    @RequestMapping(value = "/employee/create/newemployee", method = RequestMethod.POST)
    public String createEmployee (@ModelAttribute Employee employee, @ModelAttribute Address address, Model model) {

        employeeService.create(employee);
        addressService.create(address);
        model.addAttribute("employee", employee);
        model.addAttribute("address",address);

        return "employeeConfirmation";
    }

    //Show all employees
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView showEmployees() {
        return new ModelAndView("employees", "employees", employeeService.getAll());
    }

    //Show an employee by a ID
    @RequestMapping(value = "/employee/search/{employeeId}", method = RequestMethod.GET)
    public ModelAndView showEmployeeById(@PathVariable("employeeId") Integer employeeId) {

        Employee employee = employeeService.searchByEmployeeId(employeeId);

        return new ModelAndView("updateemployee", "employee", employeeService.searchByEmployeeId(employeeId));
    }

    //Show employees by First Name
    @RequestMapping(value = "/employee/search/firstname", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView employeesByFirstName(@RequestParam(value = "search", required = false, defaultValue = "0") String firstName) {
        List<Employee>employees = employeeService.searchByFirstName(firstName);
        return new ModelAndView("employeebyfirstname", "search", employees);

    }

    //Update an employee
    @RequestMapping(value = "/employee/search/{employeeId}", method = RequestMethod.POST)
    public ModelAndView updateEmployee(@PathVariable("employeeId") Integer employeeId, @ModelAttribute Employee employee){
        Employee updateEmployee=employeeService.searchByEmployeeId(employeeId);

        updateEmployee.setEmployeeId(employee.getEmployeeId());
        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName(employee.getLastName());
        updateEmployee.setEmail(employee.getEmail());
        updateEmployee.setUsername(employee.getUsername());
        updateEmployee.setPassword(employee.getPassword());
        updateEmployee.setEmployeePhoneNumber(employee.getEmployeePhoneNumber());
        updateEmployee.setEmployeeAddress(employee.getEmployeeAddress());

        employeeService.update(updateEmployee);

        return new ModelAndView("employees","employees", employeeService.getAll());
    }

    //Delete Employee
    @RequestMapping(value = "/employee/delete/{employeeId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView deleteEmployee(@PathVariable("employeeId") Integer employeeId, Model model) {

        LOGGER.log(Level.INFO, "Deleting employee with id " + employeeId);

        Employee employee = employeeService.searchByEmployeeId(employeeId);
        employeeService.delete(employee);

        model.addAttribute("employee", employee);

        return new ModelAndView("employees", "employees", employeeService.getAll());
    }

    @RequestMapping(value = "/employee/delete/{employeeId}", method = RequestMethod.GET)
    public String deleteEmployeeForm(@PathVariable("employeeId") Integer employeeId, Model model) {

        Employee deletedEmployee = employeeService.searchByEmployeeId(employeeId);

        model.addAttribute("employee", deletedEmployee);

        return "deleteemployee";
    }
}
