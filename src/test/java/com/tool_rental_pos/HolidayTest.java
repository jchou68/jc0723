package com.tool_rental_pos;
import org.junit.jupiter.api.Test;

import com.tool_rental_pos.other.classes.Holiday;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

public class HolidayTest {

    @Test
    public void testFixedDateHoliday() {
        LocalDate fixedDate = LocalDate.of(2023, 12, 25);
        Holiday holiday = new Holiday("Christmas", fixedDate);
        assertEquals("Christmas", holiday.getName());
        assertEquals(fixedDate, holiday.getDate());
        System.out.println("Test Fixed Date Holiday pass.");
    }

    @Test
    public void testHolidayWithDateRule() {
        Holiday laborDay = Holiday.createLaborDay();
        assertEquals("Labor Day", laborDay.getName());
        assertEquals(LocalDate.of(2023, Month.SEPTEMBER, 4), laborDay.getDate(2023));
        System.out.println("Test Holiday with Date Rule pass.");
    }

    @Test
    public void testGetObservedDate_Saturday() {
        LocalDate saturdayHoliday = LocalDate.of(2023, Month.JANUARY, 7);
        Holiday holiday = new Holiday("Saturday Holiday", saturdayHoliday);

        LocalDate observedDate = holiday.getObservedDate(2023);
        assertEquals(LocalDate.of(2023, Month.JANUARY, 6), observedDate);
        System.out.println("Test Get Observed Date - Saturday pass.");
    }

    @Test
    public void testGetObservedDate_Sunday() {
        LocalDate sundayHoliday = LocalDate.of(2023, Month.JANUARY, 8);
        Holiday holiday = new Holiday("Sunday Holiday", sundayHoliday);

        LocalDate observedDate = holiday.getObservedDate(2023);
        assertEquals(LocalDate.of(2023, Month.JANUARY, 9), observedDate);

        System.out.println("Test Get Observed Date - Sunday pass.");
    }

    @Test
    public void testGetObservedDate_Weekday() {
        LocalDate weekdayHoliday = LocalDate.of(2023, Month.JANUARY, 9);
        Holiday holiday = new Holiday("Weekday Holiday", weekdayHoliday);

        LocalDate observedDate = holiday.getObservedDate(2023);
        assertEquals(weekdayHoliday, observedDate);

        System.out.println("Test Get Observed Date - Weekday pass.");
    }
}
