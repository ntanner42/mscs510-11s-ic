/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.repos;

import org.workplicity.inventorycontrol.entry.Stock;
import org.workplicity.repos.Repository;

/**
 *
 * @author SHAN
 */
public class Stocks extends Repository<Stock> {

    public final static String TITLE = "Stocks";

    /**
     * Constructor
     */
    public Stocks() {
        super(TITLE);
    }
}
