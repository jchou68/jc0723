package com.tool_rental_pos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.tool_rental_pos.other.classes.RentalAgreement;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalAgreementTest {

    private RentalAgreement rentalAgreement;

    @BeforeEach
    public void setup() {
        // Initialize a RentalAgreement object before each test
        rentalAgreement = new RentalAgreement("T001", "Screwdriver", "Brand X", 5,
                LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 6),
                9.99, 4, 39.96, 10, 3.99, 35.96);
    }

    @Test
    public void testRentalAgreementInitialization() {
        assertEquals("T001", rentalAgreement.getToolCode());
        assertEquals("Screwdriver", rentalAgreement.getToolType());
        assertEquals("Brand X", rentalAgreement.getToolBrand());
        assertEquals(5, rentalAgreement.getRentalDays(), 0.001);
        assertEquals(LocalDate.of(2023, 1, 1), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2023, 1, 6), rentalAgreement.getDueDate());
        assertEquals(9.99, rentalAgreement.getDailyRentalCharge(), 0.001);
        assertEquals(4, rentalAgreement.getChargeDays(), 0.001);
        assertEquals(39.96, rentalAgreement.getPreDiscountCharge(), 0.001);
        assertEquals(10, rentalAgreement.getDiscountPercent(), 0.001);
        assertEquals(3.99, rentalAgreement.getDiscountAmount(), 0.001);
        assertEquals(35.96, rentalAgreement.getFinalCharge(), 0.001);
    }
}
