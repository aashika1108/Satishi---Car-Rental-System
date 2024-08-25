package entities;

import services.calculation.InvoiceCalculationService;

import java.sql.Date;

/**
 * Represents a booking made in the Car Rental Management System.
 * Author: Amisha Nakrani
 */
public class Booking {
    private String bookingId; // Unique identifier for the booking
    private Car car; // Car booked
    private Customer customer; // Customer who made the booking
    private Employee employee; // Employee responsible for the booking
    private Date bookingDate; // Date when the booking was made
    private Date returnDate; // Date when the car is expected to be returned
    private Date cancellationDate; // Date when the booking was cancelled
    private double invoiceAmount; // Amount to be paid for the booking

    /**
     * Constructor for Booking class.
     * @param bookingId The unique identifier for the booking.
     * @param car The car booked for the booking.
     * @param customer The customer who made the booking.
     * @param employee The employee responsible for the booking.
     * @param bookingDate The date when the booking was made.
     * @param returnDate The expected return date of the car.
     * @param cancellationDate The date when the booking was cancelled (can be null if not cancelled).
     */
    public Booking(String bookingId, Car car, Customer customer, Employee employee, Date bookingDate, Date returnDate, Date cancellationDate) {
        this.bookingId = bookingId;
        this.car = car;
        this.customer = customer;
        this.employee = employee;
        this.bookingDate = bookingDate;
        this.returnDate = returnDate;
        this.cancellationDate = cancellationDate;
    }

    // Getters and Setters

    /**
     * Get the booking ID.
     * @return The booking ID.
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * Set the booking ID.
     * @param bookingId The booking ID to set.
     */
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Get the car associated with the booking.
     * @return The car associated with the booking.
     */
    public Car getCar() {
        return car;
    }

    /**
     * Set the car associated with the booking.
     * @param car The car to set.
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Get the customer who made the booking.
     * @return The customer who made the booking.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the customer who made the booking.
     * @param customer The customer to set.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Get the employee responsible for the booking.
     * @return The employee responsible for the booking.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set the employee responsible for the booking.
     * @param employee The employee to set.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Get the date when the booking was made.
     * @return The date when the booking was made.
     */
    public Date getBookingDate() {
        return bookingDate;
    }

    /**
     * Set the date when the booking was made.
     * @param bookingDate The date to set.
     */
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * Get the expected return date of the car.
     * @return The expected return date of the car.
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Set the expected return date of the car.
     * @param returnDate The return date to set.
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Get the date when the booking was cancelled.
     * @return The date when the booking was cancelled (can be null if not cancelled).
     */
    public Date getCancellationDate() {
        return cancellationDate;
    }

    /**
     * Set the date when the booking was cancelled.
     * @param cancellationDate The cancellation date to set.
     */
    public void setCancellationDate(Date cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    /**
     * Get the invoice amount for the booking.
     * Calculates the invoice amount using the InvoiceCalculationService.
     * @return The invoice amount for the booking.
     */
    public double getInvoiceAmount() {
        return InvoiceCalculationService.calculateInvoice(this);
    }

    /**
     * Override toString method to provide a string representation of the Booking object.
     * @return A string representation of the Booking object.
     */
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", car=" + car +
                ", customer=" + customer +
                ", employee=" + employee +
                ", bookingDate=" + bookingDate +
                ", returnDate=" + returnDate +
                ", cancellationDate=" + cancellationDate +
                '}';
    }
}
