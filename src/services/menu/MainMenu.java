package services.menu;

import entities.Customer;
import entities.Employee;
import resource.ScannerManager;
import services.dashboards.FinanceDashboardService;

import java.util.Scanner;

/**
 * Manages the main menu for different user roles.
 * Author: Ghazala Anjum
 */
public class MainMenu {

    /**
     * Displays the main menu for customers.
     */
    public static void displayCustomerMenu() {
        System.out.println("Customer Main Menu:");
        System.out.println("1. Car Dashboard");
        System.out.println("2. Customer Dashboard");
        System.out.println("3. Rental History and Invoice");
        System.out.println("4. Booking and Return Dashboard");
        System.out.println("5. Exit");
    }

    /**
     * Displays the main menu for employees.
     */
    public static void displayEmployeeMenu() {
        System.out.println("Employee Main Menu:");
        System.out.println("1. Car Dashboard");
        System.out.println("2. Customer Dashboard");
        System.out.println("3. Employee Dashboard");
        System.out.println("4. Rental History and Invoice");
        System.out.println("5. Booking and Return Dashboard");
        System.out.println("6. Fleet Maintenance");
        System.out.println("7. Exit");
    }

    /**
     * Displays the main menu for managers.
     */
    public static void displayManagerMenu() {
        System.out.println("Manager Main Menu:");
        System.out.println("1. Car Dashboard");
        System.out.println("2. Customer Dashboard");
        System.out.println("3. Employee Dashboard");
        System.out.println("4. Rental History and Invoice");
        System.out.println("5. Booking and Return Dashboard");
        System.out.println("6. Fleet Maintenance");
        System.out.println("7. Finance Report");
        System.out.println("8. Exit");
    }

    /**
     * Exits the application.
     */
    public static void exitApplication() {
        System.out.println("Exiting!");
        ScannerManager.closeScanner();
        System.exit(0);
    }

    /**
     * Gets user's choice from the menu.
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
     * Handles the main menu for customers.
     * @param customer The logged-in customer.
     */
    public static void handleCustomerMenu(Customer customer) {
        boolean validChoice = false;
        while (!validChoice) {
            displayCustomerMenu();
            int customerChoice = getUserChoice();
            switch (customerChoice) {
                case 1:
                    CarMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 2:
                    CustomerMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 3:
                    RentalHistoryMenu.handleCustomerChoice(customer);
                    validChoice = true;
                    break;
                case 4:
                    BookingAndReturnMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 5:
                    exitApplication();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Handles the main menu for employees.
     * @param employee The logged-in employee.
     */
    public static void handleEmployeeMenu(Employee employee) {
        boolean validChoice = false;
        while (!validChoice) {
            displayEmployeeMenu();
            int employeeChoice = getUserChoice();
            switch (employeeChoice) {
                case 1:
                    CarMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 2:
                    CustomerMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 3:
                    EmployeeMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 4:
                    RentalHistoryMenu.handleEmployeeChoice(employee);
                    validChoice = true;
                    break;
                case 5:
                    BookingAndReturnMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 6:
                    FleetDashboardMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 7:
                    exitApplication();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Handles the main menu for managers.
     * @param employee The logged-in manager.
     */
    public static void handleManagerMenu(Employee employee) {
        boolean validChoice = false;
        while (!validChoice) {
            displayManagerMenu();
            System.out.print("Enter your choice: ");
            int managerChoice = getUserChoice();
            switch (managerChoice) {
                case 1:
                    CarMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 2:
                    CustomerMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 3:
                    EmployeeMenu.handleManagerMenu(employee);
                    validChoice = true;
                    break;
                case 4:
                    RentalHistoryMenu.handleEmployeeChoice(employee);
                    validChoice = true;
                    break;
                case 5:
                    BookingAndReturnMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 6:
                    FleetDashboardMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 7:
                    FinanceDashboardMenu.handleMenu(employee);
                    validChoice = true;
                    break;
                case 8:
                    exitApplication();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
