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
public class OrderingTableModel extends AbstractTableModel {

    /**
     * Names of the columns
     */
    private static String[] columnNames = {
        "OrderDate",
        "Size",
        "PO#",
        "Order#"
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
                return "10/02/2011" ;

            case 1:
                return 12;

            case 2:
                return "SMF65" ;
                
            case 3:
                return "C3PO84" ;

        }

        return null;
    }

    /**
     * Get a order by row;
     * @param row Row
     * @return Order
     */
    //public Order getRow(int row) {
        //if(row < 0 || row >= .size())
            //return null;

        //return .get(row);
    //}

    /**
     * Removes a order from the table
     */

    public void remove(int row) {
        // .remove(row);
    }

     /**
     * Refreshes a order table
     */
    public void refresh() {

        this.fireTableDataChanged();
    }


}
