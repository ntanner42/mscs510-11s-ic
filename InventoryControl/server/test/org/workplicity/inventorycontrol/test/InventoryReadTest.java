/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Date;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.util.Helper;

/**
 *
 * @author SHAN
 * @author Brian Gormanly
 */
public class InventoryReadTest extends Test {
    
    public InventoryReadTest() {
        super();
    }

    public static void main(String[] args) {
        try {
            
            InventoryReadTest thisTest = new InventoryReadTest();
            
            String criteria1 = "/list";
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Inventory> list = Helper.query("Inventories", criteria1, context);
            if (list == null) {
                throw new Exception("bad query");
            }
            //Print all inventories
            for (Inventory inventory : list) {

                System.out.println("ID:" + inventory.getId() + " Name:"
                        + inventory.getName() + " Description:"
                        + inventory.getDescription() + " Created DATE:"
                        + new Date(inventory.getCreateDate().getTime())
                        + " Updated DATE:"
                        + new Date(inventory.getUpdateDate().getTime()));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
