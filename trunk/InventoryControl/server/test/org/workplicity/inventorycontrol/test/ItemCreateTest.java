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
                
            String criteria1 = "/list";
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Inventory> list = Helper.query("Inventories", criteria1, context);
   
            
            //cerate counter
            int i=0;

            for (Inventory inventory : list) {

                String suffix = "";

                if(i>0) {
                    // add security suffix for security items only
                    suffix = " Security";
                }
                
                // create a List to hold the id created for the items
                ArrayList<Integer> itemIds = new ArrayList();
                
                // create arrays to hold data to insert into items
                String[] names = {"Clean View Helix", "Halo UV", "Ball Vac", "Floor Waxer", "Floor Striper"};
                String[] descriptions = {"Sucks like no other", "can change from suck to blow", "rolls around nicely", "Waxes floors with vigor", "Strips really well"};
                String[] oems = {"Bissell", "Oreck", "Dyson", "Waxinator", "Hobart"};
                String[] models = {"21K3", "85541", "ABC123", "EEB2333", "232BC"};
                Integer[] sts = {5, 5, 2, 2, 1};
                
                
                for(int j=0; j<names.length; j++) {

                    Item item = new Item(names[i] + suffix);


                    item.setName(names[i] + suffix);
                    item.setDescription(descriptions[i]);
                    item.setOem(oems[i]);
                    item.setModelNumber(models[i]);
                    item.setInventoryId(inventory.getId());
                    item.setStockThreshold(sts[i]);

              
                    if (!Helper.insert(item, "Inventories", context)) {
                        System.out.println("insert Item into Item failed!");
                    }
                    else {
                        // insert succeeded, get id and output
                        String criteria2 = "/list[name='" + item.getName() + "']/getId";
                        ArrayList<Integer> list2 = Helper.query("Inventories", criteria2, context);
                        itemIds.add(list2.get(0));
                        
                        System.out.println(item.getId());
                    
                        // insert output
                        System.out.println("Inserted :" + item.getTitle());
                    }
                    
                    
                
                }
                
                inventory.setServices(itemIds);

                //Inventory repository must be updated with the item since
                //the item is updated with new stock
                inventory.update(inventory, new WorkDate());

                /*
                if (!Helper.insert(inventory, "Inventories", context)) {
                    System.out.println("insert Stock into Items  failed!");
                }
                */

                // increment the counter
                i++;
                
                // insert output
                System.out.println("updated :" + inventory.getTitle());

                

            }

            System.out.print("Insert finished");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
