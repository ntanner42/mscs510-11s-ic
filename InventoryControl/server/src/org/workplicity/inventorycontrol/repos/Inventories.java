/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.repos;

import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.repos.Repository;

/**
 *
 * @author Uday
 */
public class Inventories extends Repository<Inventory> {

    public final static String TITLE = "Inventories";

    /**
     * Constructor
     */
    public Inventories() {
        super(TITLE);
    }
}
