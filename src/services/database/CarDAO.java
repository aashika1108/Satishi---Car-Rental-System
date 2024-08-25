package services.database;


import entities.Car;
import populators.CarPopulator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for managing car-related operations in the database.
 * Author: Aashika Vachhani
 */
public class CarDAO {

    /**
     * Retrieves a list of all cars from the database.
     *
     * @return A list of all cars in the database.
     */
    public static List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Car");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Car car = CarPopulator.populateFromResultSet(resultSet);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    /**
     * Retrieves a car from the database based on the provided car ID.
     *
     * @param carId The ID of the car to retrieve.
     * @return The car with the specified ID, or null if not found.
     */
    public static Car getCarById(String carId) {
        Car car = null;

        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Car WHERE car_id = ?");
        ) {
            statement.setString(1, carId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    car = CarPopulator.populateFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

    /**
     * Searches for cars in the database based on a search term.
     *
     * @param searchTerm The term to search for in car IDs, brands, or models.
     * @return A list of cars matching the search term.
     */
    public static List<Car> searchCar(String searchTerm) {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Car WHERE car_id LIKE ? OR brand LIKE ? OR model LIKE ?";
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + searchTerm + "%");
            statement.setString(2, "%" + searchTerm + "%");
            statement.setString(3, "%" + searchTerm + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Car car = CarPopulator.populateFromResultSet(resultSet);
                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    /**
     * Updates details of a car in the database.
     *
     * @param car The car object containing the updated details.
     */
    public static void updateCar(Car car) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Car SET brand = ?, model = ?, color = ?, no_of_passengers = ?, " +
                             "fuel_type = ?, purchase_date = ?, transmission_type = ?, is_available = ?, " +
                             "car_price = ?, rental_price = ?, is_removed = ?, last_oil_change = ?, " +
                             "next_oil_change = ?, last_inspection_date = ?, next_inspection_date = ?, " +
                             "car_status = ?, last_washing_date = ?, next_washing_date = ? WHERE car_id = ?")) {

            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getColor());
            statement.setInt(4, car.getNoOfPassengers());
            statement.setString(5, car.getFuelType().toString());
            statement.setDate(6, car.getPurchaseDate());
            statement.setString(7, car.getTransmissionType().toString());
            statement.setBoolean(8, car.isAvailable());
            statement.setDouble(9, car.getCarPrice());
            statement.setDouble(10, car.getRentalPrice());
            statement.setBoolean(11, car.isRemoved());

            // Handle last oil change date
            if (car.getLastOilChange() != null) {
                statement.setDate(12, car.getLastOilChange());
            } else {
                statement.setNull(12, Types.DATE);
            }

            // Handle next oil change date
            if (car.getNextOilChange() != null) {
                statement.setDate(13, car.getNextOilChange());
            } else {
                statement.setNull(13, Types.DATE);
            }

            // Handle last inspection date
            if (car.getLastInspectionDate() != null) {
                statement.setDate(14, car.getLastInspectionDate());
            } else {
                statement.setNull(14, Types.DATE);
            }

            // Handle next inspection date
            if (car.getNextInspectionDate() != null) {
                statement.setDate(15, car.getNextInspectionDate());
            } else {
                statement.setNull(15, Types.DATE);
            }

            statement.setString(16, car.getCarStatus().toString());

            // Handle last washing date
            if (car.getLastWashingDate() != null) {
                statement.setDate(17, car.getLastWashingDate());
            } else {
                statement.setNull(17, Types.DATE);
            }

            // Handle next washing date
            if (car.getNextWashingDate() != null) {
                statement.setDate(18, car.getNextWashingDate());
            } else {
                statement.setNull(18, Types.DATE);
            }

            statement.setString(19, car.getCarId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Deletes a car from the database based on the provided car ID.
     *
     * @param carId The ID of the car to delete.
     */
    public static void deleteCar(String carId) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("UPDATE Car SET is_removed = ? WHERE car_id = ?")) {

            statement.setBoolean(1, true);
            statement.setString(2, carId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new car to the database.
     *
     * @param car The car object representing the new car to add.
     */

    public static void addNewCar(Car car) {
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Car (brand, model, color, no_of_passengers, fuel_type, purchase_date, " +
                             "transmission_type, is_available, car_price, rental_price, is_removed, last_oil_change, " +
                             "next_oil_change, last_inspection_date, next_inspection_date, car_status, last_washing_date, " +
                             "next_washing_date) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getColor());
            statement.setInt(4, car.getNoOfPassengers());
            statement.setString(5, car.getFuelType().name());
            statement.setDate(6, car.getPurchaseDate());
            statement.setString(7, car.getTransmissionType().name());
            statement.setBoolean(8, car.isAvailable());
            statement.setDouble(9, car.getCarPrice());
            statement.setDouble(10, car.getRentalPrice());
            statement.setBoolean(11, car.isRemoved());

            // Handle last oil change date
            if (car.getLastOilChange() != null) {
                statement.setDate(12, car.getLastOilChange());
            } else {
                statement.setNull(12, Types.DATE);
            }

            // Handle next oil change date
            if (car.getNextOilChange() != null) {
                statement.setDate(13, car.getNextOilChange());
            } else {
                statement.setNull(13, Types.DATE);
            }

            // Handle last inspection date
            if (car.getLastInspectionDate() != null) {
                statement.setDate(14, car.getLastInspectionDate());
            } else {
                statement.setNull(14, Types.DATE);
            }

            // Handle next inspection date
            if (car.getNextInspectionDate() != null) {
                statement.setDate(15, car.getNextInspectionDate());
            } else {
                statement.setNull(15, Types.DATE);
            }

            statement.setString(16, car.getCarStatus().name());

            // Handle last washing date
            if (car.getLastWashingDate() != null) {
                statement.setDate(17, car.getLastWashingDate());
            } else {
                statement.setNull(17, Types.DATE);
            }

            // Handle next washing date
            if (car.getNextWashingDate() != null) {
                statement.setDate(18, car.getNextWashingDate());
            } else {
                statement.setNull(18, Types.DATE);
            }

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Car added successfully.");
            } else {
                System.out.println("Failed to add car.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of all car IDs from the database.
     *
     * @return A list of all car IDs in the database.
     */
    public static List<String> getAllCarIds() {
        List<String> carIds = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT car_id FROM Car")) {

            while (resultSet.next()) {
                carIds.add(resultSet.getString("car_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carIds;
    }

    /**
     * Retrieves a list of cars due for an oil change within the next 7 days.
     *
     * @return A list of cars due for an oil change.
     */
    public static List<Car> getCarsDueForOilChange() {
        List<Car> carsDueForOilChange = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Car WHERE next_oil_change <= DATEADD(DAY, 7, GETDATE()) AND is_removed = 0")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = CarPopulator.populateFromResultSet(resultSet); // Assuming there's a method to populate Car objects
                carsDueForOilChange.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carsDueForOilChange;
    }

    /**
     * Retrieves a list of cars due for a washing within the next 7 days.
     *
     * @return A list of cars due for a washing.
     */

    public static List<Car> getCarsDueForWashing() {
        List<Car> carsDueForWashing = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Car WHERE next_washing_date <= DATEADD(DAY, 7, GETDATE()) AND is_removed = 0")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = CarPopulator.populateFromResultSet(resultSet); // Assuming there's a method to populate Car objects
                carsDueForWashing.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carsDueForWashing;
    }

    /**
     * Retrieves a list of cars due for an inspection within the next 7 days.
     *
     * @return A list of cars due for an inspection.
     */
    public static List<Car> getCarsDueForInspection() {
        List<Car> carsDueForInspection = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Car WHERE next_inspection_date <= DATEADD(DAY, 7, GETDATE()) AND is_removed = 0")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = CarPopulator.populateFromResultSet(resultSet); // Assuming there's a method to populate Car objects
                carsDueForInspection.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carsDueForInspection;
    }

    /**
     * Retrieves a list of cars that are out of service.
     *
     * @return A list of cars that are out of service.
     */
    public static List<Car> getOutOfServiceCars() {
        List<Car> outOfServiceCars = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabasePropertiesLoader.getJdbcUrl(), DatabasePropertiesLoader.getUsername(), DatabasePropertiesLoader.getPassword());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Car WHERE car_status = 'OUTOFSERVICE' AND is_removed = 0")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = CarPopulator.populateFromResultSet(resultSet); // Assuming there's a method to populate Car objects
                outOfServiceCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outOfServiceCars;
    }


}
