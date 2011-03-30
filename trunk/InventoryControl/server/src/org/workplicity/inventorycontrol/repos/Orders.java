/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.repos;

import org.workplicity.inventorycontrol.entry.OrderAudit;
import org.workplicity.repos.Repository;

/**
 *
 * @author SHAN
 */
public class Orders extends Repository<OrderAudit> {

     public final static String TITLE = "Orders";

    /**
     * Constructor
     */
    public Orders() {
        super(TITLE);
    }
}
