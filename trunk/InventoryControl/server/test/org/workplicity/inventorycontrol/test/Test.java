/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.util.Logger;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author Brian Gormanly
 * 
 * Master Test class that carries base functions and members for all children -test
 * 
 */
public abstract class Test {
    /**
     * This constant is the URL to reach the Netprevayle server.
     */
    protected final static String URL_BASE = "http://localhost:8080/netprevayle/task";
    /**
     * This is the worklet context to utilize the Helper pattern.
     */
    protected static WorkletContext context = WorkletContext.getInstance();
    
    public Test() {
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
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
