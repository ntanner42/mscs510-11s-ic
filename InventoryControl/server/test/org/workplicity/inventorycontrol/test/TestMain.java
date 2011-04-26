/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.test;

/**
 *
 * @author Brian Gormanly
 */
public class TestMain extends Test {
    public TestMain() {
        super();
        
        // delete all existing data
        LocationDeleteTest ldt = new LocationDeleteTest();
        InventoryDeleteTest idt = new InventoryDeleteTest();
        ItemDeleteTest itdt = new ItemDeleteTest();
        StockDeleteTest sdt = new StockDeleteTest();
        TrainingDeleteTest tdt = new TrainingDeleteTest();
        OperAudDeleteTest odt = new OperAudDeleteTest();
        
        // create new data
        LocationCreateTest lct = new LocationCreateTest();
        InventoryCreateTest ict = new InventoryCreateTest();
        ItemCreateTest itct = new ItemCreateTest();
        StockCreateTest sct = new StockCreateTest();
        
        // create Training
        TrainingCreateTest tct = new TrainingCreateTest();
        
        // create Operation Audit
        OperAudCreateTest oct = new OperAudCreateTest();
        
        // show some output
        LocationReadTest lrt = new LocationReadTest();
        StockReadTest srt = new StockReadTest();
        TrainingReadTest trt = new TrainingReadTest();
        OperAudReadTest ort = new OperAudReadTest();
        
    }
    
    public static void main(String[] args) {
        TestMain mainRun = new TestMain();
    }
}
