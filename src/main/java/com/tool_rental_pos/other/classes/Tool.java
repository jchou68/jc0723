package com.tool_rental_pos.other.classes;

public class Tool extends ToolType {
    /** Members */
    private String toolCode;
    private String brand;

    /** Constructors */
    public Tool(){}
    
    public Tool(String toolCode, String brand) {
        this.toolCode = toolCode;
        this.brand = brand;
    }

    public Tool(String toolCode, String type, String brand, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        super(type, dailyCharge, weekdayCharge, weekendCharge, holidayCharge);
        this.toolCode = toolCode;
        this.brand = brand;
    }   


    /** Methods */

    /**
     * Get tool code of the tool
     * @return String tool code
     */
    public String getToolCode() {
        return toolCode;
    }

    /**
     * Get brand of tool
     * @return String brand
     */
    public String getBrand() {
        return brand;
    }
}

