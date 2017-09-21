package ro.sci.hotel.repository;


import org.springframework.stereotype.Repository;
import ro.sci.hotel.model.customer.Customer;
import ro.sci.hotel.model.customer.CustomerAddress;
import ro.sci.hotel.model.customer.PaymentMethod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Customer repository implementation
 */
@Repository("customerRepository")
public class CustomerRepositoryImpl extends BaseRepository implements CustomerRepository<Customer>{

    private static final String DATABASE_ERROR = "Database error!";

    private static final String EXCEPTION_THROWN = "Exception thrown";

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    private static final String WRITING_IN_DB_HAS_FINISHED = "Writing in db has finished!";

    private static final String SQL_INSERT_INTO_BOOKING_ROOMNUMBER_CUSTOMERID_STARTDATE_ENDDATE_VALUES =
            "INSERT INTO booking(roomnumber,customerid,startdate,enddate) values(?,?,?,?)";

    private static final String BOOKING_DELETE_HAS_COMPLETED = "Deletion of booking completed";

    private static final String SQL_UPDATE_BOOKING_WHERE_ID = "UPDATE booking " + "SET roomnumber=?, customerid=?, startdate=?, enddate=? WHERE id = ?";

    private static final String BOOKING_UPDATE_IN_DB_HAS_COMPLETED = "Booking update in db has completed";

    private static final String SQL_SELECT_ALL__FROM_ROOMS = "SELECT * FROM rooms";

    private static final String ID = "id";

    private static final String FIRSTNAME = "firstname";

    private static final String LASTNAME = "lastname";

    private static final String EMAIL = "email";

    private static final String PHONENUMBER = "phonenumber";

    private static final String STREETADRESS = "streetadress";

    private static final String CITY = "CITY";

    private static final String COUNTRY = "COUNTRY";

    private static final String PAYMENTMETHOD = "paymentmethod";

    private static final String SQL_DELETE_FROM_BOOKING_WHERE_ID = "DELETE FROM booking where id=?";

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL_SELECT_ALL__FROM_ROOMS)) {

            while (rs.next()) {

                Customer customer = new Customer();
                customer.setId(rs.getInt(ID));
                customer.setFirstName(rs.getString(FIRSTNAME));
                customer.setLastName(rs.getString(LASTNAME));
                customer.setEmail(rs.getString(EMAIL));
                customer.setPhoneNumber(rs.getString(PHONENUMBER));
                customer.setCustomerAddress(new CustomerAddress(rs.getString(STREETADRESS),rs.getString(CITY),rs.getString(COUNTRY)));
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
    public void create(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public Customer searchByCustomerId(int id) {
        return null;
    }

    @Override
    public List<Customer> searchByFirstName(String firstName) {
        return null;
    }
}
