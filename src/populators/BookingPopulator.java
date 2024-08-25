package populators;

import entities.Booking;
import entities.Car;
import entities.Customer;
import entities.Employee;
import services.database.CarDAO;
import services.database.CustomerDAO;
import services.database.EmployeeDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static constants.CarRentalManagementSystemConstants.BOOKING.*;

/**
 * Utility class to populate Booking objects from ResultSet data.
 * Author: Amisha Nakrani
 */
public class BookingPopulator {

    /**
     * Populates a Booking object from ResultSet data.
     *
     * @param rs The ResultSet containing booking data.
     * @return A populated Booking object.
     * @throws SQLException if there is an error accessing the ResultSet data.
     */
    public static Booking populateBooking(ResultSet rs) throws SQLException {
        String bookingId = rs.getString(BOOKING_ID_COLUMN);
        String carId = rs.getString(CAR_ID_COLUMN);
        String customerId = rs.getString(CUSTOMER_ID_COLUMN);
        String employeeId = rs.getString(EMPLOYEE_ID_COLUMN);
        Date bookingDate = rs.getDate(BOOKING_DATE_COLUMN);
        Date returnDate = rs.getDate(RETURN_DATE_COLUMN);
        Date cancellationDate = rs.getDate(CANCELLATION_DATE_COLUMN);

        Car car = CarDAO.getCarById(carId);
        Customer customer = CustomerDAO.getCustomerById(customerId);
        Employee employee = EmployeeDAO.getEmployeeById(employeeId);

        return new Booking(bookingId, car, customer, employee, bookingDate, returnDate, cancellationDate);
    }
}
