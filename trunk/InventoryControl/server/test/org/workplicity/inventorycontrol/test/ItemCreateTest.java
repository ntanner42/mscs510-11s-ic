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
 * @author SHAN
 */
public class ItemCreateTest {

    /**
     * This constant is the URL to reach the Netprevayle server.
     */
    private final static String URL_BASE =
            "http://localhost:8080/netprevayle/task";
    /**
     * This is the worklet context to utilize the Helper pattern.
     */
    private static WorkletContext context = WorkletContext.getInstance();

    /**
     * NetTest entry point for the JVM
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Disable logging which goes to the console.
            Logger.setEnabled(false);

            // Set the host to use.
            NetTask.setUrlBase(URL_BASE);

            // Login using the admin account with the default
            // log name and password
            if (!Helper.login("admin", "gaze11e", context)) {
                throw new Exception("login failed");
            }
            String criteria1 = "/ list";
            //Issuing the query using the helper to the accounts repository
            ArrayList<Inventory> list1 =
                    Helper.query("Inventories", criteria1, context);
            //Create 100 random Items  and add to each Inventory
            for (Inventory inventory : list1) {
                for (int j = 0; j < 100; j++) {
                    Random random = new Random();
                    String title = "" + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65);
                    Item item = new Item(title);
                   String name = "" + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65)
                            + (char) (random.nextInt(26) + 65);
                    item.setName(name);
                    inventory.insert(item);
                }
                if (!Helper.insert(inventory, "Inventories", context)) {
                    System.out.println("insert Item into Inventory failed!");
                }
            }
            System.out.print("Insert finished");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
