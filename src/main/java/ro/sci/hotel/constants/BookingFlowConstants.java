package ro.sci.hotel.constants;

import java.util.logging.Logger;

public class BookingFlowConstants {

    public static final String DATABASE_ERROR = "Database error!";
    public static final String EXCEPTION_THROWN = "Exception thrown";
    public static final Logger LOGGER = Logger.getLogger("Hotel");
    public static final String WRITING_IN_DB_HAS_FINISHED = "Writing in db has finished!";
    public static final String SQL_INSERT_INTO_BOOKING_ROOMNUMBER_CUSTOMERID_STARTDATE_ENDDATE_VALUES =
            "INSERT INTO booking(roomnumber,customerid,startdate,enddate) values(?,?,?,?)";
    public static final String BOOKING_DELETE_HAS_COMPLETED = "Deletion of booking completed";
    public static final String SQL_UPDATE_BOOKING_WHERE_ID = "UPDATE booking " + "SET roomnumber=?, customerid=?, startdate=?, enddate=? WHERE id = ?";
    public static final String BOOKING_UPDATE_IN_DB_HAS_COMPLETED = "Booking update in db has completed";
    public static final String SQL_SELECT_ALL__FROM_BOOKING = "SELECT * FROM booking";
    public static final String ID = "id";
    public static final String ROOMNUMBER = "roomnumber";
    public static final String CUSTOMERID = "customerid";
    public static final String STARTDATE = "startdate";
    public static final String ENDDATE = "enddate";
    public static final String SQL_DELETE_FROM_BOOKING_WHERE_ID = "DELETE FROM booking where id=?";
    public static final String SQL_SELECT_FROM_BOOKING_WHERE_CUSTOMERID = "SELECT * FROM booking WHERE customerid=?";
    public static final String SQL_SELECT_ENDDATE_STARTDATE_FROM_BOOKING_WHERE_ID = "SELECT enddate - startdate FROM booking WHERE id=?";
    public static final String SQL_SELECT_FROM_BOOKING_WHERE_ROOMNUMBER = "SELECT * FROM booking WHERE roomnumber=?";
    public static final String SQL_SELECT_FROM_BOOKING_WHERE_STARTDATE_AND_ENDDATE = "SELECT * FROM booking WHERE startdate>=? AND enddate<=?";
    public static final String SQL_SELECT_FROM_BOOKING_WHERE_PRICE = "SELECT * FROM booking WHERE price=?";
    public static final String SQL_SELECT_FROM_BOOKING_WHERE_ID = "SELECT * FROM booking WHERE id=?";
}
