package com.tool_rental_pos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tool_rental_pos.other.classes.ToolType;

import static org.junit.jupiter.api.Assertions.*;

public class ToolTypeTest {

    private ToolType toolType;

    @BeforeEach
    public void setup() {
        // Initialize a ToolType object before each test
        toolType = new ToolType("Screwdriver", 5.99, true, false, true);
    }

    @Test
    public void testToolTypeInitialization() {
        assertEquals("Screwdriver", toolType.getToolType());
        assertEquals(5.99, toolType.getDailyCharge(), 0.001);
        assertTrue(toolType.isWeekdayCharge());
        assertFalse(toolType.isWeekendCharge());
        assertTrue(toolType.isHolidayCharge());
    }

    @Test
    public void testSetToolType() {
        assertEquals("Screwdriver", toolType.getToolType());

        toolType.setToolType("Wrench");
        assertEquals("Wrench", toolType.getToolType());
    }

    @Test
    public void testSetDailyCharge() {
        assertEquals(5.99, toolType.getDailyCharge(), 0.001);

        toolType.setDailyCharge(7.99);
        assertEquals(7.99, toolType.getDailyCharge(), 0.001);
    }

    @Test
    public void testSetWeekdayCharge() {
        assertTrue(toolType.isWeekdayCharge());

        toolType.setWeekdayCharge(false);
        assertFalse(toolType.isWeekdayCharge());
    }

    @Test
    public void testSetWeekendCharge() {
        assertFalse(toolType.isWeekendCharge());

        toolType.setWeekendCharge(true);
        assertTrue(toolType.isWeekendCharge());
    }

    @Test
    public void testSetHolidayCharge() {
        assertTrue(toolType.isHolidayCharge());

        toolType.setHolidayCharge(false);
        assertFalse(toolType.isHolidayCharge());
    }
}
