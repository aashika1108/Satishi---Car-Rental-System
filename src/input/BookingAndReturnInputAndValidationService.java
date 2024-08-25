package input;

import entities.Booking;
import entities.Car;
import entities.Customer;
import entities.Employee;
import services.database.BookingDAO;
import services.database.CarDAO;
import services.database.CustomerDAO;
import services.database.EmployeeDAO;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Service class for input and validation related to booking and returning cars in the Car Rental Management System.
 * Author: Amisha Nakrani
 */
public class BookingAndReturnInputAndValidationService {
    /**
     * Retrieves the cancellation date from user input.
     *
     * @param scanner The Scanner object for user input.
     * @return The cancellation date, or null if not canceled.
     */
    public static Date getCancellationDate(Scanner scanner) {
        Date cancellationDate = null; // Initialize cancellation date
        boolean isValidInput = false; // Flag to track input validity

        // Continue until valid input is received
        while (!isValidInput) {
            System.out.print("Enter cancellation date (yyyy-MM-dd) or leave blank if not canceled: ");
            String inputDate = scanner.nextLine(); // Get user input as a string

            // Check if input is empty
            if (inputDate.trim().isEmpty()) {
                return null; // Return null if input is empty
            }

            try {
                // Parse the input date
                cancellationDate = Date.valueOf(inputDate);

                // Get the current date
                Date currentDate = new Date(System.currentTimeMillis());

                // Check if the input date is not in the future and is a valid date
                if (cancellationDate.after(currentDate)) {
                    System.out.println("Please enter a date that is not in the future.");
                } else {
                    isValidInput = true; // Set flag to true if input is valid
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
        return cancellationDate; // Return the cancellation date
    }

    /**
     * Retrieves the return date from user input.
     *
     * @param scanner The Scanner object for user input.
     * @return The return date, or null if not returned yet.
     */
    public static Date getReturnDate(Scanner scanner) {
        Date returnDate = null; // Initialize return date
        boolean isValidInput = false; // Flag to track input validity

        // Continue until valid input is received
        while (!isValidInput) {
            System.out.print("Enter return date (yyyy-MM-dd) or leave blank if not returned yet: ");
            String inputDate = scanner.nextLine().trim(); // Get user input as a string

            // Check if input is empty
            if (inputDate.trim().isEmpty()) {
                return null; // Return null if input is empty
            }

            try {
                // Parse the input date
                returnDate = Date.valueOf(inputDate);

                // Get the current date
                Date currentDate = new Date(System.currentTimeMillis());

                // Check if the input date is not in the future and is a valid date
                if (returnDate.after(currentDate)) {
                    System.out.println("Please enter a date that is not in the future.");
                } else {
                    isValidInput = true; // Set flag to true if input is valid
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
        return returnDate; // Return the return date
    }

    /**
     * Retrieves the booking date from user input.
     *
     * @param scanner The Scanner object for user input.
     * @return The booking date.
     */
    public static Date getBookingDate(Scanner scanner) {
        boolean validInput = false; // Flag to track input validity
        Date bookingDate = null; // Initialize booking date

        // Continue until valid input is received
        while (!validInput) {
            System.out.print("Enter booking date (yyyy-MM-dd): ");
            String inputDate = scanner.nextLine().trim(); // Get user input as a string

            try {
                // Parse the input date
                bookingDate = Date.valueOf(inputDate);

                // Get the current date
                Date currentDate = new Date(System.currentTimeMillis());

                // Check if the input date is  in the future and is a valid date
                if (!bookingDate.before(currentDate)) {
                    validInput = true; // Set the flag to true if input is valid
                } else {
                    System.out.println("Please enter a date that is  in the future.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
        return bookingDate; // Return the booking date
    }

    /**
     * Retrieves an employee from user input by their ID.
     *
     * @param scanner The Scanner object for user input.
     * @return The Employee object.
     */
    public static Employee getEmployee(Scanner scanner) {
        String employeeId; // Variable to store employee ID
        boolean isValidEmployee = false; // Flag to track input validity
        Employee employee = null; // Initialize employee object

        // Continue until a valid employee is retrieved
        while (!isValidEmployee) {
            System.out.print("Enter employee ID: ");
            employeeId = scanner.nextLine(); // Get user input for employee ID

            // Retrieve employee information from the database
            employee = EmployeeDAO.getEmployeeById(employeeId);

            // Check if the employee exists and is not removed
            if (employee != null && !employee.isRemoved()) {
                isValidEmployee = true; // Set flag to true to exit the loop
            } else {
                System.out.println("Invalid employee ID or the employee is removed. Please try again.");
            }
        }

        return employee; // Return the retrieved employee
    }


    /**
     * Retrieves a customer from user input by their ID.
     *
     * @param cid     The customer ID.
     * @param scanner The Scanner object for user input.
     * @return The Customer object.
     */
    public static Customer getCustomer(String cid, Scanner scanner) {
        String customerId = cid; // Variable to store customer ID
        boolean isValidCustomer = false; // Flag to track input validity
        Customer customer = null; // Initialize customer object

        // Continue until a valid customer is retrieved
        while (!isValidCustomer) {
            if (null == customerId) {
                System.out.print("Enter customer ID: ");
                customerId = scanner.nextLine(); // Get user input for customer ID
            }

            // Retrieve customer information from the database
            customer = CustomerDAO.getCustomerById(customerId);

            // Check if the customer exists and is not removed
            if (customer != null && !customer.isRemoved()) {
                isValidCustomer = true; // Set flag to true to exit the loop
                return customer; // Return the retrieved customer
            } else {
                System.out.println("Invalid customer ID or the customer is removed. Please try again.");
                // Reset customerId to null to prompt for input again
                customerId = null;
            }
        }

        return customer; // Return the retrieved customer
    }


    /**
     * Retrieves a car available for booking based on user input of the car ID.
     *
     * @param scanner The Scanner object for user input.
     * @return The Car object available for booking.
     */
    public static Car getCarForBooking(Scanner scanner) {
        Car car = null; // Initialize car object
        boolean isValidCar = false; // Flag to track input validity

        // Continue until a valid car is retrieved
        while (!isValidCar) {
            System.out.print("Enter car ID: ");
            String carId = scanner.nextLine(); // Get user input for car ID

            // Retrieve car information from the database
            car = CarDAO.getCarById(carId);

            // Check if the car exists and is available for booking
            if (car != null && car.isAvailable() && !car.isRemoved()) {
                isValidCar = true; // Set flag to true to exit the loop
            } else {
                System.out.println("Invalid car ID or the car is not available for booking. Please try again.");
            }
        }

        return car; // Return the retrieved car available for booking
    }

    /**
     * Retrieves a unique booking ID based on user input.
     *
     * @param scanner The Scanner object for user input.
     * @return A unique booking ID.
     */
    public static String getBookingId(Scanner scanner) {
        String bookingId = ""; // Initialize booking ID
        boolean isValidId = false; // Flag to track input validity

        // Retrieve existing booking IDs from the database
        List<String> existingBookingIds = BookingDAO.getAllBookingIds();

        // Continue until a unique booking ID is obtained
        while (!isValidId) {
            System.out.print("Enter booking ID: ");
            bookingId = scanner.nextLine(); // Get user input for booking ID

            // Check if the entered booking ID is unique
            if (!existingBookingIds.contains(bookingId)) {
                isValidId = true; // Set flag to true to exit the loop
            } else {
                System.out.println("Booking ID already exists. Please enter a different one.");
            }
        }

        return bookingId; // Return the unique booking ID
    }

    /**
     * Parses a date string into a Date object.
     *
     * @param dateString The string representation of the date.
     * @return The parsed Date object, or null if the input is empty or in an invalid format.
     */
    private static Date parseDate(String dateString) {
        // Check if the input date string is empty
        if (dateString.isEmpty()) {
            return null; // Return null if the input is empty
        }

        try {
            // Attempt to parse the input date string
            return Date.valueOf(dateString);
        } catch (IllegalArgumentException e) {
            // Catch IllegalArgumentException if the input date format is invalid
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            return null; // Return null if the input date format is invalid
        }
    }

    /**
     * Prompts the user to enter a booking ID for returning or canceling a booking.
     *
     * @param scanner The Scanner object used for user input.
     * @return The entered booking ID.
     */
    public static String getBookingIdForReturnOrCancel(Scanner scanner) {
        // Retrieve all existing booking IDs
        List<String> allBookingIds = BookingDAO.getAllBookingIds();
        String bookingId = "";
        boolean isValidId = false;

        // Keep prompting the user until a valid booking ID is entered
        while (!isValidId) {
            System.out.print("Enter booking ID of the car: ");
            bookingId = scanner.nextLine();

            // Check if the entered booking ID exists in the list of all booking IDs
            if (allBookingIds.contains(bookingId)) {
                isValidId = true; // Set isValidId to true to exit the loop
            } else {
                // Print an error message if the entered booking ID is invalid
                System.out.println("Invalid booking ID. Please enter a valid booking ID.");
            }
        }

        return bookingId; // Return the valid booking ID
    }

    /**
     * Prompts the user to enter a return or cancel date for a booking.
     *
     * @param scanner The Scanner object used for user input.
     * @param booking The Booking object for which the return or cancel date is being entered.
     * @return The entered return or cancel date.
     */
    public static Date getReturnOrCancelDate(Scanner scanner, Booking booking) {
        // Retrieve the booking date from the booking object
        Date bookingDate = booking.getBookingDate();
        Date returnOrCancelDate = null;
        boolean isValidDate = false;

        // Keep prompting the user until a valid return or cancel date is entered
        while (!isValidDate) {
            System.out.print("Enter return/cancel date (yyyy-MM-dd): ");
            String input = scanner.nextLine();

            try {
                // Attempt to parse the entered date string
                returnOrCancelDate = Date.valueOf(input);
                Date currentDate = new Date(System.currentTimeMillis());

                // Check if the return date is not in the future and after the booking date
                if (!returnOrCancelDate.before(currentDate) && !returnOrCancelDate.before(bookingDate)) {
                    isValidDate = true; // Set isValidDate to true to exit the loop
                } else {
                    // Print an error message if the entered date is invalid
                    System.out.println("Invalid date. Please enter a date that is in the future and after the booking date.");
                }
            } catch (IllegalArgumentException e) {
                // Print an error message if the date format is invalid
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }

        return returnOrCancelDate; // Return the valid return or cancel date
    }


}
