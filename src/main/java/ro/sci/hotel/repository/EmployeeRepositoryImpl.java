package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;
import ro.sci.hotel.constants.EmployeeFlowConstats;
import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.model.employee.Login;
import ro.sci.hotel.model.util.Currency;
import ro.sci.hotel.model.util.Price;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.UIManager.getInt;

/**
 * Created by tudorradovici on 17/09/17.
 */
@Repository("employeeRepository")

public class EmployeeRepositoryImpl extends BaseRepository implements EmployeeRepository<Employee> {

    private static final Logger LOGGER = Logger.getLogger("Employee");

    @Override
    public List<Employee> getAll() {

        List<Employee> employees = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(EmployeeFlowConstats.SQL_SELECT_ALL_FROM_EMPLOYEE)) {

            while (rs.next()) {

                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt(EmployeeFlowConstats.ID));
                employee.setFirstName(rs.getString(EmployeeFlowConstats.FIRSTNAME));
                employee.setLastName(rs.getString(EmployeeFlowConstats.LASTNAME));
                employee.setEmail(rs.getString(EmployeeFlowConstats.EMAIL));
//                employee.setEmployeeAddress();
                employee.setEmployeePhoneNumber(rs.getString(EmployeeFlowConstats.PHONENUMBER));
//              employee.setEmploymentDate(rs.getDate(EMPLOYMENTDATE));
//              employee.setSalary(new Price(rs.getDouble(PRICE), Currency.valueOf(rs.getString(CURRENCY))));
                employee.setEmployeeRole(rs.getString(EmployeeFlowConstats.EMPLOYEEROLE));

                employees.add(employee);

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, EmployeeFlowConstats.DATABASE_ERROR);
        }
        return employees;
    }

    @Override
    public void create(Employee employee) {
        try(Connection conn = newConnection();
            PreparedStatement stm = conn.prepareStatement(EmployeeFlowConstats.SQL_INSERT_EMPLOYEE)){

            conn.setAutoCommit(false);

            stm.setString(1,employee.getFirstName());
            stm.setString(2,employee.getLastName());
            stm.setString(3,employee.getEmail());
            stm.setString(4,employee.getEmployeePhoneNumber());
            //stm.setDate(5,employee.getEmploymentDate());
            stm.setDouble(6, employee.getSalary().getValue());
            stm.setString(7,employee.getEmployeeRole());

            conn.commit();
            conn.setAutoCommit(true);
            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, EmployeeFlowConstats.DATABASE_ERROR);
            throw new RuntimeException(EmployeeFlowConstats.EXCEPTION_THROWN);
        }
        LOGGER.log(Level.INFO, EmployeeFlowConstats.WRITING_DB_FINISHED);
    }

    @Override
    public void delete(Employee employee) {
        try(Connection conn = newConnection();
        PreparedStatement stm=conn.prepareStatement(EmployeeFlowConstats.DELETE_EMPLOYEE)){

            stm.setInt(1,employee.getEmployeeId());
            stm.execute();


        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, EmployeeFlowConstats.DATABASE_ERROR);
            throw new RuntimeException(EmployeeFlowConstats.EXCEPTION_THROWN);
        }
        LOGGER.log(Level.INFO,EmployeeFlowConstats.Employee_DELETED);

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public Employee searchByEmployeeId(Integer employeeId) {

        Employee employee= new Employee();
        try(Connection conn =newConnection(); PreparedStatement stm = conn.prepareStatement("SELECT * FROM employee WHERE  employee_id=?")){

            stm.setInt(1,employeeId);
            ResultSet rs= stm.executeQuery();

            while (rs.next()){
                employee.setEmployeeId(rs.getInt(EmployeeFlowConstats.ID));
                employee.setFirstName(rs.getString(EmployeeFlowConstats.FIRSTNAME));
                employee.setLastName(rs.getString(EmployeeFlowConstats.LASTNAME));
                employee.setEmail(rs.getString(EmployeeFlowConstats.EMAIL));
                employee.setEmployeePhoneNumber(rs.getString(EmployeeFlowConstats.PHONENUMBER));
                //employee.setDate(5,employee.getEmploymentDate());
//                employee.setDouble(SALARY);
                employee.setEmployeeRole(rs.getString(EmployeeFlowConstats.EMPLOYEEROLE));

            }
        } catch (SQLException e) {
//            LOGGER.log(Level.WARNING, DATABASE_ERROR);
//            throw new RuntimeException(EXCEPTION_THROWN);
            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public Employee validateEmployee(Login login) {

        Employee employee = new Employee();

        try(Connection conn =newConnection(); PreparedStatement stm = conn.prepareStatement(EmployeeFlowConstats.SQL_SELECT_USERNAME_PASSWORD)) {

            stm.setString(1,login.getUsername());
            stm.setString(2,login.getPassword());
            ResultSet rs= stm.executeQuery();

            while (rs.next()) {
                employee.setEmployeeId(rs.getInt(EmployeeFlowConstats.ID));
                employee.setUsername(rs.getString(EmployeeFlowConstats.USERNAME));
                employee.setPassword(rs.getString(EmployeeFlowConstats.PASSWORD));
                employee.setFirstName(rs.getString(EmployeeFlowConstats.FIRSTNAME));
                employee.setLastName(rs.getString(EmployeeFlowConstats.LASTNAME));
                employee.setEmployeeRole(rs.getString(EmployeeFlowConstats.EMPLOYEEROLE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> searchByFirstName(String firstName) {
        return null;
    }
}
