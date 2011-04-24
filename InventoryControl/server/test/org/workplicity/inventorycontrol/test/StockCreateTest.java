/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.inventorycontrol.entry.Stock;
import org.workplicity.util.Helper;


/**
 *
 * @author SHAN
 * @author Brian Gormanly
 */
public class StockCreateTest extends Test {

    public StockCreateTest() {
        super();
    }
    
    public static void main(String[] args) {
        try {
            StockCreateTest thisTest = new StockCreateTest();
            
            String criteria1 = "/list";
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Inventory> inventories = Helper.query("Inventories", criteria1, context);
            if (inventories == null) {
                throw new Exception("bad query");
            }
            
            for(int i=0; i<inventories.size(); i++) {
                try{
                    Inventory inventory = inventories.get(i);
                    
                    //get all the items in the current inventory
                    String criteria2 = "/list[inventoryId=" + inventory.getId().toString() + "]";
                    ArrayList<Item> items = Helper.query("Inventories", criteria2, context);

                    for(int j=0; j<items.size(); j++) {
                        try{
                            Item item = items.get(j);
                            
                            // create stock for items
                            String[] partNumbers = {"1Z123" + i + j, "1Z234" + i + j, "1Z345" + i + j, "1Z456" + i + j};
                            String[] serialNumbers = {"2346543" + i + j, "3458343" + i + j, "2345673" + i + j, "2323434" + i + j};
                            String[] assetTags = {"111" + i + j, "222" + i + j, "333" + i + j, "444" + i + j};
                            if(j>4) {
                                String[] rmaNumbers = {"", "234234252Z2" + i + j, "", ""};
                            }
                            else {
                                String[] rmaNumbers = {"", "", "", ""};
                            }
                            
                            for(int k=0; k<partNumbers.length; k++) {
                                
                                Stock stock = new Stock();

                                stock.setPartNumber(partNumbers[k]);
                                stock.setSerialNumber(serialNumbers[k]);
                                stock.setAssetTag(assetTags[k]);
                                stock.setItemId(item.getId());
                                
                                
                                
                                item.insert(stock);

                                System.out.println(stock.getId());

                                if (!Helper.insert(stock, "Inventories", context)) {
                                    System.out.println("insert Item into Inventory failed!");
                                }

                                System.out.println(stock.getId());
                                
                            }
                            
                            
                            
                        }
                        catch (Exception e) {
                            System.out.println("Incorrect type... skipping..");
                        }

                    }
                }
                catch (Exception e) {
                    System.out.println("Incorrect type... skipping..");
                }

            }
            System.out.print("Insert finished");

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
