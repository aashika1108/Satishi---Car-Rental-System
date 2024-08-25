package services.calculation;

import entities.Booking;

/**
 * Service class to calculate the invoice amount for a booking.
 * Author: Arshdeep Singh
 */
public class InvoiceCalculationService {

    /**
     * Calculates the invoice amount based on the booking details.
     *
     * @param booking The booking for which the invoice amount is to be calculated.
     * @return The calculated invoice amount.
     */
    public static double calculateInvoice(Booking booking) {
        // Calculate the number of days between booking and return dates
        long diffInMillies = Math.abs(booking.getReturnDate().getTime() - booking.getBookingDate().getTime());
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);

        // Get the rental price per day from the car object
        double rentalPricePerDay = booking.getCar().getRentalPrice();

        // Calculate the invoice amount
        double invoiceAmount = diffInDays * rentalPricePerDay;

        return invoiceAmount;
    }
}
