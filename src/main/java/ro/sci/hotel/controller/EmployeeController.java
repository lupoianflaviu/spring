package ro.sci.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.repository.EmployeeRepository;
import ro.sci.hotel.repository.EmployeeRepositoryImpl;
import ro.sci.hotel.service.EmployeeService;
import ro.sci.hotel.service.EmployeeServiceImpl;

/**
 * Employee Model controller
 */
@Controller
public class EmployeeController {
    private EmployeeRepository<Employee> employeeRepository;
    @Autowired
    private EmployeeService<Employee> employeeService;



    private void init(){
        this.employeeRepository= new EmployeeRepositoryImpl();
//        this.employeeService=new EmployeeServiceImpl();
        this.employeeService.setEmployeeRepository(employeeRepository);
    }

    @RequestMapping(value="/employee", method= RequestMethod.GET)
    public ModelAndView showEmployees(){
        init();
        return new ModelAndView("employees","employees",employeeService.getAll());
    }

//    email check field

//    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.refs.email.value)) {
//        this.setState({
//                active : true
//            });
//    } else {
//        this.setState({
//                active : false
//            });
//    }
}
