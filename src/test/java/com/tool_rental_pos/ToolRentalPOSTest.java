package com.tool_rental_pos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tool_rental_pos.other.classes.Holiday;
import com.tool_rental_pos.other.classes.RentalAgreement;
import com.tool_rental_pos.other.classes.Tool;
import com.tool_rental_pos.other.classes.ToolRentalPOS;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToolRentalPOSTest {
    // Members
    private ToolRentalPOS toolRentalPOS;
    private Tool tool1, tool2, tool3, tool4;
    private List<Tool> tools;
    private List<Holiday> holidays;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

    @BeforeEach
    public void setUp() {
        // Setup tools
        this.tool1 = new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true);
        this.tool2 = new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false);
        this.tool3 = new Tool("JAKD",  "Jackhammer", "DeWalt", 2.99, true, false, false);
        this.tool4 = new Tool("JAKR", "Jackhammer", "Rigid", 2.99, true, false, false);

        // Setup tool list
        this.tools = new ArrayList<Tool>();
        this.tools.add(tool1);
        this.tools.add(tool2);
        this.tools.add(tool3);
        this.tools.add(tool4);

        // Setup holidays - Independence Day and Labor Day
        this.holidays = new ArrayList<Holiday>();
        this.holidays.add(new Holiday("Independence Day", LocalDate.of(LocalDate.now().getYear(), 7, 4)));
        this.holidays.add(Holiday.createLaborDay());

        // Setup Tool Rental POS instance
        this.toolRentalPOS = new ToolRentalPOS(tools, holidays);
    }

    @Test
    public void testCheckout1() {
        // Checkout Values
        String toolCode = "JAKR";
        int dayCount = 5;
        LocalDate checkoutDate = LocalDate.of(2015,9,3);
        double discount = 101;
        
        System.out.println("Test Checkout 1\n");
        System.out.println("Test Checkout Parameters");
        System.out.println("Tool Code: "+toolCode);
        System.out.printf("Day Count: %d", dayCount).println();
        System.out.println("Checkout Date: "+checkoutDate.format(formatter));
        System.out.printf("Discount: %.0f\n", discount).println();

        // Start Test
        Exception exception = assertThrows(Exception.class, () -> {
            RentalAgreement test1 = toolRentalPOS.checkout(toolCode, dayCount, checkoutDate, discount);
            test1.print();
        });

        String expectedErrorMessage = "Discount percent is not in the range 0-100.";
        String actualErrorMessage = exception.getMessage();
        assertEquals(expectedErrorMessage, actualErrorMessage);
        System.out.println("Test Checkout 1 pass.");
    }
    
    @Test
    public void testCheckout2() {
        // Checkout Values
        String toolCode = "LADW";
        int dayCount = 3;
        LocalDate checkoutDate = LocalDate.of(2020,7,2);
        double discount = 10;

        System.out.println("Test Checkout 2\n");
        System.out.println("Test Checkout Parameters");
        System.out.println("tool code: "+toolCode);
        System.out.printf("day count: %d", dayCount).println();
        System.out.println("checkout date: "+checkoutDate.format(formatter));
        System.out.printf("discount: %.0f\n", discount).println();

        // Start Test
        try {
            RentalAgreement test = toolRentalPOS.checkout(toolCode, dayCount, checkoutDate, discount);
    
            // Print Tool 
            toolRentalPOS.printTool(toolCode);
            // Print rental agreement
            test.print();

            // Get Tool Information
            Optional<Tool> optTool = toolRentalPOS.findTool(toolCode);
            if(!optTool.isPresent()) {
                throw new Exception("Tool not found.");
            }

            Tool tool = optTool.get();
            
            // Test tool code
            assertEquals(toolCode, test.getToolCode());
            // Test tool type
            assertEquals(tool.getToolType(), test.getToolType());
            // Test tool brand
            assertEquals(tool.getBrand(), test.getToolBrand());
            // Test rental days
            assertEquals(dayCount, test.getRentalDays());
            // Test checkout date
            assertEquals(checkoutDate, test.getCheckoutDate());
            // Test due date
            assertEquals(checkoutDate.plusDays(dayCount), test.getDueDate());
            // Test daily rental charge
            assertEquals(tool.getDailyCharge(), test.getDailyRentalCharge());
            // Test charge days
            assertEquals(2, test.getChargeDays());
            // Test pre-discount charge
            assertEquals(3.98, test.getPreDiscountCharge());
            // Test discount percent
            assertEquals(discount, test.getDiscountPercent());
            // Test discount amount
            assertEquals(0.40, test.getDiscountAmount());
            // Test final charge
            assertEquals(3.58, test.getFinalCharge());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Test Checkout 2 pass.");
    }

    @Test
    public void testCheckout3() {
        // Checkout Values
        String toolCode = "CHNS";
        int dayCount = 5;
        LocalDate checkoutDate = LocalDate.of(2015,7,2);
        double discount = 25;

        System.out.println("Test Checkout 3\n");
        System.out.println("Test Checkout Parameters");
        System.out.println("tool code: "+toolCode);
        System.out.printf("day count: %d", dayCount).println();
        System.out.println("checkout date: "+checkoutDate.format(formatter));
        System.out.printf("discount: %.0f\n", discount).println();

        // Start Test
        try {
            RentalAgreement test = toolRentalPOS.checkout(toolCode, dayCount, checkoutDate, discount);
    
            // Print Tool 
            toolRentalPOS.printTool(toolCode);
            // Print rental agreement
            test.print();

            // Get Tool Information
            Optional<Tool> optTool = toolRentalPOS.findTool(toolCode);
            if(!optTool.isPresent()) {
                throw new Exception("Tool not found.");
            }

            Tool tool = optTool.get();
            
            // Test tool code
            assertEquals(toolCode, test.getToolCode());
            // Test tool type
            assertEquals(tool.getToolType(), test.getToolType());
            // Test tool brand
            assertEquals(tool.getBrand(), test.getToolBrand());
            // Test rental days
            assertEquals(dayCount, test.getRentalDays());
            // Test checkout date
            assertEquals(checkoutDate, test.getCheckoutDate());
            // Test due date
            assertEquals(checkoutDate.plusDays(dayCount), test.getDueDate());
            // Test daily rental charge
            assertEquals(tool.getDailyCharge(), test.getDailyRentalCharge());
            // Test charge days
            assertEquals(3, test.getChargeDays());
            // Test pre-discount charge
            assertEquals(4.47, test.getPreDiscountCharge());
            // Test discount percent
            assertEquals(discount, test.getDiscountPercent());
            // Test discount amount
            assertEquals(1.12, test.getDiscountAmount());
            // Test final charge
            assertEquals(3.35, test.getFinalCharge());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Test Checkout 3 pass.");
    }

    @Test
    public void testCheckout4() {
        // Checkout Values
        String toolCode = "JAKD";
        int dayCount = 6;
        LocalDate checkoutDate = LocalDate.of(2015,9,3);
        double discount = 0;

        System.out.println("Test Checkout 4\n");
        System.out.println("Test Checkout Parameters");
        System.out.println("tool code: "+toolCode);
        System.out.printf("day count: %d", dayCount).println();
        System.out.println("checkout date: "+checkoutDate.format(formatter));
        System.out.printf("discount: %.0f\n", discount).println();

        // Start Test
        try {
            RentalAgreement test = toolRentalPOS.checkout(toolCode, dayCount, checkoutDate, discount);
    
            // Print Tool 
            toolRentalPOS.printTool(toolCode);
            // Print rental agreement
            test.print();

            // Get Tool Information
            Optional<Tool> optTool = toolRentalPOS.findTool(toolCode);
            if(!optTool.isPresent()) {
                throw new Exception("Tool not found.");
            }

            Tool tool = optTool.get();
            
            // Test tool code
            assertEquals(toolCode, test.getToolCode());
            // Test tool type
            assertEquals(tool.getToolType(), test.getToolType());
            // Test tool brand
            assertEquals(tool.getBrand(), test.getToolBrand());
            // Test rental days
            assertEquals(dayCount, test.getRentalDays());
            // Test checkout date
            assertEquals(checkoutDate, test.getCheckoutDate());
            // Test due date
            assertEquals(checkoutDate.plusDays(dayCount), test.getDueDate());
            // Test daily rental charge
            assertEquals(tool.getDailyCharge(), test.getDailyRentalCharge());
            // Test charge days
            assertEquals(3, test.getChargeDays());
            // Test pre-discount charge
            assertEquals(8.97, test.getPreDiscountCharge());
            // Test discount percent
            assertEquals(discount, test.getDiscountPercent());
            // Test discount amount
            assertEquals(0, test.getDiscountAmount());
            // Test final charge
            assertEquals(8.97, test.getFinalCharge());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Test Checkout 4 pass.");
    }

    @Test
    public void testCheckout5() {
        // Checkout Values
        String toolCode = "JAKR";
        int dayCount = 9;
        LocalDate checkoutDate = LocalDate.of(2015,7,2);
        double discount = 0;

        System.out.println("Test Checkout 5\n");
        System.out.println("Test Checkout Parameters");
        System.out.println("tool code: "+toolCode);
        System.out.printf("day count: %d", dayCount).println();
        System.out.println("checkout date: "+checkoutDate.format(formatter));
        System.out.printf("discount: %.0f\n", discount).println();

        // Start Test
        try {
            RentalAgreement test = toolRentalPOS.checkout(toolCode, dayCount, checkoutDate, discount);
    
            // Print Tool 
            toolRentalPOS.printTool(toolCode);
            // Print rental agreement
            test.print();

            // Get Tool Information
            Optional<Tool> optTool = toolRentalPOS.findTool(toolCode);
            if(!optTool.isPresent()) {
                throw new Exception("Tool not found.");
            }

            Tool tool = optTool.get();
            
            // Test tool code
            assertEquals(toolCode, test.getToolCode());
            // Test tool type
            assertEquals(tool.getToolType(), test.getToolType());
            // Test tool brand
            assertEquals(tool.getBrand(), test.getToolBrand());
            // Test rental days
            assertEquals(dayCount, test.getRentalDays());
            // Test checkout date
            assertEquals(checkoutDate, test.getCheckoutDate());
            // Test due date
            assertEquals(checkoutDate.plusDays(dayCount), test.getDueDate());
            // Test daily rental charge
            assertEquals(tool.getDailyCharge(), test.getDailyRentalCharge());
            // Test charge days
            assertEquals(5, test.getChargeDays());
            // Test pre-discount charge
            assertEquals(14.95, test.getPreDiscountCharge());
            // Test discount percent
            assertEquals(discount, test.getDiscountPercent());
            // Test discount amount
            assertEquals(0, test.getDiscountAmount());
            // Test final charge
            assertEquals(14.95, test.getFinalCharge());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Test Checkout 5 pass.");
    }

    @Test
    public void testCheckout6() {
        // Checkout Values
        String toolCode = "JAKR";
        int dayCount = 4;
        LocalDate checkoutDate = LocalDate.of(2020,7,2);
        double discount = 50;

        System.out.println("Test Checkout 6\n");
        System.out.println("Test Checkout Parameters");
        System.out.println("tool code: "+toolCode);
        System.out.printf("day count: %d", dayCount).println();
        System.out.println("checkout date: "+checkoutDate.format(formatter));
        System.out.printf("discount: %.0f\n", discount).println();

        // Start Test
        try {
            RentalAgreement test = toolRentalPOS.checkout(toolCode, dayCount, checkoutDate, discount);
    
            // Print Tool 
            toolRentalPOS.printTool(toolCode);
            // Print rental agreement
            test.print();

            // Get Tool Information
            Optional<Tool> optTool = toolRentalPOS.findTool(toolCode);
            if(!optTool.isPresent()) {
                throw new Exception("Tool not found.");
            }

            Tool tool = optTool.get();
            
            // Test tool code
            assertEquals(toolCode, test.getToolCode());
            // Test tool type
            assertEquals(tool.getToolType(), test.getToolType());
            // Test tool brand
            assertEquals(tool.getBrand(), test.getToolBrand());
            // Test rental days
            assertEquals(dayCount, test.getRentalDays());
            // Test checkout date
            assertEquals(checkoutDate, test.getCheckoutDate());
            // Test due date
            assertEquals(checkoutDate.plusDays(dayCount), test.getDueDate());
            // Test daily rental charge
            assertEquals(tool.getDailyCharge(), test.getDailyRentalCharge());
            // Test charge days
            assertEquals(1, test.getChargeDays());
            // Test pre-discount charge
            assertEquals(2.99, test.getPreDiscountCharge());
            // Test discount percent
            assertEquals(discount, test.getDiscountPercent());
            // Test discount amount
            assertEquals(1.50, test.getDiscountAmount());
            // Test final charge
            assertEquals(1.50, test.getFinalCharge());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Test Checkout 6 pass.");
    }

    @Test
    public void testCheckout7() {
        // Checkout Values
        String toolCode = "JAKR";
        int dayCount = 0;
        LocalDate checkoutDate = LocalDate.of(2015,9,3);
        double discount = 0;
        
        System.out.println("Test Checkout 7\n");
        System.out.println("Test Checkout Parameters");
        System.out.println("Tool Code: "+toolCode);
        System.out.printf("Day Count: %d", dayCount).println();
        System.out.println("Checkout Date: "+checkoutDate.format(formatter));
        System.out.printf("Discount: %.0f\n", discount).println();

        // Start Test
        Exception exception = assertThrows(Exception.class, () -> {
            RentalAgreement test1 = toolRentalPOS.checkout(toolCode, dayCount, checkoutDate, discount);
            test1.print();
        });

        String expectedErrorMessage = "Rental day count is not 1 or greater.";
        String actualErrorMessage = exception.getMessage();
        assertEquals(expectedErrorMessage, actualErrorMessage);
        System.out.println("Test Checkout 7 pass.");
    }
    
    @Test
    public void testCheckout8() {
        // Checkout Values
        String toolCode = "NOTATOOL";
        int dayCount = 1;
        LocalDate checkoutDate = LocalDate.of(2015,9,3);
        double discount = 0;
        
        System.out.println("Test Checkout 8\n");
        System.out.println("Test Checkout Parameters");
        System.out.println("Tool Code: "+toolCode);
        System.out.printf("Day Count: %d", dayCount).println();
        System.out.println("Checkout Date: "+checkoutDate.format(formatter));
        System.out.printf("Discount: %.0f\n", discount).println();

        // Start Test
        Exception exception = assertThrows(Exception.class, () -> {
            RentalAgreement test1 = toolRentalPOS.checkout(toolCode, dayCount, checkoutDate, discount);
            test1.print();
        });

        String expectedErrorMessage = "Tool not found with tool code " + toolCode;
        String actualErrorMessage = exception.getMessage();
        assertEquals(expectedErrorMessage, actualErrorMessage);
        System.out.println("Test Checkout 8 pass.");
    }

    @Test
    public void testAddTool() {
        Tool newTool = new Tool("NTR", "Makita", "Saw", 3.99, false, true, true);
        
        assertFalse(toolRentalPOS.getTools().contains(newTool));
        
        toolRentalPOS.addTool(newTool);

        assertTrue(toolRentalPOS.getTools().contains(newTool));
        System.out.println("Test Add Tool pass.");
    }

    @Test
    public void testAddHoliday() {
        Holiday newHoliday = new Holiday("Christmas", year -> LocalDate.of(year, 12, 25));
        
        assertFalse(toolRentalPOS.getHolidays().contains(newHoliday));
        
        toolRentalPOS.addHoliday(newHoliday);

        assertTrue(toolRentalPOS.getHolidays().contains(newHoliday));

        System.out.println("Test Add Holiday pass.");
    }
    
    @Test
    public void testSetTools() {
        Tool newTool = new Tool("STR", "DeWalt", "Jigsaw", 2.50, true, false, true);
        ArrayList<Tool> newTools = new ArrayList<>();
        newTools.add(newTool);

        toolRentalPOS.setTools(newTools);
        
        assertEquals(1, toolRentalPOS.getTools().size());
        assertTrue(toolRentalPOS.getTools().contains(newTool));
        System.out.println("Test Set Tools pass.");
    }
    
    @Test
    public void testSetHolidays() {
        Holiday newHoliday = new Holiday("Thanksgiving", year -> LocalDate.of(year, 11, 25));
        ArrayList<Holiday> newHolidays = new ArrayList<>();
        newHolidays.add(newHoliday);

        toolRentalPOS.setHolidays(newHolidays);
        
        assertEquals(1, toolRentalPOS.getHolidays().size());
        assertTrue(toolRentalPOS.getHolidays().contains(newHoliday));
        System.out.println("Test Set Holidays pass.");
    }
}
