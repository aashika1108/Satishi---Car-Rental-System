package services.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static constants.CarRentalManagementSystemConstants.DATABASE.*;

/**
 * Utility class to load database properties from a configuration file.
 * Author: Ghazala Anjum
 */
public class DatabasePropertiesLoader {

    /**
     * Retrieves the JDBC URL for connecting to the database.
     *
     * @return The JDBC URL string.
     */
    public static String getJdbcUrl() {
        String dbName = getDatabaseName();
        return JDBC_BASE_URL + dbName + ";encrypt=false;";
    }

    /**
     * Retrieves the name of the database from the properties file.
     *
     * @return The name of the database.
     */
    public static String getDatabaseName() {
        return getProperty(DB_NAME);
    }

    /**
     * Retrieves the username for connecting to the database from the properties file.
     *
     * @return The database username.
     */
    public static String getUsername() {
        return getProperty(DB_USERNAME);
    }

    /**
     * Retrieves the password for connecting to the database from the properties file.
     *
     * @return The database password.
     */
    public static String getPassword() {
        return getProperty(DB_PASSWORD);
    }

    /**
     * Retrieves the value of a specified property from the properties file.
     *
     * @param propertyName The name of the property to retrieve.
     * @return The value of the specified property.
     */
    private static String getProperty(String propertyName) {
        Properties properties = loadProperties(DATABASE_PROPERTIES);
        return properties.getProperty(propertyName);
    }

    /**
     * Loads the properties from the specified file.
     *
     * @param fileName The name of the properties file.
     * @return The Properties object containing the loaded properties.
     */
    private static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
