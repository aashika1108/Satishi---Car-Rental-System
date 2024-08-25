package services.login;

import entities.Customer;
import entities.Employee;
import input.LoginInputAndValidationService;
import services.database.CustomerDAO;
import services.database.EmployeeDAO;
import services.menu.LoginMenu;
import services.menu.MainMenu;
import services.password.PasswordHasher;

import java.util.Scanner;

/**
 * Service class for handling user login functionality.
 * Author: Ghazala Anjum
 */
public class LoginService {

    /**
     * Performs login for a customer.
     *
     * @param scanner The scanner object for user input.
     */
    public static void loginAsCustomer(Scanner scanner) {
        String id = LoginInputAndValidationService.getCustomerId(scanner);
        String password = LoginInputAndValidationService.getCustomerPassword(scanner);

        if (verifyCustomer(id, password)) {
            System.out.println("Login successful as Customer.");
            Customer customer = CustomerDAO.getCustomerById(id);
            MainMenu.handleCustomerMenu(customer);
        } else {
            System.out.println("Invalid id or password.");
            LoginMenu.login();
        }
    }

    /**
     * Performs login for an employee.
     *
     * @param scanner The scanner object for user input.
     */
    public static void loginAsEmployee(Scanner scanner) {
        String id = LoginInputAndValidationService.getEmployeeId(scanner);
        String password = LoginInputAndValidationService.getEmployeePassword(scanner);

        if (verifyEmployee(id, password)) {
            System.out.println("Login successful as Employee.");
            Employee employee = EmployeeDAO.getEmployeeById(id);
            if (employee.isManager()) {
                MainMenu.handleManagerMenu(employee);
            } else {
                MainMenu.handleEmployeeMenu(employee);
            }
        } else {
            System.out.println("Invalid id or password.");
            LoginMenu.login();
        }
    }

    /**
     * Verifies customer credentials.
     *
     * @param id       The customer ID.
     * @param password The customer password.
     * @return True if credentials are valid, false otherwise.
     */
    public static boolean verifyCustomer(String id, String password) {
        Customer customer = CustomerDAO.getCustomerById(id);
        if (customer != null && !customer.isRemoved()) {
            String storedPassword = customer.getPassword();
            return PasswordHasher.verifyPassword(password, storedPassword);
        } else {
            return false;
        }
    }

    /**
     * Verifies employee credentials.
     *
     * @param id       The employee ID.
     * @param password The employee password.
     * @return True if credentials are valid, false otherwise.
     */
    public static boolean verifyEmployee(String id, String password) {
        Employee employee = EmployeeDAO.getEmployeeById(id);
        if (employee != null && !employee.isRemoved()) {
            String storedPassword = employee.getPassword();
            return PasswordHasher.verifyPassword(password, storedPassword);
        } else {
            return false;
        }
    }

    /**
     * Verifies employee credentials and returns the employee object.
     *
     * @param id       The employee ID.
     * @param password The employee password.
     * @return The employee object if credentials are valid, null otherwise.
     */
    public Employee verifyAndReturnEmployee(String id, String password) {
        Employee employee = EmployeeDAO.getEmployeeById(id);
        String storedPassword = employee.getPassword();
        if (PasswordHasher.verifyPassword(password, storedPassword)) {
            return employee;
        } else {
            return null;
        }
    }
}
