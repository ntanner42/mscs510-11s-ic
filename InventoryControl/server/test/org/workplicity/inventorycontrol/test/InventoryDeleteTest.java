/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Random;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.util.Logger;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SHAN
 * @author Brian Gormanly
 */
public class InventoryDeleteTest extends Test {

    public InventoryDeleteTest() {
        super();
        
        try {
            
            String criteria1 = "/ list";
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Inventory> list = Helper.query("Inventories", criteria1, context);
            //deleting all inventories
            for(int i=0; i<list.size(); i++) {
                try{
                    Inventory inventory = list.get(i);
                    if (!Helper.delete(inventory, "Inventories", context)) {
                        System.out.print("Delete failed!");
                    }
                    
                }
                catch (Exception e) {
                    System.out.println("Incorrect type... skipping...");
                }
            }

            System.out.print("Delete finished");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        InventoryDeleteTest thisTest = new InventoryDeleteTest();

    }
}
