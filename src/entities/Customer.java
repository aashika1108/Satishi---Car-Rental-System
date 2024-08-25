package entities;

/**
 * Represents a customer entity in the Car Rental Management System.
 * Author: Amisha Nakrani
 */

public class Customer {
    private String customerId; // Unique identifier for the customer
    private String firstName; // First name of the customer
    private String lastName; // Last name of the customer
    private String address; // Address of the customer
    private String email; // Email address of the customer
    private long contactNo; // Contact number of the customer
    private boolean isRemoved; // Removal status of the customer
    private String password; // Password of the customer

    /**
     * Constructor for the Customer class.
     * @param customerId The unique identifier for the customer.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param address The address of the customer.
     * @param email The email address of the customer.
     * @param contactNo The contact number of the customer.
     * @param isRemoved The removal status of the customer.
     * @param password The password of the customer.
     */

    public Customer(String customerId, String firstName, String lastName, String address, String email, long contactNo, boolean isRemoved, String password) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.contactNo = contactNo;
        this.isRemoved = isRemoved;
        this.password = password;
    }

    // Getters and setters

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Override toString method to provide a string representation of the Customer object.
     * @return A string representation of the Customer object.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contactNo=" + contactNo +
                ", isRemoved=" + isRemoved +
                '}';
    }
}
