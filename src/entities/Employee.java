package entities;


import java.sql.Date;

/**
 * Represents an employee entity in the Car Rental Management System.
 * Author: Arshdeep Singh
 */

public class Employee {
    private String employeeId; // Unique identifier for the employee
    private String firstName; // First name of the employee
    private String lastName; // Last name of the employee
    private String address; // Address of the employee
    private String email; // Email address of the employee
    private long phoneNumber; // Phone number of the employee
    private Date dateOfBirth; // Date of birth of the employee
    private Date hireDate; // Hire date of the employee
    private boolean removed; // Removal status of the employee
    private boolean manager; // Managerial status of the employee
    private String password; // Password of the employee

    /**
     * Constructor for the Employee class.
     *
     * @param employeeId  The unique identifier for the employee.
     * @param firstName   The first name of the employee.
     * @param lastName    The last name of the employee.
     * @param address     The address of the employee.
     * @param email       The email address of the employee.
     * @param phoneNumber The phone number of the employee.
     * @param dateOfBirth The date of birth of the employee.
     * @param hireDate    The hire date of the employee.
     * @param removed     The removal status of the employee.
     * @param manager     The managerial status of the employee.
     * @param password    The password of the employee.
     */

    public Employee(String employeeId, String firstName, String lastName, String address, String email, long phoneNumber, Date dateOfBirth, Date hireDate, boolean removed, boolean manager, String password) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
        this.removed = removed;
        this.manager = manager;
        this.password = password;
    }

    // Getters and setters

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Override toString method to provide a string representation of the Employee object.
     *
     * @return A string representation of the Employee object.
     */

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", hireDate=" + hireDate +
                ", removed=" + removed +
                ", manager=" + manager +
                '}';
    }
}
