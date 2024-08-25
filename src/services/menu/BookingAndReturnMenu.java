package services.menu;

import entities.Customer;
import entities.Employee;
import resource.ScannerManager;
import services.dashboards.BookingAndReturnDashboardService;

import java.util.Scanner;

/**
 * Manages the booking and return operations menu.
 * Author: Amisha Nakrani
 */
public class BookingAndReturnMenu {

    /**
     * Displays the menu options for booking and returning cars.
     */
    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Book a car");
        System.out.println("2. Return a car");
        System.out.println("3. Cancel a booking");
        System.out.println("4. Main Menu");
        System.out.println("5. Exit");
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
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    // Logic for booking a car
                    BookingAndReturnDashboardService.addBooking(null);
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    validChoice = true;
                    break;
                case 2:
                    // Logic for returning a car
                    BookingAndReturnDashboardService.returnCar();
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    validChoice = true;
                    break;
                case 3:
                    // Logic for canceling a booking
                    BookingAndReturnDashboardService.cancelBooking();
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    validChoice = true;
                    break;
                case 4:
                    // Logic for going to the main menu
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    validChoice = true;
                    break;
                case 5:
                    // Logic for exiting the application
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
     * Handles the menu for customer users.
     *
     * @param customer The logged-in customer.
     */
    public static void handleCustomerMenu(Customer customer) {
        boolean validChoice = false;
        while (!validChoice) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    // Logic for booking a car
                    BookingAndReturnDashboardService.addBooking(customer.getCustomerId());
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 2:
                    // Logic for returning a car
                    BookingAndReturnDashboardService.returnCar();
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 3:
                    // Logic for canceling a booking
                    BookingAndReturnDashboardService.cancelBooking();
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 4:
                    // Logic for going to the main menu
                    MainMenu.handleCustomerMenu(customer);
                    validChoice = true;
                    break;
                case 5:
                    // Logic for exiting the application
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
