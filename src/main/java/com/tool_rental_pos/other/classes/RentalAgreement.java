package com.tool_rental_pos.other.classes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreement {
    /** Members */
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private double rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private double dailyRentalCharge;
    private double chargeDays;
    private double preDiscountCharge;
    private double discountPercent;
    private double discountAmount;
    private double finalCharge;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
    
    /** Constructors */
    public RentalAgreement(String toolCode, String toolType, String toolBrand, double rentalDays,
            LocalDate checkoutDate, LocalDate dueDate, double dailyRentalCharge, double chargeDays,
            double preDiscountCharge, double discountPercent, double discountAmount, double finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    /** Methods */
    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public double getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getDailyRentalCharge() {
        return Double.parseDouble(decimalFormat.format(dailyRentalCharge));
    }

    public double getChargeDays() {
        return chargeDays;
    }

    public double getPreDiscountCharge() {
        return Double.parseDouble(decimalFormat.format(preDiscountCharge));
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return Double.parseDouble(decimalFormat.format(discountAmount));
    }

    public double getFinalCharge() {
        return Double.parseDouble(decimalFormat.format(finalCharge));
    }

    public void print() {
        // Print Rental Agreement
        System.out.println("\n\n");
        System.out.println("=".repeat(105));
        System.out.printf("%-45s%s%n", " ", "Rental Agreement");
        System.out.println("=".repeat(105));
        System.out.printf("%-35s%-26s%s%n", " ", "Tool Code:", this.toolCode);
        System.out.printf("%-35s%-26s%s%n", " ","Tool type:", this.toolType);
        System.out.printf("%-35s%-26s%s%n", " ","Tool brand:", this.toolBrand);
        System.out.printf("%-35s%-26s%.0f%n", " ","Rental days:", this.rentalDays);
        System.out.printf("%-35s%-26s%s%n", " ","Checkout Date:", this.checkoutDate.format(formatter));
        System.out.printf("%-35s%-26s%s%n", " ","Due Date:", this.dueDate.format(formatter));
        System.out.printf("%-35s%-26s%s%n", " ","Daily rental charge:", currencyFormatter.format(this.dailyRentalCharge));
        System.out.printf("%-35s%-26s%.0f%n", " ","Charge Days:", this.chargeDays);
        System.out.printf("%-35s%-26s%s%n", " ","Pre-discount charge:", currencyFormatter.format(this.preDiscountCharge));
        System.out.printf("%-35s%-26s%.0f%%%n", " ","Discount:", this.discountPercent);
        System.out.printf("%-35s%-26s%s%n", " ","Discount Amount:", currencyFormatter.format(this.discountAmount));
        System.out.printf("%-35s%-26s%s%n", " ","Final charge:", currencyFormatter.format(this.finalCharge));
        System.out.println("\n\n");
    }


}
