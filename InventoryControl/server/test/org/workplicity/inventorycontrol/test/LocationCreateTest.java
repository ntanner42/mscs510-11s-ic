/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.Random;
import org.workplicity.inventorycontrol.entry.Location;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.util.Logger;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SHAN
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
            //Create 100 Locations with random name
            for (int i = 0; i < 100; i++) {
                Location location = new Location();
                Random random = new Random();
                int j = random.nextInt(16) + 65;
                String name = "" + (char) (j + 0) + (char) (j + 1) + (char) (j + 7) + (char) (j + 3)
                        + (char) (j + 4) + (char) (j + 9) + (char) (j + 8) + (char) (j + 2)
                        + (char) (j + 6) + (char) (j + 5) + (char) (j + 10);
                location.setName(name);
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
