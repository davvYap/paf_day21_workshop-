package sg.edu.nus.iss.workshop21.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Customer {
    private int id;
    private String company;
    private String lastName;
    private String firstName;
    private String email;
    private String jobTitle;
    private String businessPhone;
    private String homePhone;
    private String mobilePhone;
    private String address;
    private String stateProvince;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", company=" + company + ", lastName=" + lastName + ", firstName=" + firstName
                + ", email=" + email + ", jobTitle=" + jobTitle + ", businessPhone=" + businessPhone + ", homePhone="
                + homePhone + ", mobilePhone=" + mobilePhone + ", address=" + address + ", stateProvince="
                + stateProvince + "]";
    }

    public static Customer createFromResults(SqlRowSet result) {
        Customer customer = new Customer();
        customer.setId(result.getInt("id"));
        customer.setCompany(result.getString("company"));
        customer.setLastName(result.getString("last_name"));
        customer.setFirstName(result.getString("first_name"));
        return customer;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("id", this.getId())
                .add("company", this.getCompany())
                .add("firstName", this.getFirstName())
                .add("lastName", this.getLastName())
                .build();
    }

}
