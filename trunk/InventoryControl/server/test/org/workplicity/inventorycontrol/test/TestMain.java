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
        
        // create new data
        LocationCreateTest lct = new LocationCreateTest();
        InventoryCreateTest ict = new InventoryCreateTest();
        ItemCreateTest itct = new ItemCreateTest();
        StockCreateTest sct = new StockCreateTest();
        
        // show some output
        LocationReadTest lrt = new LocationReadTest();
        StockReadTest srt = new StockReadTest();
        
    }
    
    public static void main(String[] args) {
        TestMain mainRun = new TestMain();
    }
}
