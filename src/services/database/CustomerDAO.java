package services.database;


import entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import populators.CustomerPopulator;

/**
 * Data access object for managing customer-related operations in the database.
 * Author: Amisha Nakrani
 */
public class CustomerDAO {

    /**
     * Adds a new customer to the database.
     *
     * @param customer The customer object to be added.
     */
    public static void addCustomer(Customer customer) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Customer (first_name, last_name, address, email, contact_no, is_removed, password) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getEmail());
            statement.setLong(5, customer.getContactNo());
            statement.setBoolean(6, false); // Initially not removed
            statement.setString(7, customer.getPassword());
            statement.executeUpdate();
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a customer from the database based on the provided customer ID.
     *
     * @param customerId The ID of the customer to be removed.
     */

    public static void removeCustomer(String customerId) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("UPDATE Customer SET is_removed = ? WHERE customer_id = ?")) {
            statement.setBoolean(1, true);
            statement.setString(2, customerId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer removed successfully.");
            } else {
                System.out.println("No customer found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Edits the information of an existing customer in the database.
     *
     * @param customer The updated customer object.
     */
    public static void editCustomer(Customer customer) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("UPDATE Customer SET first_name = ?, last_name = ?, address = ?, email = ?, contact_no = ?, is_removed = ?, password = ? WHERE customer_id = ?")) {
            statement.setString(1, customer.getFirstName()); // Added attribute
            statement.setString(2, customer.getLastName()); // Added attribute
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getEmail());
            statement.setLong(5, customer.getContactNo());
            statement.setBoolean(6, customer.isRemoved());
            statement.setString(7, customer.getPassword());
            statement.setString(8, customer.getCustomerId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer information updated successfully.");
            } else {
                System.out.println("No customer found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a customer from the database based on the provided customer ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return The customer object retrieved from the database.
     */
    public static Customer getCustomerById(String customerId) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customer WHERE customer_id = ?")) {
            statement.setString(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return CustomerPopulator.populateFromResultSet(resultSet);
                } else {
                    System.out.println("No customer found with the given ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Searches for customers in the database based on a keyword.
     *
     * @param keyword The keyword to search for in customer IDs, emails, and contact numbers.
     * @return A list of customers matching the search criteria.
     */
    public static List<Customer> searchCustomer(String keyword) {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customer WHERE customer_id LIKE ? OR email LIKE ? OR contact_no LIKE ?")) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    customers.add(CustomerPopulator.populateFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of all customers stored in the database.
     */
    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer")) {
            while (resultSet.next()) {
                customers.add(CustomerPopulator.populateFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Retrieves all customer IDs from the database.
     *
     * @return A list of all customer IDs stored in the database.
     */

    public static List<String> getAllCustomerIds() {
        List<String> customerIds = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT customer_id FROM Customer")) {
            while (resultSet.next()) {
                customerIds.add(resultSet.getString("customer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerIds;
    }

    /**
     * Retrieves all customer emails from the database.
     *
     * @return A list of all customer emails stored in the database.
     */
    public static List<String> getAllCustomerEmails() {
        List<String> emails = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT email FROM Customer")) {
            while (resultSet.next()) {
                emails.add(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    /**
     * Retrieves all customer contact numbers from the database.
     *
     * @return A list of all customer contact numbers stored in the database.
     */
    public static List<Long> getAllCustomerContactNumbers() {
        List<Long> contactNumbers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT contact_no FROM Customer")) {
            while (resultSet.next()) {
                contactNumbers.add(resultSet.getLong("contact_no"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactNumbers;
    }

}


