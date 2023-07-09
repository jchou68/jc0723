package com.tool_rental_pos;
import org.junit.jupiter.api.Test;

import com.tool_rental_pos.other.classes.Tool;

import static org.junit.jupiter.api.Assertions.*;

public class ToolTest {

    @Test
    public void testToolCreation() {
        Tool tool = new Tool("TLR", "Ladder", "Bosch",  1.99, true, false, true);
        
        assertEquals("TLR", tool.getToolCode());
        assertEquals("Bosch", tool.getBrand());
        assertEquals("Ladder", tool.getToolType());
        assertEquals(1.99, tool.getDailyCharge());
        assertTrue(tool.isWeekdayCharge());
        assertFalse(tool.isWeekendCharge());
        assertTrue(tool.isHolidayCharge());

        System.out.println("Test Tool Creation pass.");
    }

    @Test
    public void testEmptyConstructor() {
        Tool tool = new Tool();

        assertNull(tool.getToolCode());
        assertNull(tool.getBrand());
        assertNull(tool.getToolType());
        assertEquals(0, tool.getDailyCharge());
        assertFalse(tool.isWeekdayCharge());
        assertFalse(tool.isWeekendCharge());
        assertFalse(tool.isHolidayCharge());

        System.out.println("Test Empty Tool Constructor pass.");
    }
}