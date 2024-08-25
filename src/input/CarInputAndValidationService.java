package input;

import entities.CarStatus;
import entities.FuelType;
import entities.TransmissionType;
import services.database.CarDAO;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Service class for input and validation related to cars in the Car Rental Management System.
 * This class provides methods for retrieving and validating various attributes of cars, such as status, dates, and other details.
 * Author: Aashika Vachhani
 */
public class CarInputAndValidationService {
    /**
     * Retrieves the next washing date for a car from the user input.
     * Allows the user to input the next washing date in the format yyyy-MM-dd.
     * If left blank or entered as "null", returns null.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The next washing date as a Date object.
     */
    public static Date getNextWashingDate(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        Date nextWashingDate = null; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter next washing date (yyyy-MM-dd). Can be left blank as well: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty() || input.equalsIgnoreCase("null")) {
                // If the input is empty or "null", set nextWashingDate to null and exit loop
                nextWashingDate = null;
                validInput = true;
            } else {
                try {
                    Date date = Date.valueOf(input); // Attempt to parse the input as a Date
                    Date currentDate = new Date(System.currentTimeMillis()); // Get the current date

                    if (date.after(currentDate)) {
                        // If the input date is in the future, set nextWashingDate and exit loop
                        nextWashingDate = date;
                        validInput = true;
                    } else {
                        System.out.println("Invalid date. Please enter a date in the future.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                }
            }
        }
        return nextWashingDate;
    }


    /**
     * Retrieves the last washing date for a car from the user input.
     * Allows the user to input the last washing date in the format yyyy-MM-dd.
     * If left blank or entered as "null", returns null.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The last washing date as a Date object.
     */
    public static Date getLastWashingDate(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        Date lastWashingDate = null; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter last washing date (yyyy-MM-dd). Can be left blank as well: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty() || input.equalsIgnoreCase("null")) {
                // If the input is empty or "null", set lastWashingDate to null and exit loop
                lastWashingDate = null;
                validInput = true;
            } else {
                try {
                    Date date = Date.valueOf(input); // Attempt to parse the input as a Date
                    Date currentDate = new Date(System.currentTimeMillis()); // Get the current date

                    if (date.before(currentDate)) {
                        // If the input date is in the past, set lastWashingDate and exit loop
                        lastWashingDate = date;
                        validInput = true;
                    } else {
                        System.out.println("Invalid date. Please enter a date in the past.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                }
            }
        }
        return lastWashingDate;
    }


    /**
     * Retrieves the status of a car from the user input.
     * Allows the user to input the car status as either "FUNCTIONAL" or "OUTOFSERVICE".
     * Case-insensitive input is accepted.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The status of the car as a CarStatus enum value.
     */
    public static CarStatus getCarStatus(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        CarStatus carStatus = null; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter car status (FUNCTIONAL, OUTOFSERVICE): ");
            String input = scanner.next().toUpperCase(); // Convert input to uppercase for case-insensitive comparison

            try {
                carStatus = CarStatus.valueOf(input); // Attempt to parse the input as a CarStatus enum value
                validInput = true; // If successful, set the flag to true to exit the loop
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter either FUNCTIONAL or OUTOFSERVICE.");
            }
        }
        return carStatus;
    }


    /**
     * Retrieves the next inspection date from the user input.
     * Allows the user to input the next inspection date in the format "yyyy-MM-dd".
     *
     * @param scanner The Scanner object to read user input from.
     * @return The next inspection date as a Date object.
     */
    public static Date getNextInspectionDate(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        Date nextInspectionDate = null; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter next inspection date (yyyy-MM-dd). Can be left blank as well: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty() || input.equalsIgnoreCase("null")) {
                // If the input is empty or "null", set nextInspectionDate to null and exit loop
                nextInspectionDate = null;
                validInput = true;
            } else {
                try {
                    Date date = Date.valueOf(input); // Attempt to parse the input as a Date
                    Date currentDate = new Date(System.currentTimeMillis()); // Get the current date

                    if (date.after(currentDate)) {
                        // If the input date is in the future, set nextInspectionDate and exit loop
                        nextInspectionDate = date;
                        validInput = true;
                    } else {
                        System.out.println("Invalid date. Please enter a date in the future.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                }
            }
        }
        return nextInspectionDate;
    }


    /**
     * Retrieves the last inspection date from the user input.
     * Allows the user to input the last inspection date in the format "yyyy-MM-dd".
     *
     * @param scanner The Scanner object to read user input from.
     * @return The last inspection date as a Date object.
     */
    public static Date getLastInspectionDate(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        Date lastInspectionDate = null; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter last inspection date (yyyy-MM-dd). Can be left blank as well: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty() || input.equalsIgnoreCase("null")) {
                // If the input is empty or "null", set lastInspectionDate to null and exit loop
                lastInspectionDate = null;
                validInput = true;
            } else {
                try {
                    Date date = Date.valueOf(input); // Attempt to parse the input as a Date
                    Date currentDate = new Date(System.currentTimeMillis()); // Get the current date

                    if (date.before(currentDate)) {
                        // If the input date is in the past, set lastInspectionDate and exit loop
                        lastInspectionDate = date;
                        validInput = true;
                    } else {
                        System.out.println("Invalid date. Please enter a date in the past.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                }
            }
        }
        return lastInspectionDate;
    }


    /**
     * Retrieves the next oil change date from the user input.
     * Allows the user to input the next oil change date in the format "yyyy-MM-dd".
     *
     * @param scanner The Scanner object to read user input from.
     * @return The next oil change date as a Date object.
     */
    public static Date getNextOilChange(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        Date nextOilChange = null; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter next oil change date (yyyy-MM-dd). Can be left blank as well: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty() || input.equalsIgnoreCase("null")) {
                // If the input is empty or "null", set nextOilChange to null and exit loop
                nextOilChange = null;
                validInput = true;
            } else {
                try {
                    Date date = Date.valueOf(input); // Attempt to parse the input as a Date
                    Date currentDate = new Date(System.currentTimeMillis()); // Get the current date

                    if (date.after(currentDate)) {
                        // If the input date is in the future, set nextOilChange and exit loop
                        nextOilChange = date;
                        validInput = true;
                    } else {
                        System.out.println("Invalid date. Please enter a date in the future.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                }
            }
        }
        return nextOilChange;
    }


    /**
     * Retrieves the last oil change date from the user input.
     * Allows the user to input the last oil change date in the format "yyyy-MM-dd".
     *
     * @param scanner The Scanner object to read user input from.
     * @return The last oil change date as a Date object.
     */
    public static Date getLastOilChange(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        Date lastOilChange = null; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter last oil change date (yyyy-MM-dd). Can be left blank as well: ");
            String input = scanner.nextLine().trim(); // Use nextLine() and trim() to handle blank input

            if (input.isEmpty() || input.equalsIgnoreCase("null")) {
                // If the input is empty or "null", set lastOilChange to null and exit loop
                lastOilChange = null;
                validInput = true;
            } else {
                try {
                    Date date = Date.valueOf(input); // Attempt to parse the input as a Date
                    Date currentDate = new Date(System.currentTimeMillis()); // Get the current date

                    if (date.before(currentDate)) {
                        // If the input date is in the past, set lastOilChange and exit loop
                        lastOilChange = date;
                        validInput = true;
                    } else {
                        System.out.println("Invalid date. Please enter a date in the past.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                }
            }
        }
        return lastOilChange;
    }


    /**
     * Retrieves the removal status of a car from user input.
     * Allows the user to input whether the car is removed (true) or not (false).
     *
     * @param scanner The Scanner object to read user input from.
     * @return True if the car is removed, false otherwise.
     */
    public static boolean isRemoved(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        boolean isRemoved = false; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Is the car removed? (true/false): ");
            if (scanner.hasNextBoolean()) {
                isRemoved = scanner.nextBoolean();
                validInput = true; // Set flag to true and exit the loop
            } else {
                System.out.println("Invalid input. Please enter either 'true' or 'false'.");
                scanner.next(); // Consume invalid input to avoid infinite loop
            }
        }
        return isRemoved;
    }


    /**
     * Retrieves the rental price of a car from user input.
     * Allows the user to input the rental price, ensuring it is a positive numeric value.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The rental price of the car.
     */
    public static double getRentalPrice(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        double rentalPrice = 0.0; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter rental price: ");
            if (scanner.hasNextDouble()) {
                rentalPrice = scanner.nextDouble();
                if (rentalPrice > 0) {
                    validInput = true; // Set flag to true and exit the loop
                } else {
                    System.out.println("Invalid input. Please enter a positive value.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); // Consume invalid input to avoid infinite loop
            }
        }
        return rentalPrice;
    }


    /**
     * Retrieves the price of a car from user input.
     * Allows the user to input the car price, ensuring it is a positive numeric value.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The price of the car.
     */
    public static double getCarPrice(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        double carPrice = 0.0; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter car price: ");
            if (scanner.hasNextDouble()) {
                carPrice = scanner.nextDouble();
                if (carPrice > 0) {
                    validInput = true; // Set flag to true and exit the loop
                } else {
                    System.out.println("Invalid input. Please enter a positive value.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); // Consume invalid input to avoid infinite loop
            }
        }
        return carPrice;
    }


    /**
     * Checks the availability status of a car based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return True if the car is available, false otherwise.
     */
    public static boolean isAvailable(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        boolean isAvailable = false; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Is the car available? (true/false): ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("true")) {
                isAvailable = true;
                validInput = true; // Set flag to true and exit the loop
            } else if (input.equalsIgnoreCase("false")) {
                isAvailable = false;
                validInput = true; // Set flag to true and exit the loop
            } else {
                System.out.println("Invalid input. Please enter either 'true' or 'false'.");
            }
        }
        return isAvailable;
    }


    /**
     * Retrieves the transmission type of a car based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The TransmissionType enum value representing the transmission type.
     */
    public static TransmissionType getTransmissionType(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        TransmissionType transmissionType = null; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter transmission type (Manual, Automatic): ");
            String inputType = scanner.next().toUpperCase();

            try {
                transmissionType = TransmissionType.valueOf(inputType);

                // If the input is successfully parsed, set validInput to true
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid transmission type. Please enter either Manual or Automatic.");
            }
        }
        return transmissionType;
    }


    /**
     * Retrieves the purchase date of a car based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The purchase date as a Date object.
     */
    public static Date getPurchaseDate(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        Date purchaseDate = null; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter purchase date (yyyy-MM-dd): ");
            String inputDate = scanner.next();

            try {
                // Parsing the input date
                purchaseDate = Date.valueOf(inputDate);

                // Get the current date
                Date currentDate = new Date(System.currentTimeMillis());

                // Check if the input date is not in the future and is a valid date
                if (!purchaseDate.after(currentDate)) {
                    validInput = true; // Set the flag to true if input is valid
                } else {
                    System.out.println("Please enter a date that is not in the future.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
        return purchaseDate;
    }


    /**
     * Retrieves the fuel type of a car based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The fuel type as a FuelType enum value.
     */
    public static FuelType getFuelType(Scanner scanner) {
        boolean validInput; // Flag to track if input is valid
        FuelType fuelType = null; // Default value, will be overwritten
        validInput = false;

        while (!validInput) {
            System.out.print("Enter fuel type (Petrol, Diesel): ");
            String input = scanner.nextLine().toUpperCase();

            try {
                fuelType = FuelType.valueOf(input);
                validInput = true; // Set the flag to true if input is valid
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter either 'Petrol' or 'Diesel'.");
            }
        }
        return fuelType;
    }


    /**
     * Retrieves the number of passengers for a car based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The number of passengers as an integer value.
     */
    public static int getNoOfPassengers(Scanner scanner) {
        boolean validInput = false; // Flag to track if input is valid
        int noOfPassengers = 0; // Default value, will be overwritten

        while (!validInput) {
            System.out.print("Enter number of passengers: ");
            String input = scanner.nextLine();

            try {
                noOfPassengers = Integer.parseInt(input);
                if (noOfPassengers <= 0) {
                    System.out.println("The number of passengers cannot be negative or zero.");
                    continue;
                }
                validInput = true; // Set flag to true if parsing succeeds
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return noOfPassengers;
    }


    /**
     * Retrieves the color of a car based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The color of the car as a String.
     */
    public static String getColor(Scanner scanner) {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        return color;
    }

    /**
     * Retrieves the model of a car based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The model of the car as a String.
     */
    public static String getModel(Scanner scanner) {
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        return model;
    }

    /**
     * Retrieves the brand of a car based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The brand of the car as a String.
     */
    public static String getBrand(Scanner scanner) {
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        return brand;
    }

    /**
     * Retrieves the search term for a car (car_id, brand, or model) based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The search term as a String.
     */
    public static String getSearchTermForCar(Scanner scanner) {
        System.out.print("Enter search term (car_id, brand, or model): ");
        String searchTerm = scanner.nextLine();
        return searchTerm;
    }

    /**
     * Retrieves the car ID for updating a car based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The car ID as a String.
     */
    public static String getCarIdForUpdate(Scanner scanner) {
        List<String> allCarIds = CarDAO.getAllCarIds();
        boolean validInput = false;
        String carId = "";

        while (!validInput) {
            System.out.print("Enter car ID: ");
            carId = scanner.nextLine();
            if (allCarIds.contains(carId)) {
                validInput = true;
            } else {
                System.out.println("The entered car ID does not exist. Please enter a valid car ID.");
            }
        }
        return carId;
    }

    /**
     * Retrieves the car ID for creating a new car based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The car ID as a String.
     */
    public static String getCarIdForCreate(Scanner scanner) {
        boolean validInput = false;
        String carId = "";

        while (!validInput) {
            System.out.print("Enter car ID: ");
            carId = scanner.nextLine();
            if (CarDAO.getAllCarIds().contains(carId)) {
                System.out.println("This car ID is not unique. Please enter a unique car ID.");
            } else {
                validInput = true;
            }
        }

        return carId;
    }


}
