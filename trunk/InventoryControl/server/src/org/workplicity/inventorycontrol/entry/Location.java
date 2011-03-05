/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

import org.workplicity.entry.Entry;

/**
 *
 * @author SHAN
 */
public class Location extends Entry {

    private static final long serialVersionUID = -6482381378329769196L;
    private String name;

    public Location() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
