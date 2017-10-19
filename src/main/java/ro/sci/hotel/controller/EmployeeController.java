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

        return new ModelAndView("updateemployee", "employees", employeeService.searchByEmployeeId(employeeId));
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
        updateEmployee.setLastName(employee.getLastName());

        return new ModelAndView("updateemployee","employee",employee);
    }

//    //Delete Employee
//    @RequestMapping(value = "/employees/delete/{employeeId}",method = RequestMethod.GET)
//    public String deleteEmployee(@PathVariable("employeeId")Integer employeeId, Model model){
//
//        return new ModelAndView();
//
//    }
}
