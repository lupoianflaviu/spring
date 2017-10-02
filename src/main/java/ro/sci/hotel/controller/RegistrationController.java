package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController {
    @Autowired
    public EmployeeService employeeService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister(){
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("employee", new Employee());

        return  mav;
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addEmployee( @ModelAttribute("employee") Employee employee){
        employeeService.create(employee);
        return new ModelAndView("welcome","firstname",employee.getFirstName());
    }
}
