package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.hotel.model.employee.Address;
import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.model.employee.Login;
import ro.sci.hotel.repository.AddressRepository;
import ro.sci.hotel.repository.EmployeeRepository;
import ro.sci.hotel.service.AddressService;
import ro.sci.hotel.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private EmployeeRepository<Employee> employeeRepository;
    @Autowired
    private EmployeeService<Employee> employeeService;
    @Autowired
    private AddressRepository<Address> addressRepository;
    @Autowired
    private AddressService<Address> addressService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());
        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(@ModelAttribute("login") Login login) {
        ModelAndView mav = null;
        Employee employee = employeeService.validateEmployee(login);
        if (employee.getPassword()!=null && employee.getUsername()!=null && employee.getEmployeeRole().equals("admin")) {
            mav = new ModelAndView("welcome");
            mav.addObject("employee", employee);
        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!!");
        }
        return mav;
    }
}