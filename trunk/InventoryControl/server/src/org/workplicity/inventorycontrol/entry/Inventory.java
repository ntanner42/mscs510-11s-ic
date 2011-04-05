/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

import java.util.ArrayList;
import java.util.List;
import org.workplicity.repos.Repository;

/**
 *
 * @author Uday
 */
public class Inventory extends Repository<Item> {

    private static final long serialVersionUID = -6482381378329769196L;
    protected String name;
    private String description;
    protected List<Integer>  services = new ArrayList<Integer>();

    public Inventory(String title) {
        super(title);
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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
