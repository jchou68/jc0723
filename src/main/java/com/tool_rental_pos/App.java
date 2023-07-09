package com.tool_rental_pos;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tool_rental_pos.other.classes.Holiday;
import com.tool_rental_pos.other.classes.Tool;
import com.tool_rental_pos.other.classes.ToolRentalPOS;


/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        // Date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        // Setup tools
        Tool tool1 = new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true);
        Tool tool2 = new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false);
        Tool tool3 = new Tool("JAKD",  "Jackhammer", "DeWalt", 2.99, true, false, false);
        Tool tool4 = new Tool("JAKR", "Jackhammer", "Rigid", 2.99, true, false, false);

        // Setup tool list
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(tool1);
        tools.add(tool2);
        tools.add(tool3);
        tools.add(tool4);

        // Setup holidays - Independence Day and Labor Day
        List<Holiday> holidays = new ArrayList<Holiday>();
        holidays.add(new Holiday("Independence Day", LocalDate.of(LocalDate.now().getYear(), 7, 4)));
        holidays.add(Holiday.createLaborDay());

        // Setup Tool Rental POS instance
        ToolRentalPOS toolRentalPOS = new ToolRentalPOS(tools, holidays);

        // Setup program
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        // Run program and only exit with user input
        do {
            try {
                displayMenu();
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character after reading the integer

                switch (choice) {
                    case 1:
                        toolRentalPOS.printTools();
                        break;
                    case 2:
                        System.out.print("Enter tool code: ");
                        String toolCodePrint = scanner.nextLine();
                        toolRentalPOS.printTool(toolCodePrint);
                        break;
                    case 3:
                        toolRentalPOS.printHolidays();
                        break;
                    case 4:
                        toolRentalPOS.printTools();
                        System.out.print("\nEnter tool code: ");
                        String toolCodeCheckout = scanner.nextLine();
                        System.out.print("Enter total rental days: ");
                        String dayCount = scanner.nextLine();
                        System.out.print("Enter checkout date (mm/dd/yyyy): ");
                        String checkoutDate = scanner.nextLine();
                        System.out.print("Enter discount (0-100): ");
                        String discount = scanner.nextLine();
                        toolRentalPOS.checkout(toolCodeCheckout, Integer.parseInt(dayCount), LocalDate.parse(checkoutDate, formatter), Integer.parseInt(discount));
                        break;
                    case 5:
                        System.out.println("Add Tool");
                        System.out.print("Enter tool code (unique): ");
                        String toolCodeAdd = scanner.nextLine();
                        System.out.print("Enter tool type: ");
                        String toolType = scanner.nextLine();
                        System.out.print("Enter tool brand: ");
                        String toolBrand = scanner.nextLine();
                        System.out.print("Enter daily charge (ex 9.99): ");
                        String dailyCharge = scanner.nextLine();
                        System.out.print("Weekday Charge (1 - yes, 0 - no): ");
                        int weekdayCharge = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character after reading the integer
                        System.out.print("Weekend Charge (1 - yes, 0 - no): ");
                        int weekendCharge = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character after reading the integer
                        System.out.print("Holiday Charge (1 - yes, 0 - no): ");
                        int holidayCharge = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character after reading the integer
                        toolRentalPOS.addTool(new Tool(toolCodeAdd, toolType, toolBrand, Double.parseDouble(dailyCharge), weekdayCharge == 1 ? true : false, weekendCharge == 1 ? true : false, holidayCharge == 1 ? true : false));
                        System.out.println("Tool added.\n");
                        toolRentalPOS.printTools();
                        break;
                    case 6:
                        System.out.println("Add Holiday");
                        System.out.print("Enter holiday name: ");
                        String holidayName = scanner.nextLine();
                        System.out.print("Enter holiday date (mm/dd/yyyy): ");
                        String holidayDate = scanner.nextLine();
                        toolRentalPOS.addHoliday(new Holiday(holidayName, LocalDate.parse(holidayDate, formatter)));
                        System.out.println("Holiday added.\n");
                        toolRentalPOS.printHolidays();
                        break;
                    case 7:
                        System.out.println("Exiting the program");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            
            System.out.println(); // Print a blank line for better readability
        } while (choice != 7);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n\nMenu:");
        System.out.println("1. Print tool report for all tools");
        System.out.println("2. Print tool report for specified tool");
        System.out.println("3. Print holiday report");
        System.out.println("4. Checkout tool");
        System.out.println("5. Add tool");
        System.out.println("6. Add holiday");
        System.out.println("7. Exit");
        System.out.print("\nEnter your choice: ");
    }
}
