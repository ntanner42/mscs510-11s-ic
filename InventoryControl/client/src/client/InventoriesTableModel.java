/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author Neal
 */
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author Neal
 */
public class InventoriesTableModel extends AbstractTableModel
{
    private ArrayList<Inventory> inventories = new ArrayList<Inventory>( );
    private HashMap<Integer,Inventory> dirty = new HashMap<Integer,Inventory>( );

    private static String[] columnNames =
    {
        "Id",
        "Updated",
        "Name",
        "Description"
    };

    public boolean add(Inventory inventoryToAdd)
    {
        WorkletContext context = WorkletContext.getInstance();

        return Helper.insert(inventoryToAdd, "Inventories",context);
    }

    public boolean delete(int row)
    {
        WorkletContext context = WorkletContext.getInstance();

        Inventory inventoryToDelete = inventories.get(row);

        return Helper.delete(inventoryToDelete, "Inventories", context);
    }

    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public int getRowCount()
    {
        return inventories.size();
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

    public Object getValueAt(int row, int col)
    {
        Object valueToReturn = null;

        
        try{
            Inventory inventory = inventories.get(row);
            
            if(col == 0)
            {
                String indicator = "";

                Integer id = inventory.getId();

                if(dirty.get(id) != null)
                {
                    indicator = "* ";
                }

                valueToReturn = id + indicator;
            }
            else if(col == 1)
            {
                valueToReturn = inventory.getUpdateDate().toString();
            }
            else if(col == 2)
            {
                valueToReturn = inventory.getName();
            }
            else if(col == 3)
            {
                valueToReturn = inventory.getDescription();        
            }
        }
        catch (Exception e) {
            System.out.println("Incorrect Type..");
            return null;
        }
        

        

        return valueToReturn;
    }

    @Override
    public void setValueAt(Object value, int row, int col)
    {
        Inventory inventory = getRow(row);

        if(col == 2)
        {
            inventory.setName((String)value);
        }
        else if(col == 3)
        {
            inventory.setDescription((String)value);
        }

        dirty.put(inventory.getId(),inventory);

        this.fireTableDataChanged();
    }

    public void refresh()
    {
        WorkletContext context = WorkletContext.getInstance();

        inventories = Helper.query("Inventories", "/list", context);

        dirty.clear();

        this.fireTableDataChanged();
    }

    public boolean update()
    {
        // Saves the changes to the table
        boolean updateComplete = true;

        WorkletContext context = WorkletContext.getInstance();

        for(Integer id : dirty.keySet()) {
            Inventory inventory = dirty.get(id);

            if(!Helper.insert(inventory, "Inventories",context))
            {
                updateComplete = false;
                break;
            }
        }

        return updateComplete;
    }

    public Inventory getRow(int row)
    {
        return inventories.get(row);
    }

    public HashMap<Integer,Inventory> getDirty()
    {
        return dirty;
    }
}
