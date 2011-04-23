/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Date;
import org.workplicity.inventorycontrol.entry.Inventory;
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
            
            String criteria1 = "/list";
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Inventory> inventories = Helper.query("Inventories", criteria1, context);
            if (inventories == null) {
                throw new Exception("bad query");
            }
            
            for (Inventory inventory : inventories) {
                
                System.out.println("ID:" + inventory.getId() + " Name:"
                        + inventory.getName() + " Description:"
                        + inventory.getDescription() + " Created DATE:"
                        + new Date(inventory.getCreateDate().getTime())
                        + " Updated DATE:"
                        + new Date(inventory.getUpdateDate().getTime()));
            
                
                //Print all items in the inventory
                String criteria2 = "/list[inventoryId=" + inventory.getId().toString() + "]";
                ArrayList<Item> items = Helper.query("Inventories", criteria2, context);
                
                for(int i=0; i<items.size(); i++) {
                    try{
                        Item item = items.get(i);

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
                    catch(Exception e) {
                        System.out.println();
                    }
                }
                
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
