/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.repos;

import org.workplicity.inventorycontrol.entry.Event;
import org.workplicity.repos.Repository;

/**
 *
 * @author SHAN
 */
public class Events extends Repository<Event> {

     public final static String TITLE = "Events";

    /**
     * Constructor
     */
    public Events() {
        super(TITLE);
    }
}
