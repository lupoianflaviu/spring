package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;
import ro.sci.hotel.constants.EmployeeFlowConstants;
import ro.sci.hotel.model.employee.Employee;
import ro.sci.hotel.model.employee.Login;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by tudorradovici on 17/09/17.
 */
@Repository("employeeRepository")

public class EmployeeRepositoryImpl extends BaseRepository implements EmployeeRepository<Employee> {

    private static final Logger LOGGER = Logger.getLogger("Employee");

    @Override
    public List<Employee> getAll() {

        List<Employee> employees = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(EmployeeFlowConstants.SQL_SELECT_ALL_FROM_EMPLOYEE)) {

            while (rs.next()) {

                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt(EmployeeFlowConstants.ID));
                employee.setFirstName(rs.getString(EmployeeFlowConstants.FIRSTNAME));
                employee.setLastName(rs.getString(EmployeeFlowConstants.LASTNAME));
                employee.setEmail(rs.getString(EmployeeFlowConstants.EMAIL));
                employee.setEmployeePhoneNumber(rs.getString(EmployeeFlowConstants.PHONENUMBER));
                employee.setEmploymentDate(rs.getDate(EmployeeFlowConstants.EMPLOYMENTDATE));
//              employee.setSalary(new Price(rs.getDouble(PRICE), Currency.valueOf(rs.getString(CURRENCY))));
                employee.setEmployeeRole(rs.getString(EmployeeFlowConstants.EMPLOYEEROLE));

                employees.add(employee);

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, EmployeeFlowConstants.DATABASE_ERROR);
        }
        return employees;
    }

    @Override
    public void create(Employee employee) {
        try(Connection conn = newConnection();
            PreparedStatement stm = conn.prepareStatement(EmployeeFlowConstants.SQL_INSERT_EMPLOYEE)){

            conn.setAutoCommit(false);

            stm.setInt(1,employee.getEmployeeId());
            stm.setString(2,employee.getFirstName());
            stm.setString(3,employee.getLastName());
            stm.setString(4,employee.getEmail());
            stm.setString(5,employee.getUsername());
            stm.setString(6,employee.getPassword());
            stm.setString(7,employee.getEmployeePhoneNumber());
            stm.setDate(8,employee.getEmploymentDate());
//          stm.setDouble(6, employee.getSalary().getValue());
            stm.setString(9,employee.getEmployeeRole());

            conn.commit();
            conn.setAutoCommit(true);
            stm.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, EmployeeFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(EmployeeFlowConstants.EXCEPTION_THROWN);
        }
        LOGGER.log(Level.INFO, EmployeeFlowConstants.WRITING_DB_FINISHED);
    }

    @Override
    public void delete(Employee employee) {
        try(Connection conn = newConnection();
        PreparedStatement stm=conn.prepareStatement(EmployeeFlowConstants.DELETE_EMPLOYEE)){

            stm.setInt(1,employee.getEmployeeId());
            stm.execute();


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, EmployeeFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(EmployeeFlowConstants.EXCEPTION_THROWN);
        }
        LOGGER.log(Level.INFO, EmployeeFlowConstants.Employee_DELETED);

    }

    @Override
    public void update(Employee employee) {
        try(Connection conn=newConnection();PreparedStatement stm=conn.prepareStatement(EmployeeFlowConstants.SQL_UPDATE_EMPLOYEE)){

            stm.setInt(7,employee.getEmployeeId());
            stm.setString(1,employee.getFirstName());
            stm.setString(2,employee.getLastName());
            stm.setString(3,employee.getEmail());
            stm.setString(4,employee.getUsername());
            stm.setString(5,employee.getPassword());
            stm.setString(6,employee.getEmployeePhoneNumber());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, EmployeeFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(EmployeeFlowConstants.EXCEPTION_THROWN);
        }

    }

    @Override
    public Employee searchByEmployeeId(Integer employeeId) {

        Employee employee= new Employee();
        try(Connection conn =newConnection(); PreparedStatement stm = conn.prepareStatement(EmployeeFlowConstants.SQL_SELECT_BY_ID)){

            stm.setInt(1,employeeId);
            ResultSet rs= stm.executeQuery();

            while (rs.next()){
                employee.setEmployeeId(rs.getInt(EmployeeFlowConstants.ID));
                employee.setFirstName(rs.getString(EmployeeFlowConstants.FIRSTNAME));
                employee.setLastName(rs.getString(EmployeeFlowConstants.LASTNAME));
                employee.setEmail(rs.getString(EmployeeFlowConstants.EMAIL));
                employee.setUsername(rs.getString(EmployeeFlowConstants.USERNAME));
                employee.setPassword(rs.getString(EmployeeFlowConstants.PASSWORD));
                employee.setEmployeePhoneNumber(rs.getString(EmployeeFlowConstants.PHONENUMBER));
                employee.setEmploymentDate(rs.getDate(EmployeeFlowConstants.EMPLOYMENTDATE));
//              employee.setDouble(SALARY);
                employee.setEmployeeRole(rs.getString(EmployeeFlowConstants.EMPLOYEEROLE));

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, EmployeeFlowConstants.DATABASE_ERROR);
            throw new RuntimeException(EmployeeFlowConstants.EXCEPTION_THROWN);
        }

        return employee;
    }

    @Override
    public Employee validateEmployee(Login login) {

        Employee employee = new Employee();

        try(Connection conn =newConnection(); PreparedStatement stm = conn.prepareStatement(EmployeeFlowConstants.SQL_SELECT_USERNAME_PASSWORD)) {

            stm.setString(1,login.getUsername());
            stm.setString(2,login.getPassword());
            ResultSet rs= stm.executeQuery();

            while (rs.next()) {
                employee.setEmployeeId(rs.getInt(EmployeeFlowConstants.ID));
                employee.setUsername(rs.getString(EmployeeFlowConstants.USERNAME));
                employee.setPassword(rs.getString(EmployeeFlowConstants.PASSWORD));
                employee.setFirstName(rs.getString(EmployeeFlowConstants.FIRSTNAME));
                employee.setLastName(rs.getString(EmployeeFlowConstants.LASTNAME));
                employee.setEmployeeRole(rs.getString(EmployeeFlowConstants.EMPLOYEEROLE));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, EmployeeFlowConstants.LOGIN_ERROR);
            throw new RuntimeException(EmployeeFlowConstants.EXCEPTION_THROWN);
        }
        return employee;
    }

    @Override
    public List<Employee> searchByFirstName(String firstName) {
        List<Employee> employees = new ArrayList<>();

        try(Connection conn =newConnection(); PreparedStatement stm = conn.prepareStatement(EmployeeFlowConstants.SQL_SELECT_BY_FIRST_NAME)){

            stm.setString(1,firstName);
            ResultSet rs= stm.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt(EmployeeFlowConstants.ID));
                employee.setFirstName(rs.getString(EmployeeFlowConstants.FIRSTNAME));
                employee.setLastName(rs.getString(EmployeeFlowConstants.LASTNAME));
                employee.setEmail(rs.getString(EmployeeFlowConstants.EMAIL));
                employee.setEmployeePhoneNumber(rs.getString(EmployeeFlowConstants.PHONENUMBER));
                employee.setEmploymentDate(rs.getDate(EmployeeFlowConstants.EMPLOYMENTDATE));
//              employee.setSalary(new Price(rs.getDouble(PRICE), Currency.valueOf(rs.getString(CURRENCY))));
                employee.setEmployeeRole(rs.getString(EmployeeFlowConstants.EMPLOYEEROLE));

                employees.add(employee);

            }
        } catch (SQLException e) {
//            LOGGER.log(Level.WARNING, EmployeeFlowConstants.LOGIN_ERROR);
//            throw new RuntimeException(EmployeeFlowConstants.EXCEPTION_THROWN);
            e.printStackTrace();
        }
        return employees;
    }
}
