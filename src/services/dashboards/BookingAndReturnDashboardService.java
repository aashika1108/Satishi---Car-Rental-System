package services.dashboards;

import entities.Booking;
import entities.Car;
import input.BookingAndReturnInputAndValidationService;
import resource.ScannerManager;
import services.database.BookingDAO;
import services.database.CarDAO;

import java.sql.Date;
import java.util.Scanner;

/**
 * Dashboard service for managing bookings and returns.
 * Author: Amisha Nakrani
 */
public class BookingAndReturnDashboardService {
    // Scanner instance to manage user input
    static Scanner scanner = ScannerManager.getScanner();

    /**
     * Adds a new booking.
     *
     * @param cid The customer ID associated with the booking.
     */
    public static void addBooking(String cid) {
        boolean isValidInput = false;
        while (!isValidInput) {
            // Prompt for car details
            var car = BookingAndReturnInputAndValidationService.getCarForBooking(scanner);

            // Prompt for customer details
            var customer = BookingAndReturnInputAndValidationService.getCustomer(cid, scanner);

            // Prompt for employee details
            var employee = BookingAndReturnInputAndValidationService.getEmployee(scanner);

            // Prompt for booking date
            var bookingDate = BookingAndReturnInputAndValidationService.getBookingDate(scanner);

            // Prompt for return date
            var returnDate = BookingAndReturnInputAndValidationService.getReturnDate(scanner);

            // Prompt for cancellation date
            var cancellationDate = BookingAndReturnInputAndValidationService.getCancellationDate(scanner);

            // Check if return date or cancellation date is before the booking date
            if ((returnDate != null && returnDate.before(bookingDate)) || (cancellationDate != null && cancellationDate.before(bookingDate))) {
                System.out.println("Return date or cancellation date cannot be before the booking date. Please try again.");
            } else {
                // Create the Booking object
                Booking booking = new Booking(null, car, customer, employee, bookingDate, returnDate, cancellationDate);

                // Add the booking
                BookingDAO.addBooking(booking);

                // Set the car's availability to false
                car.setAvailable(false);

                // Update car availability
                CarDAO.updateCar(car);

                isValidInput = true; // Break the loop if input is valid
            }
        }
    }

    /**
     * Returns a booked car.
     */
    public static void returnCar() {
        // Prompt for booking ID
        var bookingId = BookingAndReturnInputAndValidationService.getBookingIdForReturnOrCancel(scanner);

        // Fetch booking details
        Booking booking = BookingDAO.getBookingById(bookingId);

        // Prompt for return date
        var returnDate = BookingAndReturnInputAndValidationService.getReturnOrCancelDate(scanner, booking);

        // Set the return date
        booking.setReturnDate(returnDate);

        // Update the booking with the return date
        BookingDAO.returnCar(booking, returnDate);

        // Update the car's availability
        Car car = booking.getCar();
        car.setAvailable(true);
        CarDAO.updateCar(car);

        System.out.println("Car returned successfully.");
    }

    /**
     * Cancels a booking.
     */
    public static void cancelBooking() {
        // Prompt for booking ID
        var bookingId = BookingAndReturnInputAndValidationService.getBookingIdForReturnOrCancel(scanner);

        // Fetch booking details
        Booking booking = BookingDAO.getBookingById(bookingId);

        // Prompt for cancellation date
        Date cancellationDate = BookingAndReturnInputAndValidationService.getReturnOrCancelDate(scanner, booking);

        // Set the cancellation date
        booking.setCancellationDate(cancellationDate);

        // Update the booking with the cancellation date
        BookingDAO.cancelBooking(booking, cancellationDate);

        // Update car availability
        Car car = booking.getCar();
        car.setAvailable(true);
        CarDAO.updateCar(car);

        System.out.println("Booking canceled successfully.");
    }
}
