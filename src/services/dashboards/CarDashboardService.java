package services.dashboards;

import entities.Car;
import input.CarInputAndValidationService;
import resource.ScannerManager;
import services.database.CarDAO;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Dashboard service for managing cars.
 * Author: Aashika Vachhani
 */
public class CarDashboardService {

    // Scanner instance to manage user input
    static Scanner scanner = ScannerManager.getScanner();

    /**
     * Displays all cars.
     */
    public static void displayCars() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s  %-10s  %-10s  %-12s  %-11s  %-9s  %-14s  %-13s  %-10s  %-11s  %-8s  %-8s  %-14s  %-17s  %-20s  %-21s  %-13s  %-17s  %-18s%n",
                "ID", "Brand", "Model", "Color", "Passengers", "Fuel Type", "Purchase Date", "Transmission", "Available", "Car Price", "Rental", "Removed", "Last Oil Chg", "Next Oil Chg", "Last Inspection", "Next Inspection", "Status", "Last Washing", "Next Washing");

        for (Car car : CarDAO.getAllCars()) {
            System.out.printf("%-8s  %-10s  %-10s  %-12s  %-11d  %-9s  %-14s  %-13s  %-10b  %-11.2f  %-8.2f  %-8b  %-14s  %-17s  %-20s  %-21s  %-13s  %-17s  %-18s%n",
                    car.getCarId(), car.getBrand(), car.getModel(), car.getColor(), car.getNoOfPassengers(), car.getFuelType().name(),
                    car.getPurchaseDate() != null ? dateFormat.format(car.getPurchaseDate()) : "N/A", car.getTransmissionType().name(), car.isAvailable(), car.getCarPrice(),
                    car.getRentalPrice(), car.isRemoved(), car.getLastOilChange() != null ? dateFormat.format(car.getLastOilChange()) : "N/A",
                    car.getNextOilChange() != null ? dateFormat.format(car.getNextOilChange()) : "N/A",
                    car.getLastInspectionDate() != null ? dateFormat.format(car.getLastInspectionDate()) : "N/A",
                    car.getNextInspectionDate() != null ? dateFormat.format(car.getNextInspectionDate()) : "N/A",
                    car.getCarStatus().name(),
                    car.getLastWashingDate() != null ? dateFormat.format(car.getLastWashingDate()) : "N/A",
                    car.getNextWashingDate() != null ? dateFormat.format(car.getNextWashingDate()) : "N/A");
        }
    }

    /**
     * Searches for cars.
     */
    public static void searchCar() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var searchTerm = CarInputAndValidationService.getSearchTermForCar(scanner);

        List<Car> searchResults = CarDAO.searchCar(searchTerm);

        if (searchResults.isEmpty()) {
            System.out.println("No cars found matching the search term: " + searchTerm);
        } else {
            System.out.println("Search Results:");
            System.out.printf("%-10s %-15s %-15s %-15s %-10s %-10s %-15s %-15s %-10s %-10s %-10s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n",
                    "ID", "Brand", "Model", "Color", "Passengers", "Fuel Type", "Purchase Date",
                    "Transmission", "Available", "Car Price", "Rental", "Removed", "Last Oil Chg",
                    "Next Oil Chg", "Last Inspection", "Next Inspection", "Status", "Last Washing", "Next Washing");
            for (Car car : searchResults) {
                System.out.printf("%-10s %-15s %-15s %-15s %-10s %-10s %-15s %-15s %-10s %-10s %-10s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n",
                        car.getCarId(), car.getBrand(), car.getModel(), car.getColor(), car.getNoOfPassengers(), car.getFuelType().name(),
                        car.getPurchaseDate() != null ? dateFormat.format(car.getPurchaseDate()) : "N/A", car.getTransmissionType().name(), car.isAvailable(), car.getCarPrice(),
                        car.getRentalPrice(), car.isRemoved(), car.getLastOilChange() != null ? dateFormat.format(car.getLastOilChange()) : "N/A",
                        car.getNextOilChange() != null ? dateFormat.format(car.getNextOilChange()) : "N/A",
                        car.getLastInspectionDate() != null ? dateFormat.format(car.getLastInspectionDate()) : "N/A",
                        car.getNextInspectionDate() != null ? dateFormat.format(car.getNextInspectionDate()) : "N/A",
                        car.getCarStatus().name(),
                        car.getLastWashingDate() != null ? dateFormat.format(car.getLastWashingDate()) : "N/A",
                        car.getNextWashingDate() != null ? dateFormat.format(car.getNextWashingDate()) : "N/A");
            }
        }
    }

    /**
     * Removes a car.
     */
    public void removeCar() {
        String carId = CarInputAndValidationService.getCarIdForUpdate(scanner);

        Car carToRemove = CarDAO.getCarById(carId);
        if (carToRemove != null) {
            CarDAO.deleteCar(carId);
            System.out.println("Car with ID " + carId + " removed successfully.");
        } else {
            System.out.println("No car found with the given ID: " + carId);
        }
    }

    /**
     * Adds a new car.
     */
    public void addCar() {
        // Prompt for car details
        //var carId = CarInputAndValidationService.getCarIdForCreate(scanner);
        var brand = CarInputAndValidationService.getBrand(scanner);
        var model = CarInputAndValidationService.getModel(scanner);
        var color = CarInputAndValidationService.getColor(scanner);
        var noOfPassengers = CarInputAndValidationService.getNoOfPassengers(scanner);
        var fuelType = CarInputAndValidationService.getFuelType(scanner);
        var purchaseDate = CarInputAndValidationService.getPurchaseDate(scanner);
        var transmissionType = CarInputAndValidationService.getTransmissionType(scanner);
        var isAvailable = CarInputAndValidationService.isAvailable(scanner);
        var carPrice = CarInputAndValidationService.getCarPrice(scanner);
        var rentalPrice = CarInputAndValidationService.getRentalPrice(scanner);
        var isRemoved = CarInputAndValidationService.isRemoved(scanner);
        scanner.nextLine();
        var lastOilChange = CarInputAndValidationService.getLastOilChange(scanner);
        var nextOilChange = CarInputAndValidationService.getNextOilChange(scanner);
        var lastInspectionDate = CarInputAndValidationService.getLastInspectionDate(scanner);
        var nextInspectionDate = CarInputAndValidationService.getNextInspectionDate(scanner);
        var carStatus = CarInputAndValidationService.getCarStatus(scanner);
        scanner.nextLine();
        var lastWashingDate = CarInputAndValidationService.getLastWashingDate(scanner);
        var nextWashingDate = CarInputAndValidationService.getNextWashingDate(scanner);

        Car car = new Car(null, brand, model, color, noOfPassengers, fuelType, purchaseDate, transmissionType,
                isAvailable, carPrice, rentalPrice, isRemoved, lastOilChange, nextOilChange, lastInspectionDate,
                nextInspectionDate, carStatus, lastWashingDate, nextWashingDate);

        CarDAO.addNewCar(car);
    }

    /**
     * Updates a car.
     */
    public void updateCar() {
        var carId = CarInputAndValidationService.getCarIdForUpdate(scanner);
        var brand = CarInputAndValidationService.getBrand(scanner);
        var model = CarInputAndValidationService.getModel(scanner);
        var color = CarInputAndValidationService.getColor(scanner);
        var noOfPassengers = CarInputAndValidationService.getNoOfPassengers(scanner);
        var fuelType = CarInputAndValidationService.getFuelType(scanner);
        var purchaseDate = CarInputAndValidationService.getPurchaseDate(scanner);
        var transmissionType = CarInputAndValidationService.getTransmissionType(scanner);
        var isAvailable = CarInputAndValidationService.isAvailable(scanner);
        var carPrice = CarInputAndValidationService.getCarPrice(scanner);
        var rentalPrice = CarInputAndValidationService.getRentalPrice(scanner);
        var isRemoved = CarInputAndValidationService.isRemoved(scanner);
        scanner.nextLine();
        var lastOilChange = CarInputAndValidationService.getLastOilChange(scanner);
        var nextOilChange = CarInputAndValidationService.getNextOilChange(scanner);
        var lastInspectionDate = CarInputAndValidationService.getLastInspectionDate(scanner);
        var nextInspectionDate = CarInputAndValidationService.getNextInspectionDate(scanner);
        var carStatus = CarInputAndValidationService.getCarStatus(scanner);
        scanner.nextLine();
        var lastWashingDate = CarInputAndValidationService.getLastWashingDate(scanner);
        var nextWashingDate = CarInputAndValidationService.getNextWashingDate(scanner);

        Car car = new Car(carId, brand, model, color, noOfPassengers, fuelType, purchaseDate, transmissionType,
                isAvailable, carPrice, rentalPrice, isRemoved, lastOilChange, nextOilChange, lastInspectionDate,
                nextInspectionDate, carStatus, lastWashingDate, nextWashingDate);

        CarDAO.updateCar(car);
    }
}
