package services.menu;

import entities.Customer;
import entities.Employee;
import resource.ScannerManager;
import services.dashboards.CarDashboardService;

import java.util.Scanner;

/**
 * Manages the menu for car-related operations.
 * Author: Aashika Vachhani
 */
public class CarMenu {

    /**
     * Displays the menu options for employee users.
     */
    public static void displayEmployeeMenu() {
        System.out.println("Employee Car Menu:");
        System.out.println("1. Add Car");
        System.out.println("2. Remove Car");
        System.out.println("3. Edit Car Info");
        System.out.println("4. Search Car");
        System.out.println("5. Display Cars");
        System.out.println("6. Main Menu");
        System.out.println("7. Exit");
    }

    /**
     * Displays the menu options for customer users.
     */
    public static void displayCustomerMenu() {
        System.out.println("Customer Car Menu:");
        System.out.println("1. Search Car");
        System.out.println("2. Display Cars");
        System.out.println("3. Main Menu");
        System.out.println("4. Exit");
    }

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
     * Handles the menu for employee users.
     *
     * @param employee The logged-in employee.
     */
    public static void handleEmployeeMenu(Employee employee) {
        boolean validChoice = false;
        while (!validChoice) {
            displayEmployeeMenu();
            CarDashboardService carDashboardService = new CarDashboardService();
            System.out.print("Enter your choice: ");
            int employeeChoice = getUserChoice();
            switch (employeeChoice) {
                case 1:
                    carDashboardService.addCar();
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    validChoice = true;
                    break;
                case 2:
                    carDashboardService.removeCar();
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    validChoice = true;
                    break;
                case 3:
                    carDashboardService.updateCar();
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    validChoice = true;
                    break;
                case 4:
                    carDashboardService.searchCar();
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    validChoice = true;
                    break;
                case 5:
                    carDashboardService.displayCars();
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    validChoice = true;
                    break;
                case 6:
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    validChoice = true;
                    break;
                case 7:
                    MainMenu.exitApplication();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
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
            System.out.print("Enter your choice: ");
            int customerChoice = getUserChoice();
            switch (customerChoice) {
                case 1:
                    CarDashboardService.searchCar();
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 2:
                    CarDashboardService.displayCars();
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 3:
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 4:
                    MainMenu.exitApplication();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
