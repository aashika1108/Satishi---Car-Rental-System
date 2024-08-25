package entities;


import java.sql.Date;

/**
 * Represents a car entity in the Car Rental Management System.
 * Author: Aashika Vachhani
 */

public class Car {
    private String carId; // Unique identifier for the car
    private String brand; // Brand of the car
    private String model; // Model of the car
    private String color; // Color of the car
    private int noOfPassengers; // Number of passengers the car can accommodate
    private FuelType fuelType; // Type of fuel used by the car
    private Date purchaseDate; // Date when the car was purchased
    private TransmissionType transmissionType; // Transmission type of the car
    private boolean available; // Availability status of the car
    private double carPrice; // Price of the car
    private double rentalPrice; // Rental price of the car
    private boolean removed; // Removal status of the car
    private Date lastOilChange; // Date of the last oil change for the car
    private Date nextOilChange; // Date of the next scheduled oil change for the car
    private Date lastInspectionDate; // Date of the last inspection for the car
    private Date nextInspectionDate; // Date of the next scheduled inspection for the car
    private CarStatus carStatus; // Status of the car
    private Date lastWashingDate; // Date of the last washing for the car
    private Date nextWashingDate; // Date of the next scheduled washing for the car

    // Constructors, getters, and setters

    /**
     * Default constructor for the Car class.
     */

    public Car() {
        // Default constructor
    }

    /**
     * Parameterized constructor for the Car class.
     * @param carId The unique identifier for the car.
     * @param brand The brand of the car.
     * @param model The model of the car.
     * @param color The color of the car.
     * @param noOfPassengers The number of passengers the car can accommodate.
     * @param fuelType The type of fuel used by the car.
     * @param purchaseDate The date when the car was purchased.
     * @param transmissionType The transmission type of the car.
     * @param available The availability status of the car.
     * @param carPrice The price of the car.
     * @param rentalPrice The rental price of the car.
     * @param removed The removal status of the car.
     * @param lastOilChange The date of the last oil change for the car.
     * @param nextOilChange The date of the next scheduled oil change for the car.
     * @param lastInspectionDate The date of the last inspection for the car.
     * @param nextInspectionDate The date of the next scheduled inspection for the car.
     * @param carStatus The status of the car.
     * @param lastWashingDate The date of the last washing for the car.
     * @param nextWashingDate The date of the next scheduled washing for the car.
     */

    public Car(String carId, String brand, String model, String color, int noOfPassengers, FuelType fuelType,
               Date purchaseDate, TransmissionType transmissionType, boolean available, double carPrice, double rentalPrice,
               boolean removed, Date lastOilChange, Date nextOilChange, Date lastInspectionDate,
               Date nextInspectionDate, CarStatus carStatus, Date lastWashingDate, Date nextWashingDate) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.noOfPassengers = noOfPassengers;
        this.fuelType = fuelType;
        this.purchaseDate = purchaseDate;
        this.transmissionType = transmissionType;
        this.available = available;
        this.carPrice = carPrice;
        this.rentalPrice = rentalPrice;
        this.removed = removed;
        this.lastOilChange = lastOilChange;
        this.nextOilChange = nextOilChange;
        this.lastInspectionDate = lastInspectionDate;
        this.nextInspectionDate = nextInspectionDate;
        this.carStatus = carStatus;
        this.lastWashingDate = lastWashingDate;
        this.nextWashingDate = nextWashingDate;
    }

    // Getters and setters

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public Date getLastOilChange() {
        return lastOilChange;
    }

    public void setLastOilChange(Date lastOilChange) {
        this.lastOilChange = lastOilChange;
    }

    public Date getNextOilChange() {
        return nextOilChange;
    }

    public void setNextOilChange(Date nextOilChange) {
        this.nextOilChange = nextOilChange;
    }

    public Date getLastInspectionDate() {
        return lastInspectionDate;
    }

    public void setLastInspectionDate(Date lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
    }

    public Date getNextInspectionDate() {
        return nextInspectionDate;
    }

    public void setNextInspectionDate(Date nextInspectionDate) {
        this.nextInspectionDate = nextInspectionDate;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public Date getLastWashingDate() {
        return lastWashingDate;
    }

    public void setLastWashingDate(Date lastWashingDate) {
        this.lastWashingDate = lastWashingDate;
    }

    public Date getNextWashingDate() {
        return nextWashingDate;
    }

    public void setNextWashingDate(Date nextWashingDate) {
        this.nextWashingDate = nextWashingDate;
    }

    /**
     * Override toString method to provide a string representation of the Car object.
     * @return A string representation of the Car object.
     */
    @Override
    public String toString() {
        return "Car{" +
            "carId='" + carId + '\'' +
            ", brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", color='" + color + '\'' +
            ", noOfPassengers=" + noOfPassengers +
            ", fuelType=" + fuelType +
            ", purchaseDate=" + purchaseDate +
            ", transmissionType=" + transmissionType +
            ", available=" + available +
            ", carPrice=" + carPrice +
            ", rentalPrice=" + rentalPrice +
            ", removed=" + removed +
            ", lastOilChange=" + lastOilChange +
            ", nextOilChange=" + nextOilChange +
            ", lastInspectionDate=" + lastInspectionDate +
            ", nextInspectionDate=" + nextInspectionDate +
            ", carStatus=" + carStatus +
            ", lastWashingDate=" + lastWashingDate +
            ", nextWashingDate=" + nextWashingDate +
            '}';
    }
}
