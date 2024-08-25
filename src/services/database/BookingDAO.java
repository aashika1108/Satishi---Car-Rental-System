package services.database;

import entities.Booking;
import populators.BookingPopulator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for managing booking-related operations in the database.
 * Author: Amisha Nakrani
 */

public class BookingDAO {

    /**
     * Retrieves all bookings from the database.
     *
     * @return A list of all bookings.
     */
    public static List<Booking> displayBookings() {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword())) {
            String query = "SELECT * FROM Booking";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Booking booking = BookingPopulator.populateBooking(resultSet);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    /**
     * Retrieves all bookings associated with a specific customer from the database.
     *
     * @param customerId The ID of the customer for whom bookings are retrieved.
     * @return A list of bookings associated with the specified customer.
     */
    public static List<Booking> displayBookingsByCustomerId(String customerId) {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword())) {
            String query = "SELECT * FROM Booking WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Booking booking = BookingPopulator.populateBooking(resultSet);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    /**
     * Adds a new booking to the database.
     *
     * @param booking The booking object to be added.
     */
    public static void addBooking(Booking booking) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword())) {
            String query = "INSERT INTO Booking (car_id, customer_id, employee_id, booking_date, return_date, cancellation_date) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, booking.getCar().getCarId());
            statement.setString(2, booking.getCustomer().getCustomerId());
            statement.setString(3, booking.getEmployee().getEmployeeId());
            statement.setDate(4, booking.getBookingDate());
            statement.setDate(5, booking.getReturnDate());
            statement.setDate(6, booking.getCancellationDate());

            statement.executeUpdate();
            System.out.println("Booking added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the return date of a booking in the database when the car is returned.
     *
     * @param booking    The booking for which the return date is to be updated.
     * @param returnDate The date the car was returned.
     */

    public static void returnCar(Booking booking, Date returnDate) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Booking SET return_date = ? WHERE booking_id = ?")) {

            statement.setDate(1, returnDate);
            statement.setString(2, booking.getBookingId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Car returned successfully.");
            } else {
                System.out.println("Failed to update return date.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cancels a booking by updating the cancellation date in the database.
     *
     * @param booking          The booking to be canceled.
     * @param cancellationDate The date when the booking was canceled.
     */
    public static void cancelBooking(Booking booking, Date cancellationDate) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Booking SET cancellation_date = ? WHERE booking_id = ?")) {

            statement.setDate(1, cancellationDate);
            statement.setString(2, booking.getBookingId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Booking canceled successfully.");
            } else {
                System.out.println("Failed to update cancellation date.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a booking by its unique identifier (booking ID) from the database.
     *
     * @param bookingId The unique identifier of the booking to retrieve.
     * @return The booking object if found, or null if not found.
     */

    public static Booking getBookingById(String bookingId) {
        Booking booking = null;
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Booking WHERE booking_id = ?")) {

            statement.setString(1, bookingId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                booking = BookingPopulator.populateBooking(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    /**
     * Retrieves a list of all booking IDs from the database.
     *
     * @return A list containing all booking IDs retrieved from the database.
     */
    public static List<String> getAllBookingIds() {
        List<String> bookingIds = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT booking_id FROM Booking");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String bookingId = resultSet.getString("booking_id");
                bookingIds.add(bookingId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingIds;
    }

    /**
     * Retrieves a list of bookings within a specified return date range from the database.
     *
     * @param startDate The start date of the return date range.
     * @param endDate   The end date of the return date range.
     * @return A list containing bookings that have return dates falling within the specified range.
     */
    public static List<Booking> getBookingsByReturnDateRange(Date startDate, Date endDate) {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword())) {
            String query = "SELECT * FROM Booking WHERE return_date BETWEEN ? AND ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Booking booking = BookingPopulator.populateBooking(resultSet);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    /**
     * Retrieves a list of bookings associated with a specific car ID from the database.
     * Only bookings with a return date are included in the result.
     *
     * @param carId The ID of the car for which bookings are to be retrieved.
     * @return A list of bookings associated with the specified car ID.
     */
    public static List<Booking> getBookingsByCarId(String carId) {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword())) {
            String query = "SELECT * FROM Booking WHERE car_id = ? AND return_date IS NOT NULL";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, carId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Booking booking = BookingPopulator.populateBooking(resultSet);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    /**
     * Retrieves a list of bookings associated with a specific customer ID from the database.
     * Only bookings with a return date are included in the result.
     *
     * @param customerId The ID of the customer for which bookings are to be retrieved.
     * @return A list of bookings associated with the specified customer ID.
     */

    public static List<Booking> getBookingsByCustomerId(String customerId) {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword())) {
            String query = "SELECT * FROM Booking WHERE customer_id = ? AND return_date IS NOT NULL";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Booking booking = BookingPopulator.populateBooking(resultSet);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

}
