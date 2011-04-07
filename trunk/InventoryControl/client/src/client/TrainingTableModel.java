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
public class TrainingTableModel extends AbstractTableModel{

    /**
     * Names of the columns
     */
     private static String[] columnNames = {
        "Training Date",
        "Model id",
        "User",
        "Status",
        "Trainer"
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
                return "ME078";

            case 2:
                return "12,15" ;
            case 3:
                return "Repair" ;
            case 4:
                return "5";

        }

        return null;
    }

     /**
     * Get a training by row;
     * @param row Row
     * @return Training
     */
    //public Training getRow(int row) {
        //if(row < 0 || row >= .size())
            //return null;

        //return .get(row);
    //}

    /**
     * Removes a training from the table
     */
    public void remove(int row) {
        // .remove(row);
    }

    /**
     * Refreshes the table of training;
     */
    public void refresh() {

        this.fireTableDataChanged();
    }

    
}
