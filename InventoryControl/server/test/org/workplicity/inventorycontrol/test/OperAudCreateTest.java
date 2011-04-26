/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.inventorycontrol.entry.Item.Status;
import org.workplicity.inventorycontrol.entry.Location;
import org.workplicity.inventorycontrol.entry.OperationAudit;
import org.workplicity.util.Helper;

/**
 *
 * @author Brian Gormanly
 */
public class OperAudCreateTest extends Test {

    public OperAudCreateTest() {
        super();
        
        try {
            
            Item.Status[] status = {Status.REPAIR, Status.IN_STOCK, Status.IN_SERVICE, Status.IN_SERVICE, Status.IN_SERVICE, Status.IN_SERVICE, Status.IN_SERVICE, Status.IN_STOCK, Status.IN_SERVICE};
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            Date date[] = {sdf.parse("03-FEB-2011"), sdf.parse("168-FEB-2011"), sdf.parse("06-MAR-2011"), sdf.parse("08-MAR-2011"), sdf.parse("23-MAR-2011"), sdf.parse("05-APR-2011"), sdf.parse("09-APR-2011"), sdf.parse("11-APR-2011"), sdf.parse("18-APR-2011")};
            Integer[] model = new Integer[date.length];
    
            // get models ids
            String locations[] = {"Donnelly Hall", "Hancock Hall", "Fontaine Hall", "Lowell Thomas", "Dyson Center", "Cannavino Library", "McCann Center", "Lowell Thomas", "Dyson Center"};
            Integer locationIds[] = new Integer[9];
            
            for(int i=0; i< 9; i++) {  
                locationIds[i] = this.getLocationbyName(locations[i]);
            }
            
            
            
            
            for(int i=0; i<date.length; i++) {
                OperationAudit oa = new OperationAudit();
            
                oa.setStatus(status[i]);
                oa.setStamp(date[i]);
                oa.setLocation(locationIds[i]);
                

                if (!Helper.insert(oa, "Operations", context)) {
                    System.out.print("insert failed!");
                }
            }
            
            
            System.out.print("Insert finished");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        OperAudCreateTest thisTest = new OperAudCreateTest();
        
    }
    
    public Integer getLocationbyName(String name) {
        
        ArrayList<Location> ids = new ArrayList<Location>();
        
        // query for the model 
        if(!name.isEmpty() && name != null) {
            String q1 = "/list[name='" + name + "']";
            ids = Helper.query("Locations", q1, context);
        }   
        Location rItem = ids.get(0);
        return rItem.getId();
    }
}
