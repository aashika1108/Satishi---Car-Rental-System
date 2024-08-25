package services.menu;

import entities.Employee;
import resource.ScannerManager;
import services.dashboards.EmployeeDashboardService;

/**
 * Manages the menu for employee-related operations.
 * Author: Arshdeep Singh
 */
public class EmployeeMenu {

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
     * Displays the menu options for regular employees.
     */
    public static void displayEmployeeMenu() {
        System.out.println("Employee Menu:");
        System.out.println("1. Edit employee");
        System.out.println("2. Main Menu");
        System.out.println("3. Exit");
    }

    /**
     * Displays the menu options for manager employees.
     */
    public static void displayManagerMenu() {
        System.out.println("Employee Menu:");
        System.out.println("1. Add Employee");
        System.out.println("2. Remove Employee");
        System.out.println("3. Edit employee");
        System.out.println("4. Search employee");
        System.out.println("5. Display employee");
        System.out.println("6. Main Menu");
        System.out.println("7. Exit");
    }

    /**
     * Handles the menu for regular employee users.
     *
     * @param employee The logged-in employee.
     */
    public static void handleEmployeeMenu(Employee employee) {
        boolean validChoice = false;
        while (!validChoice) {
            displayEmployeeMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    EmployeeDashboardService.updateEmployee(employee.getEmployeeId());
                    validChoice = true;
                    MainMenu.handleEmployeeMenu(employee);
                    break;
                case 2:
                    MainMenu.handleEmployeeMenu(employee);
                    validChoice = true;
                    break;
                case 3:
                    MainMenu.exitApplication();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    /**
     * Handles the menu for manager employee users.
     *
     * @param employee The logged-in manager employee.
     */
    public static void handleManagerMenu(Employee employee) {
        boolean validChoice = false;
        while (!validChoice) {
            displayManagerMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    EmployeeDashboardService.addEmployee();
                    validChoice = true;
                    MainMenu.handleManagerMenu(employee);
                    break;
                case 2:
                    EmployeeDashboardService.removeEmployee();
                    validChoice = true;
                    MainMenu.handleManagerMenu(employee);
                    break;
                case 3:
                    EmployeeDashboardService.updateEmployee(null);
                    validChoice = true;
                    MainMenu.handleManagerMenu(employee);
                    break;
                case 4:
                    EmployeeDashboardService.searchEmployee();
                    validChoice = true;
                    MainMenu.handleManagerMenu(employee);
                    break;
                case 5:
                    EmployeeDashboardService.displayEmployees();
                    validChoice = true;
                    MainMenu.handleManagerMenu(employee);
                    break;
                case 6:
                    MainMenu.handleManagerMenu(employee);
                    validChoice = true;
                    break;
                case 7:
                    MainMenu.exitApplication();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
