/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.repos;

import org.workplicity.inventorycontrol.entry.Location;
import org.workplicity.repos.Repository;

/**
 *
 * @author SHAN
 */
public class Locations extends Repository<Location> {

     public final static String TITLE = "Locations";

    /**
     * Constructor
     */
    public Locations() {
        super(TITLE);
    }
}
