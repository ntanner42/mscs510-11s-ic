/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.inventorycontrol.entry.Location;
import org.workplicity.inventorycontrol.entry.Training;
import org.workplicity.inventorycontrol.entry.Training.Status;
import org.workplicity.util.Helper;

/**
 *
 * @author Brian Gormanly
 */
public class TrainingCreateTest extends Test {

    public TrainingCreateTest() {
        super();
        
        try {
            
            Training.Status[] status = {Status.COMPLETE, Status.ONGOING, Status.PENDING, Status.COMPLETE, Status.PENDING, Status.PENDING};
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            Date date[] = {sdf.parse("01-MAR-2011"), sdf.parse("05-MAR-2011"), sdf.parse("23-MAR-2011"), sdf.parse("05-APR-2011"), sdf.parse("09-APR-2011"), sdf.parse("11-APR-2011")};
            String[] trainer = {"Jakson, Mary", "Simpson, Homer", "Bush, George", "Smith, Simba", "Jones, Jasmine", "Potter, Harry"};
            Integer[] model = new Integer[trainer.length];
    
            // get models ids
            String mods[] = {"21K3", "85541", "ABC123", "EEB2333", "232BC", "EEB2333"};
            
            for(int i=0; i< 6; i++) {  
                model[i] = this.getIdbyModel(mods[i]);
            }
            
            
            
            
            for(int i=0; i<trainer.length; i++) {
                Training training = new Training();
            
                training.setStatus(status[i]);
                training.setDate(date[i]);
                training.setTrainer(trainer[i]);
                training.setModelId(model[i]);
                training.setUserId(1);
                

                if (!Helper.insert(training, "Trainings", context)) {
                    System.out.print("insert failed!");
                }
            }
            
            
            System.out.print("Insert finished");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        TrainingCreateTest thisTest = new TrainingCreateTest();
        
    }
    
    public Integer getIdbyModel(String model) {
        
        ArrayList<Item> ids = new ArrayList<Item>();
        
        // query for the model 
        if(!model.isEmpty() && model != null) {
            String q1 = "/list[modelNumber='" + model + "']";
            ids = Helper.query("Inventories", q1, context);
        }   
        Item rItem = ids.get(0);
        return rItem.getId();
    }
}
