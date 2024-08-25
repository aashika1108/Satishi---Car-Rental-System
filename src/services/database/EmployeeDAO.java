package services.database;

import entities.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import populators.EmployeePopulator;

/**
 * Data access object for managing employee-related operations in the database.
 * Author: Arshdeep Singh
 */
public class EmployeeDAO {

    /**
     * Adds a new employee to the database.
     *
     * @param employee The employee object to be added.
     */
    public static void addEmployee(Employee employee) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Employee (first_name, last_name, address, email, phone_number, date_of_birth, hire_date, is_removed, is_manager, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getEmail());
            statement.setLong(5, employee.getPhoneNumber());
            statement.setDate(6, employee.getDateOfBirth());
            statement.setDate(7, employee.getHireDate());
            statement.setBoolean(8, employee.isRemoved());
            statement.setBoolean(9, employee.isManager());
            statement.setString(10, employee.getPassword());
            statement.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes an employee from the database based on the provided employee ID.
     *
     * @param employeeId The ID of the employee to be removed.
     */
    public static void removeEmployee(String employeeId) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("UPDATE Employee SET is_removed = ? WHERE employee_id = ?")) {
            statement.setBoolean(1, true);
            statement.setString(2, employeeId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee removed successfully.");
            } else {
                System.out.println("No employee found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edits the information of an existing employee in the database.
     *
     * @param employee The updated employee object.
     */
    public static void editEmployee(Employee employee) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("UPDATE Employee SET address = ?, email = ?, phone_number = ?, date_of_birth = ?, hire_date = ?, is_removed = ?, is_manager = ?, password = ?, first_name = ?, last_name = ? WHERE employee_id = ?")) {
            statement.setString(1, employee.getAddress());
            statement.setString(2, employee.getEmail());
            statement.setLong(3, employee.getPhoneNumber());
            statement.setDate(4, employee.getDateOfBirth());
            statement.setDate(5, employee.getHireDate());
            statement.setBoolean(6, employee.isRemoved()); // Updated to use the provided isRemoved parameter
            statement.setBoolean(7, employee.isManager());
            statement.setString(8, employee.getPassword());
            statement.setString(9, employee.getFirstName());
            statement.setString(10, employee.getLastName());
            statement.setString(11, employee.getEmployeeId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee information updated successfully.");
            } else {
                System.out.println("No employee found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an employee from the database based on the provided employee ID.
     *
     * @param employeeId The ID of the employee to retrieve.
     * @return The employee object retrieved from the database.
     */
    public static Employee getEmployeeById(String employeeId) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employee WHERE employee_id = ?")) {
            statement.setString(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return EmployeePopulator.populateFromResultSet(resultSet);
                } else {
                    System.out.println("No employee found with the given ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Searches for employees in the database based on a keyword.
     *
     * @param keyword The keyword to search for in employee IDs, emails, and phone numbers.
     * @return A list of employees matching the search criteria.
     */

    public static List<Employee> searchEmployee(String keyword) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employee WHERE employee_id LIKE ? OR email LIKE ? OR phone_number LIKE ?")) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    employees.add(EmployeePopulator.populateFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    /**
     * Retrieves all employees from the database.
     *
     * @return A list of all employees stored in the database.
     */
    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee")) {
            while (resultSet.next()) {
                employees.add(EmployeePopulator.populateFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    /**
     * Retrieves all employee IDs from the database.
     *
     * @return A list of all employee IDs stored in the database.
     */
    public static List<String> getAllEmployeeIds() {
        List<String> employeeIds = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT employee_id FROM Employee")) {
            while (resultSet.next()) {
                employeeIds.add(resultSet.getString("employee_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeIds;
    }

}

