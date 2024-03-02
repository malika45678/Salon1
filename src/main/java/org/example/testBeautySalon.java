package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class testBeautySalon {

	public static void main(String[] args) throws SQLException {
        BeautySalon beautySalon = new BeautySalon();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            UserDAO userDao = new UserDAO();
            BeautyProcedureDAO procedureDao = new BeautyProcedureDAO();
            BookingDAO bookingDao = new BookingDAO();
            System.out.println("Hello! You have the following available functions:");
            System.out.println("1) To show beauty procedures list;");
            System.out.println("2) To add a beauty procedure;");
            System.out.println("3) To add a new user;");
            System.out.println("4) To book for a beauty procedure;");
            System.out.println("5) To cancel booking for a beauty procedure;");
            System.out.println("6) To show booking history;");
            System.out.println("0) Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Listing all procedures...");
                    for (BeautyProcedure procedure : procedureDao.getAllProcedures()) {
                        System.out.println("Procedure: " + procedure.getNameOfProcedure() + ", Price: " + procedure.getPrice() + ", Description: " + procedure.getDescription());
                    }
                    break;

                case 2:
                    System.out.println("Enter procedure name: ");
                    scanner.nextLine(); // Consume the newline character
                    String procedureName = scanner.nextLine();
                    System.out.println("Enter procedure price: ");
                    Integer procedurePrice = (int) scanner.nextDouble();
                    System.out.println("Enter procedure description: ");
                    scanner.nextLine(); // Consume the newline character
                    String procedureDescription = scanner.nextLine();

                    System.out.println("Adding a new procedure...");
                    BeautyProcedure newProcedure = new BeautyProcedure(procedureName, procedurePrice, procedureDescription);
                    procedureDao.addProcedure(newProcedure);
                    break;

                case 3:
                    System.out.println("Enter user name: ");
                    scanner.nextLine(); // Consume the newline character
                    String userName = scanner.nextLine();
                    System.out.println("Enter initial balance: ");
                    int initialBalance = (int) scanner.nextDouble();

                    User newUser = new User();
                    newUser.setName(userName);
                    newUser.setBalance(initialBalance);
                    userDao.addUser(newUser);
                    break;

                case 4:
                    for (BeautyProcedure procedure : procedureDao.getAllProcedures()) {
                        System.out.println("Procedure: " + procedure.getNameOfProcedure() + ", Price: " + procedure.getPrice() + ", Description: " + procedure.getDescription());
                    }
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Enter procedure name: ");
                    String bookingProcedureName = scanner.nextLine();
                    System.out.println("Enter booking date (e.g., 22-12-2022): ");
                    String bookingDate = scanner.nextLine();
                    System.out.println("Enter booking time (e.g., 12:00): ");
                    String bookingTime = scanner.nextLine();

                    Booking newBooking = new Booking(bookingProcedureName, bookingDate, bookingTime);
                    bookingDao.addBooking(newBooking);

                    break;

                case 5:
                    List<Booking> bookings = bookingDao.getAllBookings();
                    bookings.forEach(booking -> System.out.println(booking.getProcedureName() + " at " + booking.getTime() + " " + booking.getDate()));
                    System.out.println("Enter user name: ");
                    scanner.nextLine(); // Consume the newline character
                    String cancelUserName = scanner.nextLine();
                    System.out.println("Enter booking ID to cancel: ");
                    int bookingIdToCancel = scanner.nextInt();

                    bookingDao.deleteBooking(bookingIdToCancel);
                    break;

                case 6:
                    List<Booking> getBookings = bookingDao.getAllBookings();
                    getBookings.forEach(getBooking -> System.out.println(getBooking.getProcedureName() + " at " + getBooking.getTime() + " " + getBooking.getDate()));
                    break;

                case 0:
                    System.out.println("Exiting the beauty salon system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

            System.out.println(); // Add a newline for better readability

        } while (choice != 0);

        // Close the scanner
        scanner.close();
    }

    private static User findUserByName(BeautySalon beautySalon, String userName) {
        for (User user : beautySalon.getUsers()) {
            if (user.getName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null;
    }

    private static BeautyProcedure findProcedureByName(BeautySalon beautySalon, String procedureName) {
        for (BeautyProcedure procedure : beautySalon.getProcedures()) {
            if (procedure.getNameOfProcedure().equalsIgnoreCase(procedureName)) {
                return procedure;
            }
        }
        return null;
    }

    private static Booking findBookingById(BeautySalon beautySalon, int bookingId) {
        for (Booking booking : beautySalon.getBookingHistory()) {
            if (booking.getId() == bookingId) {
                return booking;
            }
        }
        return null;
    }

}
