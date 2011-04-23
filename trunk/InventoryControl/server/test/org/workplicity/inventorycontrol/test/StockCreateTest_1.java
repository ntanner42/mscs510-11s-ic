/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.inventorycontrol.entry.Stock;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.util.Logger;
import org.workplicity.util.WorkDate;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SHAN
 * @author Brian Gormanly
 */
public class StockCreateTest_1 extends Test {

    public StockCreateTest_1() {
        super();
    }
    
    public static void main(String[] args) {
        try {
            
            StockCreateTest_1 thisItem = new StockCreateTest_1();
            
            
            
            
            String criteria1 = "/ list";
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Inventory> list1 = Helper.query("Inventories", criteria1, context);
            //Insert each 100 stock entries in each Item.
            for (Inventory inventory : list1) {
                List<Item> items = inventory.getList();
                for (Item item : items) {
                    for (int i = 0; i < 100; i++) {
                        Random random = new Random();

                        Stock stock = new Stock();
                        stock.setPartNumber("" + random.nextInt(100000));
                        stock.setSerialNumber("" + random.nextInt(200000));
                        stock.setAssetTag("" + random.nextInt(300000));
                        stock.setRmaNumber("" + random.nextInt(100000));
                        //add stock to the item
                        item.getList().add(stock);
                    }
                    //Inventory repository must be updated with the item since
                    //the item is updated with new stock
                    inventory.update(item, new WorkDate());
                }
                if (!Helper.insert(inventory, "Inventories", context)) {
                    System.out.println("insert Stock into Items  failed!");
                }
            }
            System.out.print("Insert finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
