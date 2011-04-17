/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.inventorycontrol.entry.Stock;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.util.Logger;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SHAN
 */
public class InventoryReadTest {

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
            String criteria1 = "/list";
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Inventory> list =
                    Helper.query("Inventories", criteria1, context);
            if (list == null) {
                throw new Exception("bad query");
            }
            //Print all inventories
            for (Inventory inventory : list) {

                System.out.println("ID:" + inventory.getId() + " Name:"
                        + inventory.getName() + " Title:"
                        + inventory.getTitle() + " Items List:"
                        + inventory.getList() + " Stocklist:"
                        + inventory.getList() + " Created DATE:"
                        + new Date(inventory.getCreateDate().getTime())
                        + " Updated DATE:"
                        + new Date(inventory.getUpdateDate().getTime()));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
