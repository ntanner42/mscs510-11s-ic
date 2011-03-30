/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.repos;

import org.workplicity.inventorycontrol.entry.Part;
import org.workplicity.repos.Repository;

/**
 *
 * @author SHAN
 */
public class Parts extends Repository<Part> {

     public final static String TITLE = "Parts";

    /**
     * Constructor
     */
    public Parts() {
        super(TITLE);
    }
}
