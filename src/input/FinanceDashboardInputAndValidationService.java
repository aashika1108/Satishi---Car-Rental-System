package input;

import java.sql.Date;
import java.util.Scanner;

/**
 * Service class for input and validation related to finance dashboard in the Car Rental Management System.
 * Author: Aashika Vachhani
 */
public class FinanceDashboardInputAndValidationService {

    /**
     * Retrieves the end date for finance dashboard based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The end date as a Date object.
     */
    public static Date getEndDate(Scanner scanner) {
        boolean validInput = false;
        Date endDate = null;
        while (!validInput) {
            System.out.print("Enter end date (yyyy-mm-dd): ");
            try {
                endDate = Date.valueOf(scanner.nextLine());
                validInput = true; // If no exception, set validInput to true
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please enter date in yyyy-mm-dd format.");
            }
        }
        return endDate;
    }

    /**
     * Retrieves the start date for finance dashboard based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The start date as a Date object.
     */
    public static Date getStartDate(Scanner scanner) {
        boolean validInput = false;
        Date startDate = null;
        while (!validInput) {
            System.out.print("Enter start date (yyyy-mm-dd): ");
            try {
                startDate = Date.valueOf(scanner.nextLine());
                validInput = true; // If no exception, set validInput to true
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please enter date in yyyy-mm-dd format.");
            }
        }
        return startDate;
    }

}
