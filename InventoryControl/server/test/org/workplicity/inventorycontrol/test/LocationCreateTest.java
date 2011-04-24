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
public class LocationCreateTest extends Test {

    public LocationCreateTest() {
        super();
        
        try {
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
    
    public static void main(String[] args) {
        LocationCreateTest thisTest = new LocationCreateTest();
        
    }
}
