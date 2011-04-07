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
public class LocationsTableModel extends AbstractTableModel {

    /**
     * Names of the columns
     */
    private static String[] columnNames = {
        "Id",
        "Name",
        "Address",
        "State"
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
        if(col == 4)
            return true;

        return false;
    }


    public Object getValueAt(int rowIndex, int columnIndex) {

        switch(columnIndex) {
            case 0:
                return 102 ;

            case 1:
                return "Marist College";

            case 2:
                return "3399 North Road" ;

            case 3:
                return "NY";

        }

        return null;
    }

   /**
     * Get a location by row;
     * @param row Row
     * @return Location
     */
    //public Location getRow(int row) {
        //if(row < 0 || row >= .size())
            //return null;

        //return .get(row);
    //}

    /**
     * Removes a location from the table
     */
    public void remove(int row) {
        // .remove(row);
    }

    /**
     * Refreshes the table of location;
     */
    public void refresh() {

        this.fireTableDataChanged();
    }


}
