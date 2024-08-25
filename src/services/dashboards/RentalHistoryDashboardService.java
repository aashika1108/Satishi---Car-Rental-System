package services.dashboards;

import entities.Booking;
import entities.Car;
import entities.Customer;
import services.database.BookingDAO;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Dashboard service for rental history-related operations.
 * Author: Arshdeep Singh
 */
public class RentalHistoryDashboardService {

    /**
     * Displays the rental history of all bookings.
     */
    public static void displayRentalHistory() {
        List<Booking> bookings = BookingDAO.displayBookings();

        if (bookings.isEmpty()) {
            System.out.println("No rental history found.");
        } else {
            System.out.println("Rental History:");
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s%n",
                    "Booking ID", "Car ID", "Customer ID", "Employee ID", "Booking Date", "Return Date", "Cancellation Date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Booking booking : bookings) {
                String bookingId = booking.getBookingId();
                String carId = booking.getCar() != null ? booking.getCar().getCarId() : "N/A";
                String customerId = booking.getCustomer() != null ? booking.getCustomer().getCustomerId() : "N/A";
                String employeeId = booking.getEmployee() != null ? booking.getEmployee().getEmployeeId() : "N/A";
                String bookingDate = booking.getBookingDate() != null ? dateFormat.format(booking.getBookingDate()) : "N/A";
                String returnDate = booking.getReturnDate() != null ? dateFormat.format(booking.getReturnDate()) : "N/A";
                String cancellationDate = booking.getCancellationDate() != null ? dateFormat.format(booking.getCancellationDate()) : "N/A";

                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s%n",
                        bookingId, carId, customerId, employeeId, bookingDate, returnDate, cancellationDate);
            }
        }
    }

    /**
     * Displays the rental history of a specific customer.
     * @param customer The customer whose rental history is to be displayed.
     */
    public static void displayRentalHistoryByCustomerId(Customer customer) {
        String customerId = customer.getCustomerId();
        List<Booking> bookings = BookingDAO.displayBookingsByCustomerId(customerId);

        if (bookings.isEmpty()) {
            System.out.println("No rental history found for customer with ID: " + customerId);
        } else {
            System.out.println("Rental History for Customer ID " + customerId + ":");
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s%n",
                    "Booking ID", "Car ID", "Employee ID", "Booking Date", "Return Date", "Cancellation Date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Booking booking : bookings) {
                String bookingId = booking.getBookingId();
                String carId = booking.getCar() != null ? booking.getCar().getCarId() : "N/A";
                String employeeId = booking.getEmployee() != null ? booking.getEmployee().getEmployeeId() : "N/A";
                String bookingDate = booking.getBookingDate() != null ? dateFormat.format(booking.getBookingDate()) : "N/A";
                String returnDate = booking.getReturnDate() != null ? dateFormat.format(booking.getReturnDate()) : "N/A";
                String cancellationDate = booking.getCancellationDate() != null ? dateFormat.format(booking.getCancellationDate()) : "N/A";

                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s%n",
                        bookingId, carId, employeeId, bookingDate, returnDate, cancellationDate);
            }
        }
    }

    /**
     * Generates an invoice for a booking.
     */
    public static void generateInvoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        Booking booking = BookingDAO.getBookingById(bookingId);
        if (booking != null) {
            // Check if the booking is cancelled
            if (booking.getCancellationDate() != null) {
                System.out.println("Booking ID: " + bookingId + " is cancelled.");
            } else {
                // Check if both booking date and return date are populated
                if (booking.getBookingDate() != null && booking.getReturnDate() != null) {
                    Car car = booking.getCar();
                    Customer customer = booking.getCustomer();
                    System.out.println("Booking ID: " + booking.getBookingId());
                    System.out.println("Car ID: " + car.getCarId());
                    System.out.println("Car Brand: " + car.getBrand());
                    System.out.println("Car Model: " + car.getModel());
                    System.out.println("Car Color: " + car.getColor());
                    System.out.println("Customer ID: " + customer.getCustomerId());
                    System.out.println("Employee ID: " + booking.getEmployee().getEmployeeId());
                    System.out.println("Booking Date: " + booking.getBookingDate());
                    System.out.println("Return Date: " + booking.getReturnDate());
                    System.out.println("Invoice Amount: " + booking.getInvoiceAmount());
                } else {
                    System.out.println("Booking ID: " + bookingId + " is still active. Return date is not yet set.");
                }
            }
        } else {
            System.out.println("Booking not found.");
        }
    }
}
