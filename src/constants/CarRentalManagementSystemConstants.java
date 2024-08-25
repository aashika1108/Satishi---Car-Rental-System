package constants;

/**
 * Constants class for Car Rental Management System.
 * Contains static final inner classes for defining column names in database tables.
 * Also includes database connection properties.
 * Author: Arshdeep Singh
 */
public final class CarRentalManagementSystemConstants {

    /**
     * Inner class for defining constants related to the 'CAR' table.
     */
    public static final class CAR {
        // Column names for the 'CAR' table
        public static final String CAR_ID_COLUMN = "car_id";
        public static final String BRAND_COLUMN = "brand";
        public static final String MODEL_COLUMN = "model";
        public static final String COLOR_COLUMN = "color";
        public static final String NO_OF_PASSENGERS_COLUMN = "no_of_passengers";
        public static final String FUEL_TYPE_COLUMN = "fuel_type";
        public static final String PURCHASE_DATE_COLUMN = "purchase_date";
        public static final String TRANSMISSION_TYPE_COLUMN = "transmission_type";
        public static final String IS_AVAILABLE_COLUMN = "is_available";
        public static final String CAR_PRICE_COLUMN = "car_price";
        public static final String RENTAL_PRICE_COLUMN = "rental_price";
        public static final String IS_REMOVED_COLUMN = "is_removed";
        public static final String LAST_OIL_CHANGE_COLUMN = "last_oil_change";
        public static final String NEXT_OIL_CHANGE_COLUMN = "next_oil_change";
        public static final String LAST_INSPECTION_DATE_COLUMN = "last_inspection_date";
        public static final String NEXT_INSPECTION_DATE_COLUMN = "next_inspection_date";
        public static final String CAR_STATUS_COLUMN = "car_status";
        public static final String LAST_WASHING_DATE_COLUMN = "last_washing_date";
        public static final String NEXT_WASHING_DATE_COLUMN = "next_washing_date";
    }

    /**
     * Inner class for defining constants related to the 'BOOKING' table.
     */
    public static final class BOOKING {
        // Column names for the 'BOOKING' table
        public static final String BOOKING_ID_COLUMN = "booking_id";
        public static final String CAR_ID_COLUMN = "car_id";
        public static final String CUSTOMER_ID_COLUMN = "customer_id";
        public static final String EMPLOYEE_ID_COLUMN = "employee_id";
        public static final String BOOKING_DATE_COLUMN = "booking_date";
        public static final String RETURN_DATE_COLUMN = "return_date";
        public static final String CANCELLATION_DATE_COLUMN = "cancellation_date";
    }

    /**
     * Inner class for defining constants related to the 'CUSTOMER' table.
     */
    public static final class CUSTOMER {
        // Column names for the 'CUSTOMER' table
        public static final String CUSTOMER_ID_COLUMN = "customer_id";
        public static final String FIRST_NAME_COLUMN = "first_name";
        public static final String LAST_NAME_COLUMN = "last_name";
        public static final String ADDRESS_COLUMN = "address";
        public static final String EMAIL_COLUMN = "email";
        public static final String CONTACT_NO_COLUMN = "contact_no";
        public static final String IS_REMOVED_COLUMN = "is_removed";
        public static final String PASSWORD_COLUMN = "password";
    }

    /**
     * Inner class for defining constants related to the 'EMPLOYEE' table.
     */
    public static final class EMPLOYEE {
        // Column names for the 'EMPLOYEE' table
        public static final String EMPLOYEE_ID_COLUMN = "employee_id";
        public static final String FIRST_NAME_COLUMN = "first_name";
        public static final String LAST_NAME_COLUMN = "last_name";
        public static final String ADDRESS_COLUMN = "address";
        public static final String EMAIL_COLUMN = "email";
        public static final String PHONE_NUMBER_COLUMN = "phone_number";
        public static final String DATE_OF_BIRTH_COLUMN = "date_of_birth";
        public static final String HIRE_DATE_COLUMN = "hire_date";
        public static final String IS_REMOVED_COLUMN = "is_removed";
        public static final String IS_MANAGER_COLUMN = "is_manager";
        public static final String PASSWORD_COLUMN = "password";
    }

    /**
     * Inner class for defining constants related to the database connection.
     */
    public static final class DATABASE {
        // Database connection properties
        public static final String JDBC_BASE_URL = "jdbc:sqlserver://localhost:1433;Database=";
        public static final String DB_NAME = "db.name";
        public static final String DB_USERNAME = "db.username";
        public static final String DB_PASSWORD = "db.password";
        public static final String DATABASE_PROPERTIES = "src\\services\\database\\database.properties";
    }

}
