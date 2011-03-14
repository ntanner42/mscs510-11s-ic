/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.workplicity.inventorycontrol.desktop.stockhandler;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Neal
 */

public class StockEditorTableModel extends AbstractTableModel
{
    private String[] columnNames = {"Property Name","Value"};
    private String[] rowNames = {"Stock ID",
                                "Item ID",
                                "Part Number (required)",
                                "Serial Number (required)",
                                "Asset Tag (required)",
                                "RMA Number (Required)"};

    private Object[][] data = new Object[rowNames.length][columnNames.length];
    private boolean isEditable = false;

    public StockEditorTableModel()
    {
        setRowNames();
    }//end default constructor

    public StockEditorTableModel(boolean isEditable)
    {
        this.isEditable = isEditable;
        setRowNames();
    }//end constructor

    public int getColumnCount()
    {
        return columnNames.length;
    }//end getColumnCount

    @Override public String getColumnName(int col) {
        return columnNames[col];
    }

    public int getRowCount()
    {
        return rowNames.length;
    }//end getRowCount

    public Object getValueAt(int row, int column)
    {
        return data[row][column];
    }//end getValueAt

    @Override public boolean isCellEditable(int row, int column)
    {
        boolean isCellEditable = false;

        if(!isEditable)
        {
            isCellEditable = false;
        }//end if
        else
        {
            if(column == 0)
            {
                isCellEditable = false;
            }//end if
            else
            {
                // Column is 1
                if(row == 0)
                {
                    isCellEditable = false;
                }//end if
                else
                {
                    isCellEditable = true;
                }
            }//end else
        }//end else

        return isCellEditable;
    }//end isCellEditable

    @Override public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        //fireTableCellUpdated(row, col);
    }//end setValueAt

    private void setRowNames()
    {
        for(int i = 0; i < rowNames.length; i++)
        {
            data[i][0] = rowNames[i];
        }//end for
    }//end setRowNames
}//end StableEditorTableModel