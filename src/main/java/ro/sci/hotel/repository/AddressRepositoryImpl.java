package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.sci.hotel.model.employee.Address;

/**
 * Implementation of AddressRepository Interface
 */
@Repository("addressRepository")
public class AddressRepositoryImpl extends BaseRepository implements AddressRepository<Address> {

    private static final Logger LOGGER = Logger.getLogger("AddressRepositoryImpl");

    private static final String DATABASE_ERROR = "Database error!";

    private static final String EXCEPTION_THROWN = "Exception thrown";

    private static final String WRITING_DB_FINISHED = "Writing in db has finished!";

    private static final String SQL_SELECT_ALL_FROM_ADDRESS = "SELECT employee_id, street_address, city, country FROM employee_address";
    private static final String SQL_INSERT_ADDRESS = "INSERT INTO employee_address(employee_id, street_address, city, country) VALUES (?,?,?,?)";
    private static final String ID = "employee_id";
    private static final String STREET_ADDRESS = "street_address";
    private static final String CITY = "city";
    private static final String COUNTRY = "country";


    @Override
    public List<Address> getAll() {

        List<Address> addresses = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL_SELECT_ALL_FROM_ADDRESS)) {

            while (rs.next()) {
                Address address = new Address();
                address.setEmployeeId(rs.getInt(ID));
                address.setStreetAddress(rs.getString(STREET_ADDRESS));
                address.setCity(rs.getString(CITY));
                address.setCountry(rs.getString(COUNTRY));

                addresses.add(address);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addresses;
    }

    @Override
    public void create(Address address) {
        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_INSERT_ADDRESS)) {

            conn.setAutoCommit(false);

            stm.setInt(1, address.getEmployeeId());
            stm.setString(2, address.getStreetAddress());
            stm.setString(3, address.getCity());
            stm.setString(4, address.getCountry());

            conn.commit();
            conn.setAutoCommit(true);
            stm.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }
        LOGGER.log(Level.INFO, WRITING_DB_FINISHED);
    }

    @Override
    public void delete(Address address) {
        LOGGER.log(Level.WARNING, "NOT implemented");
    }

    @Override
    public void update(Address address) {
        LOGGER.log(Level.WARNING, "NOT implemented");
    }

    @Override
    public Address searchByEmployeeId(Integer employeeId) {

        Address address = new Address();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement("select * from employee_address WHERE employee_id=?")) {
            stm.setInt(1, employeeId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                address.setEmployeeId(rs.getInt(ID));
                address.setStreetAddress(rs.getString(STREET_ADDRESS));
                address.setCity(rs.getString(CITY));
                address.setCountry(rs.getString(COUNTRY));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }
}
