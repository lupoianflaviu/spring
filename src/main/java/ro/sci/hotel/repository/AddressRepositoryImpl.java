package ro.sci.hotel.repository;

import ro.sci.hotel.model.employee.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressRepositoryImpl extends BaseRepository  implements AddressRepository<Address> {

    private static final String SQL_SELECT_ALL_FROM_ADDRESS="SELECT employee_id, street_address, city, country FROM employee_address";
    private static final String ID="employeeID";

    @Override
    public List<Address> getAll() {

        List<Address> addresses = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL_SELECT_ALL_FROM_ADDRESS)){

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addresses;
    }

    @Override
    public void create(Address address) {

    }

    @Override
    public void delete(Address address) {

    }

    @Override
    public void update(Address address) {

    }

    @Override
    public Address searchByEmployeeId(Integer EmployeeId) {
        return null;
    }
}
