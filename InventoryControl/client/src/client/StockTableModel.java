/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author SandeepMJ
 */
public class StockTableModel extends AbstractTableModel {

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
        return 1;
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

        switch(columnIndex) {
            case 0:
                return 102 ;

            case 1:
                return "12/04/2010";
                
            case 2:
                return "ASDF65" ;
                
            case 3:
                return "RMA8242";

        }
        
        return null;
    }

    /**
     * Get a stock by row;
     * @param row Row
     * @return Stock
     */
    //public Stock getRow(int row) {
        //if(row < 0 || row >= .size())
            //return null;

        //return .get(row);
    //}

    /**
     * Removes a stock from the table
     */
    public void remove(int row) {
        // .remove(row);
    }

    /**
     * Refreshes the table of stock;
     */
    public void refresh() {

        this.fireTableDataChanged();
    }
   

}
