package com.tool_rental_pos.other.classes;

import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ToolRentalPOS {
    /** Members */
    private List<Tool> tools;
    private List<Holiday> holidays;
    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    
    /** Constructors */
    public ToolRentalPOS() {
        this.tools = new ArrayList<>();
        this.holidays = new ArrayList<>();
    }
    public ToolRentalPOS(List<Tool>  tools) {
        this.tools = new ArrayList<>(tools);
        this.holidays = new ArrayList<>();
    }
    public ToolRentalPOS(List<Tool>  tools, List<Holiday> holidays) {
        this.tools = new ArrayList<>(tools);
        this.holidays = new ArrayList<>(holidays);
    }

    /** Methods */
    /**
     * Checkout a tool from the Tool Rental POS
     * After checkout is completed, the transaction will be printed on console.
     * @param toolCode Code of tool to checkout
     * @param dayCount The number of dayts for which the customer wants to rent the tool (e.g. 4 days)
     * @param checkoutDate Date the tool is being checked out on
     * @param discount Tool discount applied to daily rate, As a whole number, 0-100.
     */
    public RentalAgreement checkout(String toolCode, int dayCount, LocalDate checkoutDate, double discountPercent) throws Exception {
        validateDayCount(dayCount);
        validateDiscountPercent(discountPercent);

        Optional<Tool> tool = findTool(toolCode);
        validateTool(tool, toolCode);
        Tool toolToCheckout = tool.get();

        LocalDate dueDate = checkoutDate.plusDays(dayCount);
        double chargeDays = calculateChargeDays(dayCount, checkoutDate, toolToCheckout);

        double dailyCharge = toolToCheckout.getDailyCharge();
        double preDiscountCharge = calculatePreDiscountCharge(chargeDays, dailyCharge);
        double discountAmount = calculateDiscountAmount(preDiscountCharge, discountPercent);
        double finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);

        RentalAgreement rentalAgreement = createRentalAgreement(toolCode, toolToCheckout, dayCount, checkoutDate,
                dueDate, dailyCharge, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);

        rentalAgreement.print();

        return rentalAgreement;
    }

    /**
     * Add tool to the Tool Rental POS
     * @param tool Tool to add to POS
     */
    public void addTool(Tool tool) {
        this.tools.add(tool);
    }

    /**
     * Add a holiday into the Tool Rental POS
     * @param holiday Holiday to add to POS
     */
    public void addHoliday(Holiday holiday) {
        this.holidays.add(holiday);
    }

    /**
     * Get a list of all the tools in the system
     * @return List of tools
     */
    public List<Tool> getTools() {
        return new ArrayList<>(this.tools);
    }

    /**
     * Provide a list of tools to setup the POS
     * @param tools List of tools
     */
    public void setTools(List<Tool> tools) {
        this.tools = new ArrayList<>(tools);
    }

    /**
     * Gets a list of holidays set for the POS
     * @return List of holidays
     */
    public List<Holiday> getHolidays() {
        return new ArrayList<>(this.holidays);
    }

    /**
     * Set the holidays for the Tool Rental POS
     * @param holidays List of holidays
     */
    public void setHolidays(List<Holiday> holidays) {
        this.holidays = new ArrayList<>(holidays);
    }

    /**
     * Print a tool report for a specific tool
     * @param toolCode Provide tool code to print the report against
     * @throws Exception
     */
    public void printTool(String toolCode) throws Exception {
        Optional<Tool> foundTool = findTool(toolCode);
        validateTool(foundTool, toolCode);
        Tool tool = foundTool.get();

        printToolReportHeader();
        printToolReportRow(tool);
    }
    
    /**
     * Print the tool report for all tools in the system.
     */
    public void printTools() {        
        printToolReportHeader();
        printToolReportTable();
    }
    
    /**
     * Print the tool report for all tools in the system.
     */
    public void printHolidays() {        
        // Print Header
        System.out.println();
        System.out.println("=".repeat(105));
        System.out.printf("%-44s%s", " ","Holiday Report").println();
        System.out.println("=".repeat(105));
        String headerFormat = "%-25s %-12s";
        System.out.printf(headerFormat,"Holiday","Date").println();

        System.out.println("=".repeat(105));

        // Print Table
        String toolFormat = "%-25s %-12s";
        for(int i=0; i < this.holidays.size(); i++) {
            Holiday holiday = this.holidays.get(i);
            System.out.printf(toolFormat, holiday.getName(), holiday.getDate()).println();
        }
    }

    /**
     * Finds the tool in the tool list provided by the tool code
     * @param toolCode Tool code to search for in the tools list
     * @return Tool matching the tool code or undefined
     */
    public Optional<Tool> findTool(String toolCode) {
        return this.tools.stream()
                .filter(tool -> tool.getToolCode().equals(toolCode))
                .findFirst();
    }

    /**
     * Checks if the given date is a holiday in the system.
     * @param dateToCheck LocalDate 
     * @return boolean
     */
    public boolean isHoliday(LocalDate dateToCheck) {
        for (Holiday holiday : this.holidays) {
            LocalDate holidayDate = holiday.getObservedDate(dateToCheck.getYear());
            
            if (dateToCheck.equals(holidayDate)) {
                return true;
            }
        }
        return false;
    }

    private void validateDayCount(int dayCount) throws Exception {
        if (dayCount < 1) {
            throw new IllegalArgumentException("Rental day count is not 1 or greater.");
        }
    }

    private void validateDiscountPercent(double discountPercent) throws Exception {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent is not in the range 0-100.");
        }
    }

    private void validateTool(Optional<Tool> tool, String toolCode) throws Exception {
        if (!tool.isPresent()) {
            throw new Exception("Tool not found with tool code " + toolCode);
        }
    }

    private double calculateChargeDays(int dayCount, LocalDate checkoutDate, Tool toolToCheckout) {
        double chargeDays = 0;

        for (int i = 1; i <= dayCount; i++) {
            LocalDate date = checkoutDate.plusDays(i);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            
            if (isHoliday(date) && !toolToCheckout.isHolidayCharge()) {
                continue;
            } else if (toolToCheckout.isWeekdayCharge() && isWeekday(dayOfWeek)) {
                chargeDays++;
            } else if (toolToCheckout.isWeekendCharge() && isWeekend(dayOfWeek)) {
                chargeDays++;
            }
        }

        return chargeDays;
    }

    private boolean isWeekday(DayOfWeek dayOfWeek) {
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private double calculatePreDiscountCharge(double chargeDays, double dailyCharge) {
        return chargeDays * dailyCharge;
    }

    private double calculateDiscountAmount(double preDiscountCharge, double discountPercent) {
        return (preDiscountCharge * discountPercent) / 100;
    }

    private double calculateFinalCharge(double preDiscountCharge, double discountAmount) {
        return preDiscountCharge - discountAmount;
    }

    private RentalAgreement createRentalAgreement(String toolCode, Tool toolToCheckout, int dayCount,
            LocalDate checkoutDate, LocalDate dueDate, double dailyRentalCharge, double chargeDays,
            double preDiscountCharge, double discountPercent, double discountAmount, double finalCharge) {
        return new RentalAgreement(toolCode, toolToCheckout.getToolType(), toolToCheckout.getBrand(),
                dayCount, checkoutDate, dueDate, dailyRentalCharge, chargeDays, preDiscountCharge,
                discountPercent, discountAmount, finalCharge);
    }

    private void printToolReportHeader() {
        System.out.println();
        System.out.println("=".repeat(105));
        System.out.printf("%-47s%s%n", " ", "Tool Report");
        System.out.println("=".repeat(105));
        String headerFormat = "%-12s %-12s %-12s %-14s %-15s %-15s %-15s%n";
        System.out.printf(headerFormat,
                "Tool Code",
                "Tool Type",
                "Brand",
                "Daily Charge",
                "Weekday Charge",
                "Weekend Charge",
                "Holiday Charge");
        System.out.println("=".repeat(105));
    }

    private void printToolReportTable() {
        for (Tool tool : this.tools) {
            printToolReportRow(tool);
        }
    }

    private void printToolReportRow(Tool tool) {
        String toolFormat = "%-12s %-12s %-12s %-14s %-15b %-15b %-15b%n";
        System.out.printf(toolFormat,
                tool.getToolCode(),
                tool.getToolType(),
                tool.getBrand(),
                currencyFormatter.format(tool.getDailyCharge()),
                tool.isWeekdayCharge(),
                tool.isWeekendCharge(),
                tool.isHolidayCharge());
    }
}
