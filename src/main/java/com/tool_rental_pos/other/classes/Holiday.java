package com.tool_rental_pos.other.classes;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Function;

public class Holiday {
    /** Members */
    private LocalDate date;
    private String name;
    private Function<Integer, LocalDate> dateRule;

    /** Constructors */   
    public Holiday(String name, LocalDate date) {
        this.date = date;
        this.name = name;
        // Default date rule
        this.dateRule = (year) -> LocalDate.of(year, date.getMonthValue(), date.getDayOfMonth());
    }
    
    public Holiday(String name, Function<Integer, LocalDate> dateRule) {
        this.name = name;
        this.dateRule = dateRule;
    }

    /** Methods */
    /**
     * Get date of the holiday
     * @return LocalDate of holiday
     */
    public LocalDate getDate() {
        if(date == null) {
            return dateRule.apply(LocalDate.now().getYear());
        }
        return date;
    }

    /**
     * Get Date of the holiday with the provided year
     * @param year The year the holiday to check against
     * @return LocalDate of holiday
     */
    public LocalDate getDate(int year) {
        return dateRule.apply(year);
    }
    
    /**
     * Get the name of the holiday
     * @return String holiday name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the observed date of the holiday based on the weekend rule.
     * If the holiday falls on a weekend, it is observed on the closest weekday
     * (Friday before if it falls on Saturday, Monday after if it falls on Sunday).
     * If the holiday does not fall on a weekend, the original holdiday date is returned.
     * 
     * @param year The year to determine the observed date
     * @return LocalDate of the observed date
     */
    public LocalDate getObservedDate(int year) {
        LocalDate holidayDate = getDate(year);
        DayOfWeek dayOfWeek = holidayDate.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY) {
            return holidayDate.minusDays(1); // Observed on Friday before
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            return holidayDate.plusDays(1); // Observed on Monday after
        }

        return holidayDate; // No adjustment needed for weekdays
    }

    /** Factory method for creating Labor Day */
    public static Holiday createLaborDay() {
        return new Holiday("Labor Day", year -> LocalDate.of(year, 9, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
    }
}
