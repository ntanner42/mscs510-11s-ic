/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.repos;

import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.repos.Repository;

/**
 *
 * @author SHAN
 */
public class Items extends Repository<Item> {

    private static String TITLE = "Items";

    /**
     * Constructor
     */
    public Items() {
        super(TITLE);
    }
}
