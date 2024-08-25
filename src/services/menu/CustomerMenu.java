package services.menu;

import entities.Customer;
import entities.Employee;
import resource.ScannerManager;
import services.dashboards.CustomerDashboardService;

import java.util.Scanner;

/**
 * Manages the menu for customer-related operations.
 * Author: Amisha Nakrani
 */
public class CustomerMenu {

    /**
     * Retrieves the user's choice from the menu.
     *
     * @return The user's choice.
     */
    public static int getUserChoice() {
        boolean validInput = false;
        int choice = 0;
        while (!validInput) {
            System.out.print("Enter your choice: ");
            String input = ScannerManager.getScanner().nextLine();
            try {
                choice = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }

    /**
     * Displays the menu options for customers.
     */
    public static void displayCustomerMenu() {
        System.out.println("Customer Menu:");
        System.out.println("1. Edit Info");
        System.out.println("2. Main Menu");
        System.out.println("3. Exit");
    }

    /**
     * Handles the menu for customer users.
     *
     * @param customer The logged-in customer.
     */
    public static void handleCustomerMenu(Customer customer) {
        boolean validChoice = false;
        while (!validChoice) {
            displayCustomerMenu();
            int customerChoice = getUserChoice();
            switch (customerChoice) {
                case 1:
                    CustomerDashboardService.editCustomer(customer.getCustomerId());
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 2:
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 3:
                    MainMenu.exitApplication();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


    /**
     * Displays the menu options for employee users.
     */
    public static void displayEmployeeMenu() {
        System.out.println("Employee Menu:");
        System.out.println("1. Add Customer");
        System.out.println("2. Remove Customer");
        System.out.println("3. Edit Customer");
        System.out.println("4. Search Customer");
        System.out.println("5. Display Customer");
        System.out.println("6. Main Menu");
        System.out.println("7. Exit");
    }

    /**
     * Handles the menu for employee users.
     *
     * @param employee The logged-in employee.
     */
    public static void handleEmployeeMenu(Employee employee) {
        boolean validChoice = false;
        while (!validChoice) {
            displayEmployeeMenu();
            int employeeChoice = getUserChoice();
            switch (employeeChoice) {
                case 1:
                    CustomerDashboardService.addCustomer();
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 2:
                    CustomerDashboardService.removeCustomer();
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 3:
                    CustomerDashboardService.editCustomer(null);
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 4:
                    CustomerDashboardService.searchCustomer();
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 5:
                    CustomerDashboardService.displayCustomers();
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 6:
                    MainMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 7:
                    MainMenu.exitApplication();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
