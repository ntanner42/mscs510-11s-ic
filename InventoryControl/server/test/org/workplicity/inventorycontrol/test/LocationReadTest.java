/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Date;
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
public class LocationReadTest extends Test {

    public LocationReadTest() {
        super();
        
        try {
            String criteria1 = "/ list";
            //Issuing the query using the helper to the Locations repository
            ArrayList<Location> list = Helper.query("Locations", criteria1, context);
            if (list == null) {
                throw new Exception("bad query");
            }
            for (Location location : list) {
                System.out.println("ID:" + location.getId() + " Name:"
                        + location.getName() + " Created DATE:"
                        + new Date(location.getCreateDate().getTime())
                        + " Updated DATE:"
                        + new Date(location.getUpdateDate().getTime()));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public static void main(String[] args) {
        try {
            LocationReadTest thisTest = new LocationReadTest();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
