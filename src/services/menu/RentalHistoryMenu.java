package services.menu;

import entities.Customer;
import entities.Employee;
import resource.ScannerManager;
import services.dashboards.RentalHistoryDashboardService;

import java.util.Scanner;

/**
 * Manages the rental history menu for both employees and customers.
 * Author: Arshdeep Singh
 */
public class RentalHistoryMenu {

    /**
     * Displays the rental history menu options.
     */
    public static void displayMenu() {
        System.out.println("Rental History Menu:");
        System.out.println("1. Display history");
        System.out.println("2. Invoice");
        System.out.println("3. Main Menu");
        System.out.println("4. Exit");
    }

    /**
     * Handles rental history menu choices for employees.
     * @param employee The logged-in employee.
     */
    public static void handleEmployeeChoice(Employee employee) {
        boolean validChoice = false;
        while (!validChoice) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    RentalHistoryDashboardService.displayRentalHistory();
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 2:
                    RentalHistoryDashboardService.generateInvoice();
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 3:
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 4:
                    MainMenu.exitApplication();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    /**
     * Handles rental history menu choices for customers.
     * @param customer The logged-in customer.
     */
    public static void handleCustomerChoice(Customer customer) {
        boolean validChoice = false;
        while (!validChoice) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    RentalHistoryDashboardService.displayRentalHistoryByCustomerId(customer);
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 2:
                    RentalHistoryDashboardService.generateInvoice();
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 3:
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 4:
                    MainMenu.exitApplication();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    /**
     * Gets user input for menu choice.
     * @return The user's menu choice.
     */
    public static int getUserChoice() {
        Scanner scanner = ScannerManager.getScanner();
        boolean validInput = false;
        int choice = 0;
        while (!validInput) {
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }

}
