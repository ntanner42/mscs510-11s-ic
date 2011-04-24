    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.Random;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.util.Logger;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SHAN
 * @author Brian Gormanly
 */
public class InventoryCreateTest extends Test {

    public InventoryCreateTest() {
        super();
        
        try {

            //Create array of names
            String[] names = {"Main College Inventory", "Security Inventory"};
            
            //Create array of descriptions
            String[] descriptions = {"All Items that belong to the main College inventory", "Items that are managed by the security force only"};
            
            
            for (int i = 0; i < names.length; i++) {
                Inventory inventory = new Inventory("inventory" + i);

                inventory.setName(names[i]);
                inventory.setDescription(descriptions[i]);
                
                System.out.println(inventory.getId());

                if (!Helper.insert(inventory, "Inventories", context)) {
                    System.out.println("insert Item into Inventory failed!");
                }
                
                System.out.println(inventory.getId());
            }
            System.out.print("Insert finished");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        InventoryCreateTest thisTest = new InventoryCreateTest();
        
        
    }
}
