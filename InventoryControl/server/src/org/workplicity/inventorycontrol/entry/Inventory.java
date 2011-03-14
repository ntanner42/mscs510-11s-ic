/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

import java.util.ArrayList;
import java.util.List;
import org.workplicity.entry.Entry;

/**
 *
 * @author Uday
 */
public class Inventory extends Entry {

    private static final long serialVersionUID = -6482381378329769196L;
    protected String name;
    protected List<Integer> items = new ArrayList<Integer>();
    protected List<Integer> services = new ArrayList<Integer>();

    public Inventory() {
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

    /**
     * @return the items
     */
    public List<Integer> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Integer> items) {
        this.items = items;
    }

    /**
     * @return the services
     */
    public List<Integer> getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(List<Integer> services) {
        this.services = services;
    }
}