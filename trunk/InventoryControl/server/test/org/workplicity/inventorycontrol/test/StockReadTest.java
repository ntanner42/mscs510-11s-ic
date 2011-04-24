/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Date;
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
public class StockReadTest extends Test {

    public StockReadTest() {
        super();
    }
    
    public static void main(String[] args) {
        
        StockReadTest thisTest = new StockReadTest();
        
        try {
            String criteria1 = "/list";
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Inventory> inventories = Helper.query("Inventories", criteria1, context);
            if (inventories == null) {
                throw new Exception("bad query");
            }
            
            for (Inventory inventory : inventories) {
                
                // add blank lines around inventory for better hierarchacal clarity.
                System.out.println("");
                
                System.out.println("ID:" + inventory.getId() + " Name:"
                        + inventory.getName() + " Description:"
                        + inventory.getDescription() + " Created DATE:"
                        + new Date(inventory.getCreateDate().getTime())
                        + " Updated DATE:"
                        + new Date(inventory.getUpdateDate().getTime()));
                
                // add blank lines around inventory for better hierarchacal clarity.
                System.out.println("");
                
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
                        
                        //query the stock for the item
                        //Print all items in the inventory
                        String criteria3 = "/list[itemId=" + item.getId().toString() + "]";
                        ArrayList<Stock> stocks = Helper.query("Inventories", criteria3, context);

                        for(int j=0; j<stocks.size(); j++) {
                            try{
                                Stock stock = stocks.get(j);
                                
                                System.out.println("Part Num:" + stock.getPartNumber() + " Serial Num:"
                                    + stock.getSerialNumber() + " Asset Tag:"
                                    + stock.getAssetTag() + "rmaNumber :"
                                    + stock.getRmaNumber() + " Item Number:"
                                    + stock.getItemId());
                                
                            }
                            catch(Exception e) {
                                System.out.println("Incorrect type... skipping...");
                            }
                        }

                    }
                    catch(Exception e) {
                        System.out.println("Incorrect type... skipping...");
                    }
                }
                
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
