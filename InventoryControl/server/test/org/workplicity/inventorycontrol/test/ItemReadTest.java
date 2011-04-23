/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.util.Helper;

/**
 *
 * @author Brian Gormanly
 */
public class ItemReadTest extends Test {

    public ItemReadTest() {
        super();
        
    }
    /**
     * NetTest entry point for the JVM
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ItemReadTest thisTest = new ItemReadTest();
            
            String criteria1 = "/list[name='Main College Inventory']/list";
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Item> list = Helper.query("Inventories", criteria1, context);
            if (list == null) {
                throw new Exception("bad query");
            }
            //Print all inventories
            for (Item item : list) {
                //if(item.get)

                System.out.println("ID:" + item.getId() + " Name:"
                        + item.getName() + " Description:"
                        + item.getDescription() + " oem:"
                        + item.getOem() + " Model Number:"
                        + item.getModelNumber() + " Inventory ID:"
                        + item.getInventoryId() + " Trainings:"
                        + item.getTrainings() + " Order History:"
                        + item.getOrderHistory() + " Stock Theshold:"
                        + item.getStockThreshold() + " Trigger:"
                        + item.isTrigger() + " Count If:"
                        + item.getCountIf());

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
