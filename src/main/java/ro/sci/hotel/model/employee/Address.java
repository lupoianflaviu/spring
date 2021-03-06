package ro.sci.hotel.model.employee;

/**
 * Employee address blueprint
 */
public class Address {

    private int employeeId;
    private String streetAddress;
    private String city;
    private String country;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" + "employeeId=" + employeeId + ", streetAddress='" + streetAddress + '\'' + ", city='" + city + '\'' + ", country='" + country + '\''
                + '}';
    }

}
