package services.dashboards;

import entities.Car;
import services.database.CarDAO;

import java.sql.Date;
import java.util.List;

/**
 * Dashboard service for fleet-related operations.
 * Author: Ghazala Anjum
 */
public class FleetDashboardService {

    /**
     * Displays cars due for an oil change within the next week.
     */
    public static void displayCarsDueForOilChange() {
        List<Car> carsDueForOilChange = CarDAO.getCarsDueForOilChange();
        if (carsDueForOilChange.isEmpty()) {
            System.out.println("No cars are due for an oil change in the next week.");
        } else {
            System.out.println("Cars Due for Oil Change:");
            displayCars(carsDueForOilChange);
        }
    }

    /**
     * Displays cars due for washing within the next week.
     */
    public static void displayCarsDueForWashing() {
        List<Car> carsDueForWashing = CarDAO.getCarsDueForWashing();
        if (carsDueForWashing.isEmpty()) {
            System.out.println("No cars are due for washing in the next week.");
        } else {
            System.out.println("Cars Due for Washing:");
            displayCars(carsDueForWashing);
        }
    }

    /**
     * Displays cars due for inspection within the next week.
     */
    public static void displayCarsDueForInspection() {
        List<Car> carsDueForInspection = CarDAO.getCarsDueForInspection();
        if (carsDueForInspection.isEmpty()) {
            System.out.println("No cars are due for inspection in the next week.");
        } else {
            System.out.println("Cars Due for Inspection:");
            displayCars(carsDueForInspection);
        }
    }

    /**
     * Displays cars that are currently out of service.
     */
    public static void displayOutOfServiceCars() {
        List<Car> outOfServiceCars = CarDAO.getOutOfServiceCars();
        if (outOfServiceCars.isEmpty()) {
            System.out.println("No cars are currently out of service.");
        } else {
            System.out.println("Out of Service Cars:");
            displayCars(outOfServiceCars);
        }
    }

    /**
     * Displays the details of cars in the list.
     */
    private static void displayCars(List<Car> cars) {
        System.out.printf("%-10s | %-15s | %-15s | %-15s | %-10s | %-15s | %-15s | %-15s%n",
                "Car ID", "Brand", "Model", "Color", "Status", "Last Oil Change", "Next Oil Change", "Last Inspection");
        for (Car car : cars) {
            System.out.printf("%-10s | %-15s | %-15s | %-15s | %-10s | %-15s | %-15s | %-15s%n",
                    car.getCarId(), car.getBrand(), car.getModel(), car.getColor(),
                    car.getCarStatus(), formatDate(car.getLastOilChange()),
                    formatDate(car.getNextOilChange()), formatDate(car.getLastInspectionDate()));
        }
    }

    /**
     * Formats the date to string representation.
     */
    private static String formatDate(Date date) {
        return (date != null) ? date.toString() : "N/A";
    }
}
