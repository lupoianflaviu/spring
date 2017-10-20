package ro.sci.hotel.constants;

public final class EmployeeFlowConstants {


    private EmployeeFlowConstants() {
    }

    public static final String DATABASE_ERROR = "Database error!";

    public static final String EXCEPTION_THROWN = "Exception thrown";

    public static final String WRITING_DB_FINISHED = "Writing in db has finished!";

    public static final String Employee_DELETED = "Deletion of employee by ID successful";

    public static final String ID = "employee_id";

    public static final String FIRSTNAME = "first_name";

    public static final String LASTNAME = "last_name";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String PHONENUMBER = "employee_phone_number";

    public static final String EMPLOYMENTDATE = "employment_date";

    public static final String EMPLOYEEROLE = "employee_role";

    public static final String PRICE = "price";

    public static final String CURRENCY = "currency";

    public static final String SQL_SELECT_ALL_FROM_EMPLOYEE =
            "SELECT employee_id, first_name, last_name, email, employee_phone_number, employment_date, price, employee_role FROM employee";

    public static final String SQL_INSERT_EMPLOYEE =
            "INSERT INTO employee (employee_id,first_name, last_name,email,username,password, employee_phone_number, employment_date,employee_role) VALUES (?,?,?,?,?,?,?,?,?)";

    public static final String SQL_UPDATE_EMPLOYEE =
            "UPDATE employee  SET first_name=?, last_name=?, email=?, username=?,password=?, employee_phone_number=? WHERE employee_id = ?";

    public static final String DELETE_EMPLOYEE = "Delete from employee where employee_id=?";

    public static final String SQL_SELECT_USERNAME_PASSWORD = "SELECT * FROM employee where username=? AND password=?";

    public static final String SQL_SELECT_BY_FIRST_NAME = "SELECT * FROM employee where  first_name=? ";

    public static final String SQL_SELECT_BY_ID = "SELECT * FROM employee WHERE  employee_id=?";

}
