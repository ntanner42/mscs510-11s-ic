/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author Neal
 */
public class ItemsTableModel extends AbstractTableModel
{
    private ArrayList<Item> items = new ArrayList<Item>( );
    private HashMap<Integer,Item> dirty = new HashMap<Integer,Item>( );

    private static String[] columnNames =
    {
        "Id",
        "Updated",
        "Model #",
        "Name",
        "Description"
    };

    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public int getRowCount()
    {
        return items.size();
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int row, int col)
    {
        boolean isEditable = false;

        if((col > 1) && (col < columnNames.length))
        {
            isEditable = true;
        }

        return isEditable;
    }

    public Object getValueAt(int row, int col) {
        //WorkSlate workSlate = workSlates.get(row);
        Item item = items.get(row);

        Object valueToReturn = null;

        if(col == 0)
        {
            String indicator = "";

            Integer id = item.getId();

            if(dirty.get(id) != null)
            {
                indicator = "* ";
            }

            valueToReturn = id + indicator;
        }
        else if(col == 1)
        {
            valueToReturn = item.getUpdateDate();
        }
        else if(col == 2)
        {
            valueToReturn = item.getModelNumber();
        }
        else if(col == 3)
        {
            valueToReturn = item.getName();
        }
        else if(col == 4)
        {
            valueToReturn = item.getDescription();
        }

        return valueToReturn;
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
        Item item = getRow(row);

        if(col == 2)
        {
            item.setModelNumber((String)value);
        }
        else if(col == 3)
        {
            item.setName((String)value);
        }
        else if(col == 4)
        {
            item.setDescription((String)value);
        }

        dirty.put(item.getId(),item);

        this.fireTableDataChanged();
    }

    public void refresh() {
        WorkletContext context = WorkletContext.getInstance();

        items = Helper.query("Items", "/list", context);

        dirty.clear();

        this.fireTableDataChanged();
    }

    public Item getRow(int row) {
        return items.get(row);
    }

    public HashMap<Integer,Item> getDirty() {
        return dirty;
    }
}
