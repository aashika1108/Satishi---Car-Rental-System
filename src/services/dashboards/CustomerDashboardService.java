package services.dashboards;

import entities.Customer;
import input.CustomerInputAndValidationService;
import resource.ScannerManager;
import services.database.CustomerDAO;
import services.password.PasswordHasher;

import java.util.List;
import java.util.Scanner;

/**
 * Dashboard service for managing customers.
 * Author: Amisha Nakrani
 */
public class CustomerDashboardService {
    // Scanner instance to manage user input
    static Scanner scanner = ScannerManager.getScanner();

    /**
     * Displays all customers.
     */
    public static void displayCustomers() {
        List<Customer> customers = CustomerDAO.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("List of Customers:");
            System.out.printf("%-12s%-15s%-15s%-25s%-15s%-10s\n", "Customer ID", "First Name", "Last Name", "Email", "Contact No", "Is Removed");
            for (Customer customer : customers) {
                System.out.printf("%-12s%-15s%-15s%-25s%-15s%-10s\n", customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getContactNo(), customer.isRemoved());
            }
        }
    }

    /**
     * Adds a new customer.
     */
    public static void addCustomer() {
        String firstName = CustomerInputAndValidationService.getFirstName(scanner);
        String lastName = CustomerInputAndValidationService.getLastName(scanner);
        String address = CustomerInputAndValidationService.getAddress(scanner);
        String email = CustomerInputAndValidationService.getEmail(scanner);
        long contactNo = CustomerInputAndValidationService.getContactNo(scanner);
        String password = CustomerInputAndValidationService.getPassword(scanner);
        boolean isRemoved = CustomerInputAndValidationService.isRemoved(scanner);

        Customer customer = new Customer(null, firstName, lastName, address, email, contactNo, isRemoved, PasswordHasher.hashPassword(password));

        CustomerDAO.addCustomer(customer);
    }

    /**
     * Removes a customer.
     */
    public static void removeCustomer() {
        String customerId = CustomerInputAndValidationService.getCustomerIdForUpdate(scanner);
        CustomerDAO.removeCustomer(customerId);
    }

    /**
     * Edits customer information.
     */
    public static void editCustomer(String id) {
        System.out.println("Editing customer information...");
        String customerId = id;
        if (null == id) {
            customerId = CustomerInputAndValidationService.getCustomerIdForUpdate(scanner);
        }
        String firstName = CustomerInputAndValidationService.getFirstName(scanner);
        String lastName = CustomerInputAndValidationService.getLastName(scanner);
        String address = CustomerInputAndValidationService.getAddress(scanner);
        String email = CustomerInputAndValidationService.getEmail(scanner);
        long contactNo = CustomerInputAndValidationService.getContactNo(scanner);
        String password = CustomerInputAndValidationService.getPassword(scanner);
        boolean isRemoved = CustomerInputAndValidationService.isRemoved(scanner);

        Customer customer = new Customer(customerId, firstName, lastName, address, email, contactNo, isRemoved, PasswordHasher.hashPassword(password));

        CustomerDAO.editCustomer(customer);
    }

    /**
     * Searches for customers.
     */
    public static void searchCustomer() {
        String keyword = CustomerInputAndValidationService.getSearchTermForCustomer(scanner);

        List<Customer> foundCustomers = CustomerDAO.searchCustomer(keyword);
        if (foundCustomers.isEmpty()) {
            System.out.println("No customers found matching the keyword.");
        } else {
            System.out.println("Found customers:");
            System.out.printf("%-12s%-15s%-15s%-25s%-15s%-10s\n", "Customer ID", "First Name", "Last Name", "Email", "Contact No", "Is Removed");
            for (Customer customer : foundCustomers) {
                System.out.printf("%-12s%-15s%-15s%-25s%-15s%-10s\n", customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getContactNo(), customer.isRemoved());
            }
        }
    }
}
