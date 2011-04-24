/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Random;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.util.Logger;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author Brian Gormanly
 */
public class ItemDeleteTest extends Test {
    
    public ItemDeleteTest() {
        super();
        
    }

   
    public static void main(String[] args) {
        try {
            
            ItemDeleteTest thisItem = new ItemDeleteTest();
            
            String criteria1 = "/ list";
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Item> items = Helper.query("Inventories", criteria1, context);
            //deleting all inventories
            for(int i=0; i<items.size(); i++) {
                try{
                        Item item = items.get(i);
                    if (!Helper.delete(item, "Inventories", context)) {
                        System.out.print("Delete failed!");
                    }
                }
                catch(Exception e) {
                    System.out.println("Incorrect type... skipping...");
                }
                    
            }
            System.out.print("Delete finished");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
