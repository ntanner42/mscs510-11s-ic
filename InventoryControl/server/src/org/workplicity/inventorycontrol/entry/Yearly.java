/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

/**
 *
 * @author briangormanly
 */
public class Yearly extends Recurring {

    private Integer day;
    private Integer month;

    public Yearly() {
    }

    /**
     * @return the day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * @return the month
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(Integer month) {
        this.month = month;
    }
}
