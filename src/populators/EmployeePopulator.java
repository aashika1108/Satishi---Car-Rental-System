package populators;

import entities.Employee;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static constants.CarRentalManagementSystemConstants.EMPLOYEE.*;

/**
 * Utility class to populate Employee objects from ResultSet data.
 * Author: Arshdeep Singh
 */
public class EmployeePopulator {

    /**
     * Populates an Employee object from ResultSet data.
     *
     * @param rs The ResultSet containing employee data.
     * @return An Employee object populated with data from the ResultSet.
     * @throws SQLException if there is an error accessing the ResultSet data.
     */
    public static Employee populateFromResultSet(ResultSet rs) throws SQLException {
        String employeeId = rs.getString(EMPLOYEE_ID_COLUMN);
        String firstName = rs.getString(FIRST_NAME_COLUMN);
        String lastName = rs.getString(LAST_NAME_COLUMN);
        String address = rs.getString(ADDRESS_COLUMN);
        String email = rs.getString(EMAIL_COLUMN);
        long phoneNumber = rs.getLong(PHONE_NUMBER_COLUMN);
        Date dateOfBirth = rs.getDate(DATE_OF_BIRTH_COLUMN);
        Date hireDate = rs.getDate(HIRE_DATE_COLUMN);
        boolean isRemoved = rs.getBoolean(IS_REMOVED_COLUMN);
        boolean isManager = rs.getBoolean(IS_MANAGER_COLUMN);
        String password = rs.getString(PASSWORD_COLUMN);

        return new Employee(employeeId, firstName, lastName, address, email, phoneNumber, dateOfBirth, hireDate, isRemoved, isManager, password);
    }
}
