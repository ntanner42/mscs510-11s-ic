/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.repos;

import org.workplicity.inventorycontrol.entry.OperationAudit;
import org.workplicity.repos.Repository;

/**
 *
 * @author SHAN
 */
public class Operations extends Repository<OperationAudit> {

     public final static String TITLE = "Operations";

    /**
     * Constructor
     */
    public Operations() {
        super(TITLE);
    }
}
