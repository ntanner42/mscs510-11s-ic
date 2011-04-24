    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Random;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.util.Helper;
import org.workplicity.util.WorkDate;

/**
 *
 * @author SHAN
 * @author Brian Gormamnly
 */
public class ItemCreateTest extends Test {
    
    public ItemCreateTest() {
        super();
        
    }

    public static void main(String[] args) {
        try {
            
            ItemCreateTest thisTest = new ItemCreateTest();
            
            String criteria1 = "/ list";
            //Issuing the query using the helper to the accounts repository
            ArrayList<Inventory> inventories = Helper.query("Inventories", criteria1, context);
            //Create 100 random Items  and add to each Inventory
            
            for (Inventory inventory : inventories) {
                
                String suffix = "";

                // create arrays to hold data to insert into items
                String[] names = {"Clean View Helix", "Halo UV", "Ball Vac", "Floor Waxer", "Floor Striper"};
                String[] descriptions = {"Sucks like no other", "can change from suck to blow", "rolls around nicely", "Waxes floors with vigor", "Strips really well"};
                String[] oems = {"Bissell", "Oreck", "Dyson", "Waxinator", "Hobart"};
                String[] models = {"21K3", "85541", "ABC123", "EEB2333", "232BC"};
                Integer[] sts = {5, 5, 2, 2, 1};
                
                for(int i=0; i<names.length; i++) {
                    if(inventory.getName() == "Security Inventory") {
                        // add security suffix for security items only
                        suffix = " Security";
                    }

                    Item item = new Item(names[i] + suffix);

                    item.setName(names[i] + suffix);
                    item.setDescription(descriptions[i]);
                    item.setOem(oems[i]);
                    item.setModelNumber(models[i]);
                    item.setInventoryId(inventory.getId());
                    item.setStockThreshold(sts[i]);

                    inventory.insert(item);
                    
                    
                    System.out.println(item.getId());

                    if (!Helper.insert(item, "Inventories", context)) {
                        System.out.println("insert Item into Inventory failed!");
                    }

                    System.out.println(item.getId());
                   
                }
            }
            System.out.print("Insert finished");


        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
