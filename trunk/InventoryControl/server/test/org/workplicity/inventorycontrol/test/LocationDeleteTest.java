/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Random;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Location;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.util.Logger;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author Brian Gormanly
 */
public class LocationDeleteTest extends Test {

    public LocationDeleteTest() {
        super();
        
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
            //Issuing the query using the helper to the Inventories repository
            ArrayList<Location> list = Helper.query("Locations", criteria1, context);
            //deleting all inventories
            for (Location locations : list) {
                if (!Helper.delete(locations, "Locations", context)) {
                    System.out.print("Delete failed!");
                }
            }
            System.out.print("Delete finished");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public static void main(String[] args) {
        LocationDeleteTest thisTest = new LocationDeleteTest();
        
    }
}
