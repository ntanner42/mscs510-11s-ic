/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Date;
import org.workplicity.inventorycontrol.entry.Location;
import org.workplicity.inventorycontrol.entry.OperationAudit;
import org.workplicity.util.Helper;

/**
 *
 * @author Brian Gormanly
 */
public class OperAudReadTest extends Test {

    public OperAudReadTest() {
        super();
        
        try {
            String criteria1 = "/ list";
            //Issuing the query using the helper to the Locations repository
            ArrayList<OperationAudit> list = Helper.query("Operations", criteria1, context);
            if (list == null) {
                throw new Exception("bad query");
            }
            for (OperationAudit oa : list) {
                System.out.println("ID:" + oa.getId() + " Status:"
                        + oa.getStatus() + " DATE:"
                        + oa.getStamp()
                        + " Location Id:"
                        + oa.getLocation());
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public static void main(String[] args) {
        try {
            OperAudReadTest thisTest = new OperAudReadTest();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
