package services.menu;

import input.LoginInputAndValidationService;
import resource.ScannerManager;
import services.login.LoginService;

import java.util.Scanner;

/**
 * Manages the login menu for users.
 * Author: Ghazala Anjum
 */
public class LoginMenu {

    /**
     * Displays the login menu and prompts the user to select Customer or Employee.
     */
    public static void login() {
        Scanner loginScanner = ScannerManager.getScanner();

        System.out.println("Welcome to the Login Menu");
        System.out.println("Are you a Customer or an Employee?");
        System.out.println("1. Customer");
        System.out.println("2. Employee");

        int choice = LoginInputAndValidationService.getChoice(loginScanner);
        if (choice == 1) {
            System.out.println("You selected Customer.");
            LoginService.loginAsCustomer(loginScanner);
        } else if (choice == 2) {
            System.out.println("You selected Employee.");
            LoginService.loginAsEmployee(loginScanner);
        }
    }
}
