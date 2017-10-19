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

import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.customer.CustomerAddress;
import ro.sci.hotel.model.customer.PaymentMethod;

/**
 * Customer repository implementation
 */
@Repository("customerRepository")
public class CustomerRepositoryImpl extends BaseRepository implements CustomerRepository<Customer> {

    private static final String DATABASE_ERROR = "Database error!";

    private static final String EXCEPTION_THROWN = "Exception thrown";

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    private static final String WRITING_IN_DB_HAS_FINISHED = "Writing in db has finished!";

    private static final String SQL_SELECT_ALL__FROM_CUSTOMERS = "SELECT * FROM customer";

    private static final String ID = "id";

    private static final String FIRSTNAME = "firstname";

    private static final String LASTNAME = "lastname";

    private static final String EMAIL = "email";

    private static final String PHONENUMBER = "phonenumber";

    private static final String STREETADDRESS = "streetaddress";

    private static final String CITY = "city";

    private static final String COUNTRY = "country";

    private static final String PAYMENTMETHOD = "paymentmethod";

    private static final String SQL_INSERT_INTO_CUSTOMER_ID_FIRSTNAME_LASTNAME_EMAIL_PHONENUMBER_STREETADDRESS_CITY_COUNTRY_PAYMENTMETHOD_VALUES =
            "INSERT INTO customer(id,firstname,lastname,email,phonenumber,streetaddress,city,country,paymentmethod) values(?,?,?,?,?,?,?,?,?)";

    private static final String SQL_DELETE_FROM_CUSTOMER_WHERE_ID = "DELETE FROM customer where id=?";

    private static final String CUSTOMER_DELETE_HAS_COMPLETED = "Deletion of customer completed";

    private static final String SQL_UPDATE_CUSTOMER_WHERE_ID = "UPDATE customer " + "SET firstname=?, lastname=?, email=?, oceanview=?, phonenumber=?, streetaddress=?, city=?, country=?, paymentmethod=? WHERE id = ?";

    private static final String CUSTOMER_UPDATE_IN_DB_HAS_COMPLETED = "Customer update in db has completed";

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL_SELECT_ALL__FROM_CUSTOMERS)) {

            while (rs.next()) {

                Customer customer = new Customer();
                customer.setId(rs.getInt(ID));
                customer.setFirstName(rs.getString(FIRSTNAME));
                customer.setLastName(rs.getString(LASTNAME));
                customer.setEmail(rs.getString(EMAIL));
                customer.setPhoneNumber(rs.getString(PHONENUMBER));
                customer.setCustomerAddress(new CustomerAddress(rs.getString(STREETADDRESS), rs.getString(CITY), rs.getString(COUNTRY)));
                customer.setPaymentMethod(PaymentMethod.valueOf(rs.getString(PAYMENTMETHOD)));

                customers.add(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return customers;
    }

    @Override
    public void create(Customer customer, CustomerAddress customerAddress) {

        try (Connection conn = newConnection();
             PreparedStatement stm = conn.prepareStatement(SQL_INSERT_INTO_CUSTOMER_ID_FIRSTNAME_LASTNAME_EMAIL_PHONENUMBER_STREETADDRESS_CITY_COUNTRY_PAYMENTMETHOD_VALUES)) {


            stm.setInt(1, customer.getId());
            stm.setString(2, customer.getFirstName());
            stm.setString(3, customer.getLastName());
            stm.setString(4, customer.getEmail());
            stm.setString(5, customer.getPhoneNumber());
            stm.setString(6, String.valueOf(customerAddress.getStreetAddress()));
            stm.setString(7, String.valueOf(customerAddress.getCity()));
            stm.setString(8, String.valueOf(customerAddress.getCountry()));
            stm.setString(9, String.valueOf(customer.getPaymentMethod()));


            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, WRITING_IN_DB_HAS_FINISHED);
    }

    @Override
    public void delete(Customer customer) {

        try (Connection conn = newConnection();
             PreparedStatement stm = conn.prepareStatement(SQL_DELETE_FROM_CUSTOMER_WHERE_ID)) {

            stm.setInt(1, customer.getId());
            stm.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, CUSTOMER_DELETE_HAS_COMPLETED);
    }

    @Override
    public void update(Customer customer) {
        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_UPDATE_CUSTOMER_WHERE_ID)) {

            stm.setString(1, customer.getFirstName());
            stm.setString(2, customer.getLastName());
            stm.setString(3, customer.getEmail());
            stm.setString(4, customer.getPhoneNumber());
            stm.setString(5, String.valueOf(customer.getCustomerAddress()));
            stm.setString(6, String.valueOf(customer.getPaymentMethod()));

            stm.setInt(8, customer.getId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, CUSTOMER_UPDATE_IN_DB_HAS_COMPLETED);

    }

    @Override
    public Customer searchByCustomerId(int id) {
        Customer customer = new Customer();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement("SELECT * FROM customer WHERE id=?")) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                customer.setId(id);
                customer.setFirstName(rs.getString(FIRSTNAME));
                customer.setLastName(rs.getString(LASTNAME));
                customer.setEmail(rs.getString(EMAIL));
                customer.setPhoneNumber(rs.getString(PHONENUMBER));
                customer.setCustomerAddress(new CustomerAddress(rs.getString(STREETADDRESS), rs.getString(CITY), rs.getString(COUNTRY)));
                customer.setPaymentMethod(PaymentMethod.valueOf(rs.getString(PAYMENTMETHOD)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return customer;
    }

    @Override
    public List<Customer> searchByLastName(String lastName) {
        return null;
    }
}
