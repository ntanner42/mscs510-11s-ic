/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

/**
 *
 * @author briangormanly
 */
public class Monthly extends Recurring {

    private Integer day;

    public Monthly() {
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
}
