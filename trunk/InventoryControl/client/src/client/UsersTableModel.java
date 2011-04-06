/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.workplicity.entry.WorkSlate;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author vageesh & Krishnan
 */
public class UsersTableModel extends AbstractTableModel {

    //private ArrayList<WorkSlate> workSlates = new ArrayList<WorkSlate>( );
    //private HashMap<Integer,WorkSlate> dirty = new HashMap<Integer,WorkSlate>( );
    private static String[] columnNames = {
        "Id",
        "Name",
        "Address",
        "Phone#"
    };

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }


    public int getRowCount() {
        return 1;//--Hardcoded the num of rows to 1
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    //--Made the cells editable except the userID
    public boolean isCellEditable(int row, int col) {
        if(col == 2)
            return true;
        if(col == 3)
            return true;
        if(col == 4)
            return true;

        return false;
    }

    public Object getValueAt(int row, int col) {

        switch (col)//--Hardcoded the return values
        {
            case 0:
                return 102;
            case 1:
                return "John Doe";
            case 2:
                return "43 Delafield street";
            case 3:
                 return "8455753000";

        }
        return null;

        
    }

   public void refresh() {
        
        this.fireTableDataChanged();
    }

    public void getRow(int row) {
       // return workSlates.get(row);
    }
    /*Dirty check to be implemented
     public HashMap<Integer,WorkSlate> getDirty() {
        return dirty;
    }*/
}
