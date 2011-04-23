/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.List;
import java.util.Random;
import org.workplicity.inventorycontrol.entry.Location;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.util.Logger;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SHAN
 * @author Brian Gormanly
 */
public class LocationCreateTest {

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
            
            String[] names = {"Donnelly Hall", "Hancock Hall", "Fontaine Hall", "Lowell Thomas", "Dyson Center", "Cannavino Library", "McCann Center"};
            
            for(int i=0; i<names.length; i++) {
                Location location = new Location();
            
                location.setName(names[i]);

                if (!Helper.insert(location, "Locations", context)) {
                    System.out.print("insert failed!");
                }
            }
            
            
            System.out.print("Insert finished");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}