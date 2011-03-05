/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

/**
 *
 * @author briangormanly
 */
public class Weekly extends Recurring {

    private int day;

    public Weekly() {
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }
}
