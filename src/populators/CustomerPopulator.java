package populators;

import entities.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

import static constants.CarRentalManagementSystemConstants.CUSTOMER.*;

/**
 * Utility class to populate Customer objects from ResultSet data.
 * Author: Amisha Nakrani
 */
public class CustomerPopulator {

    /**
     * Populates a Customer object from ResultSet data.
     *
     * @param rs The ResultSet containing customer data.
     * @return A populated Customer object.
     * @throws SQLException if there is an error accessing the ResultSet data.
     */
    public static Customer populateFromResultSet(ResultSet rs) throws SQLException {
        String customerId = rs.getString(CUSTOMER_ID_COLUMN);
        String firstName = rs.getString(FIRST_NAME_COLUMN);
        String lastName = rs.getString(LAST_NAME_COLUMN);
        String address = rs.getString(ADDRESS_COLUMN);
        String email = rs.getString(EMAIL_COLUMN);
        long contactNo = rs.getLong(CONTACT_NO_COLUMN);
        boolean isRemoved = rs.getBoolean(IS_REMOVED_COLUMN);
        String password = rs.getString(PASSWORD_COLUMN);

        return new Customer(customerId, firstName, lastName, address, email, contactNo, isRemoved, password);
    }
}
