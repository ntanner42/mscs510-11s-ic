/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.repos;

import org.workplicity.inventorycontrol.entry.ItemType;
import org.workplicity.repos.Repository;

/**
 *
 * @author SHAN
 */
public class Itemtypes extends Repository<ItemType> {

    private static String TITLE = "ItemTypes";

    /**
     * Constructor
     */
    public Itemtypes() {
        super(TITLE);
    }
}
