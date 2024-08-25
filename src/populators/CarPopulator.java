package populators;

import entities.Car;
import entities.CarStatus;
import entities.FuelType;
import entities.TransmissionType;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static constants.CarRentalManagementSystemConstants.CAR.*;

/**
 * Utility class to populate Car objects from ResultSet data.
 * Author: Aashika Vachhani
 */
public class CarPopulator {

    /**
     * Populates a Car object from ResultSet data.
     *
     * @param rs The ResultSet containing car data.
     * @return A populated Car object.
     * @throws SQLException if there is an error accessing the ResultSet data.
     */
    public static Car populateFromResultSet(ResultSet rs) throws SQLException {
        String carId = rs.getString(CAR_ID_COLUMN);
        String brand = rs.getString(BRAND_COLUMN);
        String model = rs.getString(MODEL_COLUMN);
        String color = rs.getString(COLOR_COLUMN);
        int noOfPassengers = rs.getInt(NO_OF_PASSENGERS_COLUMN);
        FuelType fuelType = FuelType.valueOf(rs.getString(FUEL_TYPE_COLUMN));
        Date purchaseDate = rs.getDate(PURCHASE_DATE_COLUMN);
        TransmissionType transmissionType = TransmissionType.valueOf(rs.getString(TRANSMISSION_TYPE_COLUMN));
        boolean isAvailable = rs.getBoolean(IS_AVAILABLE_COLUMN);
        double carPrice = rs.getDouble(CAR_PRICE_COLUMN);
        double rentalPrice = rs.getDouble(RENTAL_PRICE_COLUMN);
        boolean isRemoved = rs.getBoolean(IS_REMOVED_COLUMN);
        Date lastOilChange = rs.getDate(LAST_OIL_CHANGE_COLUMN);
        Date nextOilChange = rs.getDate(NEXT_OIL_CHANGE_COLUMN);
        Date lastInspectionDate = rs.getDate(LAST_INSPECTION_DATE_COLUMN);
        Date nextInspectionDate = rs.getDate(NEXT_INSPECTION_DATE_COLUMN);
        CarStatus carStatus = CarStatus.valueOf(rs.getString(CAR_STATUS_COLUMN));
        Date lastWashingDate = rs.getDate(LAST_WASHING_DATE_COLUMN);
        Date nextWashingDate = rs.getDate(NEXT_WASHING_DATE_COLUMN);

        return new Car(carId, brand, model, color, noOfPassengers, fuelType, purchaseDate, transmissionType,
                isAvailable, carPrice, rentalPrice, isRemoved, lastOilChange, nextOilChange,
                lastInspectionDate, nextInspectionDate, carStatus, lastWashingDate, nextWashingDate);
    }
}
