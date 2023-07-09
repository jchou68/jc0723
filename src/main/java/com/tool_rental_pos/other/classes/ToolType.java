package com.tool_rental_pos.other.classes;

public class ToolType {
    /* Members */
    private String type;
    private double dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;


    /* Constructors */
    public ToolType() {}

    public ToolType(String type, double dailyCharge, boolean weekdayCharge, boolean weekendCharge,
            boolean holidayCharge) {
        this.type = type;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    /* Methods */
    /**
     * Get the type of tool
     * @return
     */
    public String getToolType() {
        return type;
    }

    /**
     * Set the type of tool
     * @param type
     */
    public void setToolType(String type) {
        this.type = type;
    }

    /**
     * Get the daily charge of renting the tool
     * @return Double daily charge
     */
    public double getDailyCharge() {
        return dailyCharge;
    }

    /**
     * Set the daily charge of the tool
     * @param dailyCharge Double daily charge amount in form of XX.XX
     */
    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    /**
     * Get whether the tool charges for weekday rentals
     * @return Boolean
     */
    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    /**
     * Set whether the tool charges for weekday rental
     * @param weekdayCharge Boolean
     */
    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    /**
     * Get whether the tool charges for weekend rentals
     * @return Boolean
     */
    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    /**
     * Set whether the tool charges for weekends
     * @param weekendCharge Boolean
     */
    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    /**
     * Get whether the tool charges for holiday rentals
     * @return Boolean
     */
    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    /**
     * Set whether to charge rental for holidays 
     * @param holidayCharge Boolean
     */
    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }
}
