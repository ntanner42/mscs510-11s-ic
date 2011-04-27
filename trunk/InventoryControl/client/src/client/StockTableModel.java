/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.inventorycontrol.entry.Stock;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SandeepMJ
 */
public class StockTableModel extends AbstractTableModel {



    private ArrayList<Stock> requiredStocks = new ArrayList<Stock>( );
    private HashMap<Integer,Stock> dirty = new HashMap<Integer,Stock>( );
    /**
     * Names of the columns
     */
     private static String[] columnNames = {
        "Id",
        "Updated",
        "Asset tag",
        "RMA #"
    };

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }


    public int getRowCount() {
        return requiredStocks.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

     @Override
    public boolean isCellEditable(int row, int col) {
        if(col == 2)
            return true;
        if(col == 3)
            return true;

        return false;
    }


    public Object getValueAt(int rowIndex, int columnIndex) {

        Stock stock = requiredStocks.get(rowIndex);
        switch(columnIndex) {
            case 0:
                    String indicator = "";
                    Integer id = stock.getId();
                    if(dirty.get(id) != null)
                    indicator = "* ";
                    return id + indicator;
            
            case 1:
                    Date date = stock.getUpdateDate();
                    return date;
                
            case 2:
                    String assetTag = stock.getAssetTag();
                    return assetTag;
                
            case 3:
                    String rmaNumber = stock.getRmaNumber();
                    return rmaNumber;

        }
        
        return null;
    }

    /**
     * Get a stock by row;
     * @param row Row
     * @return Stock
     */
    public Stock getRow(int row) {
        
        if(row < 0 || row >= requiredStocks.size())
            return null;

        return requiredStocks.get(row);
    }

    /**
     * Removes a stock from the table
     */
    public void remove(int row) {
        // .remove(row);
    }

     @Override
    public void setValueAt(Object value, int row, int col) {
        Stock stock = getRow(row);

        switch(col) {
            case 2:
                   stock.setAssetTag((String)value);
                   break;
            case 3:
                   stock.setRmaNumber((String)value);
                   break;
        }

        dirty.put(stock.getId(),stock);

        this.fireTableDataChanged();

//        WorkletContext context = WorkletContext.getInstance();
//
//        Helper.insert(workSlate, NetTask.REPOS_WORKSLATES,context);
////        getRow(row).setName((String)value);
    }



    /**
     * Refreshes the table of stock;
     */
    public void refresh(Inventory inventory,Item item) {

        WorkletContext context = WorkletContext.getInstance();

        String s1 = inventory.getId().toString();
        String s2 = item.getId().toString();

        String criteria = "/list[inventoryId=" + inventory.getId().toString() +"]";


        ArrayList<Item> items = Helper.query("Inventories", criteria, context);


        for(int i=0; i<items.size(); i++) {
               Item item1 = items.get(i);

               if (item1.getId().toString().equals(item.getId().toString()))
               {

                    //query the stock for the item
                    //Print all items in the inventory
                    String criteria3 = "/list[itemId=" + item.getId().toString() + "]";
                    requiredStocks = Helper.query("Inventories", criteria3, context);
                }
        }

        dirty.clear();
        this.fireTableDataChanged();
    }

    

    public HashMap<Integer,Stock> getDirty() {
        return dirty;
    }

}
