/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.util.ArrayList;
import java.util.Date;
import org.workplicity.inventorycontrol.entry.Location;
import org.workplicity.inventorycontrol.entry.Training;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.util.Logger;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author Brian Gormanly
 */
public class TrainingReadTest extends Test {

    public TrainingReadTest() {
        super();
        
        try {
            String criteria1 = "/ list";
            //Issuing the query using the helper to the Locations repository
            ArrayList<Training> list = Helper.query("Trainings", criteria1, context);
            if (list == null) {
                throw new Exception("bad query");
            }
            for (Training training : list) {
                System.out.println("Id:" + training.getId() + " Status:"
                        + training.getStatus() + " Model:"
                        + training.getModelId()
                        + " User: " + training.getUserId()
                        + " Trainer:" + training.getTrainer());
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public static void main(String[] args) {
        try {
            TrainingReadTest thisTest = new TrainingReadTest();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
