package services.menu;

import entities.Employee;
import resource.ScannerManager;
import services.dashboards.FleetDashboardService;

import java.util.Scanner;

/**
 * Manages the menu for fleet dashboard operations.
 * Author: Ghazala Anjum
 */
public class FleetDashboardMenu {

    /**
     * Displays the menu options for the fleet dashboard.
     */
    public static void displayMenu() {
        System.out.println("Fleet Dashboard Menu:");
        System.out.println("1. Cars due for oil change");
        System.out.println("2. Cars due for washing");
        System.out.println("3. Cars due for inspection");
        System.out.println("4. Out-of-service cars");
        System.out.println("5. Main Menu");
        System.out.println("6. Exit");
    }

    /**
     * Retrieves the user's choice from the menu.
     *
     * @param scanner The scanner object to get user input.
     * @return The user's choice.
     */
    public static int getUserChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return choice;
    }

    /**
     * Handles the menu options for the fleet dashboard.
     *
     * @param employee The logged-in employee.
     */
    public static void handleEmployeeMenu(Employee employee) {
        boolean validChoice = false;
        Scanner scanner = ScannerManager.getScanner();
        while (!validChoice) {
            displayMenu();
            int choice = getUserChoice(scanner);
            switch (choice) {
                case 1:
                    FleetDashboardService.displayCarsDueForOilChange();
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 2:
                    FleetDashboardService.displayCarsDueForWashing();
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 3:
                    FleetDashboardService.displayCarsDueForInspection();
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 4:
                    FleetDashboardService.displayOutOfServiceCars();
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 5:
                    validChoice = true;
                    if (employee.isManager()) {
                        MainMenu.handleManagerMenu(employee);
                    } else {
                        MainMenu.handleEmployeeMenu(employee);
                    }
                    break;
                case 6:
                    validChoice = true;
                    MainMenu.exitApplication();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }
}
