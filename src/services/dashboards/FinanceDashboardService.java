package services.dashboards;

import entities.Booking;
import input.CarInputAndValidationService;
import input.CustomerInputAndValidationService;
import input.FinanceDashboardInputAndValidationService;
import resource.ScannerManager;
import services.database.BookingDAO;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Dashboard service for finance-related operations.
 * Author: Aashika Vachhani
 */
public class FinanceDashboardService {
    // Scanner instance to manage user input
    static Scanner scanner = ScannerManager.getScanner();

    /**
     * Displays finance report for a specified date range.
     */
    public static void displayFinanceReportForDateRange() {
        Date startDate;
        Date endDate;
        scanner.nextLine();

        // Prompt the user to enter dates until a valid range is provided
        do {
            startDate = FinanceDashboardInputAndValidationService.getStartDate(scanner);
            endDate = FinanceDashboardInputAndValidationService.getEndDate(scanner);

            // Ensure that startDate is less than endDate
            if (startDate.compareTo(endDate) > 0) {
                System.out.println("Start date cannot be greater than end date. Please enter valid dates.");
            }
        } while (startDate.compareTo(endDate) > 0);

        // Call the method from BookingDAO to get bookings within the date range
        List<Booking> bookingsInRange = BookingDAO.getBookingsByReturnDateRange(startDate, endDate);

        // Display the retrieved bookings and perform calculations
        displayBookings(bookingsInRange);
    }

    /**
     * Displays finance report for a specified car.
     */
    public static void displayFinanceReportForCar() {

        scanner.nextLine();
        String carId = CarInputAndValidationService.getCarIdForUpdate(scanner);

        // Call the method from BookingDAO to get bookings for the specified car
        List<Booking> bookingsForCar = BookingDAO.getBookingsByCarId(carId);

        // Display the retrieved bookings and perform calculations
        displayBookings(bookingsForCar);
    }

    /**
     * Displays finance report for a specified customer.
     */
    public static void displayFinanceReportForCustomer() {

        scanner.nextLine();
        String customerId = CustomerInputAndValidationService.getCustomerIdForUpdate(scanner);

        // Call the method from BookingDAO to get bookings for the specified customer
        List<Booking> bookingsForCustomer = BookingDAO.getBookingsByCustomerId(customerId);

        // Display the retrieved bookings and perform calculations
        displayBookings(bookingsForCustomer);
    }

    /**
     * Displays booking details and calculates total invoice amount.
     */
    private static void displayBookings(List<Booking> bookings) {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        double totalInvoiceAmount = 0.0;

        for (Booking booking : bookings) {
            double invoiceAmount = booking.getInvoiceAmount();
            totalInvoiceAmount += invoiceAmount;

            System.out.println(formatBookingDetails(booking, invoiceAmount));
            System.out.println("-----------------------------");
        }

        System.out.println("Total Invoice Amount: $" + totalInvoiceAmount);
    }

    /**
     * Formats booking details into a string.
     */
    private static String formatBookingDetails(Booking booking, double invoiceAmount) {
        StringBuilder sb = new StringBuilder();
        sb.append("Booking ID: ").append(booking.getBookingId()).append("\n");
        sb.append("Car: ").append(booking.getCar().getModel()).append("\n");
        sb.append("Customer: ").append(booking.getCustomer().getCustomerId()).append("\n");
        sb.append("Booking Date: ").append(booking.getBookingDate()).append("\n");
        sb.append("Return Date: ").append(booking.getReturnDate()).append("\n");
        sb.append("Invoice Amount: $").append(invoiceAmount).append("\n");
        return sb.toString();
    }
}
