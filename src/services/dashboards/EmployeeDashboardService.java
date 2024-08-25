package services.dashboards;

import entities.Employee;
import input.EmployeeInputAndValidationService;
import resource.ScannerManager;
import services.database.EmployeeDAO;
import services.password.PasswordHasher;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Dashboard service for managing employees.
 * Author: Arshdeep Singh
 */
public class EmployeeDashboardService {
    // Scanner instance to manage user input
    static Scanner scanner = ScannerManager.getScanner();

    /**
     * Displays all employees.
     */
    public static void displayEmployees() {
        List<Employee> employees = EmployeeDAO.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("List of Employees:");
            System.out.printf("%-12s %-15s %-15s %-30s %-25s %-15s %-15s %-12s %-10s%n",
                    "Employee ID", "First Name", "Last Name", "Address", "Email", "Phone Number",
                    "Date of Birth", "Hire Date", "Manager");
            for (Employee employee : employees) {
                System.out.printf("%-12s %-15s %-15s %-30s %-25s %-15s %-15s %-12s %-10s%n",
                        employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
                        employee.getAddress(), employee.getEmail(), employee.getPhoneNumber(),
                        employee.getDateOfBirth(), employee.getHireDate(),
                        employee.isManager() ? "Yes" : "No");
            }
        }
    }

    /**
     * Searches for employees.
     */
    public static void searchEmployee() {
        String searchTerm = EmployeeInputAndValidationService.getSearchTermForEmployee(scanner);

        List<Employee> searchResults = EmployeeDAO.searchEmployee(searchTerm);

        if (searchResults.isEmpty()) {
            System.out.println("No employees found matching the search term: " + searchTerm);
        } else {
            System.out.println("Search Results:");
            System.out.printf("%-12s %-15s %-15s %-30s %-25s %-15s %-15s %-12s %-10s%n",
                    "Employee ID", "First Name", "Last Name", "Address", "Email", "Phone Number",
                    "Date of Birth", "Hire Date", "Manager");
            for (Employee employee : searchResults) {
                System.out.printf("%-12s %-15s %-15s %-30s %-25s %-15s %-15s %-12s %-10s%n",
                        employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
                        employee.getAddress(), employee.getEmail(), employee.getPhoneNumber(),
                        employee.getDateOfBirth(), employee.getHireDate(),
                        employee.isManager() ? "Yes" : "No");
            }
        }
    }

    /**
     * Removes an employee.
     */
    public static void removeEmployee() {
        String employeeId = EmployeeInputAndValidationService.getEmployeeIdForUpdateOrRemove(scanner);

        Employee employeeToRemove = EmployeeDAO.getEmployeeById(employeeId);
        if (employeeToRemove != null) {
            EmployeeDAO.removeEmployee(employeeId);
            System.out.println("Employee with ID " + employeeId + " removed successfully.");
        } else {
            System.out.println("No employee found with the given ID: " + employeeId);
        }
    }

    /**
     * Adds a new employee.
     */
    public static void addEmployee() {
        String firstName = EmployeeInputAndValidationService.getFirstName(scanner);
        String lastName = EmployeeInputAndValidationService.getLastName(scanner);
        String address = EmployeeInputAndValidationService.getAddress(scanner);
        String email = EmployeeInputAndValidationService.getEmail(scanner);
        long phoneNumber = EmployeeInputAndValidationService.getPhoneNumber(scanner);
        Date dateOfBirth = EmployeeInputAndValidationService.getDateOfBirth(scanner);
        Date hireDate = EmployeeInputAndValidationService.getHireDate(scanner);
        boolean isManager = EmployeeInputAndValidationService.isManager(scanner);
        String password = EmployeeInputAndValidationService.getPassword(scanner);
        boolean isRemoved = EmployeeInputAndValidationService.isRemoved(scanner);

        Employee newEmployee = new Employee(null, firstName, lastName, address, email, phoneNumber, dateOfBirth, hireDate,
                isRemoved, isManager, PasswordHasher.hashPassword(password));

        EmployeeDAO.addEmployee(newEmployee);
    }

    /**
     * Updates employee information.
     */
    public static void updateEmployee(String id) {
        String employeeId = id;
        if (null == employeeId) {
            employeeId = EmployeeInputAndValidationService.getEmployeeIdForUpdateOrRemove(scanner);
        }

        String firstName = EmployeeInputAndValidationService.getFirstName(scanner);
        String lastName = EmployeeInputAndValidationService.getLastName(scanner);
        String address = EmployeeInputAndValidationService.getAddress(scanner);
        String email = EmployeeInputAndValidationService.getEmail(scanner);
        long phoneNumber = EmployeeInputAndValidationService.getPhoneNumber(scanner);
        Date dateOfBirth = EmployeeInputAndValidationService.getDateOfBirth(scanner);
        Date hireDate = EmployeeInputAndValidationService.getHireDate(scanner);
        boolean isManager = EmployeeInputAndValidationService.isManager(scanner);
        String password = EmployeeInputAndValidationService.getPassword(scanner);
        boolean isRemoved = EmployeeInputAndValidationService.isRemoved(scanner);

        Employee updatedEmployee = new Employee(employeeId, firstName, lastName, address, email, phoneNumber, dateOfBirth, hireDate,
                isRemoved, isManager, PasswordHasher.hashPassword(password));

        EmployeeDAO.editEmployee(updatedEmployee);
        System.out.println("Employee information updated successfully.");
    }
}
