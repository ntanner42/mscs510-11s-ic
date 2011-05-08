/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.util.DateFormatter;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author Neal
 * @author Brian Gormamnly
 */
public class InventoriesTableModel extends AbstractTableModel
{
    private Inventory inventory = null;
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
        
        boolean success = Helper.insert(inventoryToAdd, "Inventories",context);
        System.out.println("Insert of " + inventoryToAdd.getName() + " successful! " + inventoryToAdd.getId());
        
        dirty.put(inventoryToAdd.getId(),inventoryToAdd);

        this.fireTableDataChanged();
        

        return success;
    }

    public boolean delete(int row)
    {
        WorkletContext context = WorkletContext.getInstance();

        Inventory inventoryToDelete = inventories.get(row);
        inventories.remove(row);

        Helper.delete(inventoryToDelete, "Inventories", context);
        
        dirty.put(inventoryToDelete.getId(),inventoryToDelete);

        this.fireTableDataChanged();
        
        return true;
       
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
                valueToReturn = DateFormatter.toString(inventory.getUpdateDate());
            }
            else if(col == 2)
            {
                valueToReturn = inventory.getName();
            }
            else if(col == 3)
            {
                valueToReturn = inventory.getDescription();        
            }
            
            //System.out.println("success .. " + inventory.getId());
        }
        catch (Exception e) {
            System.out.println(e);
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

        try {
            inventories = Helper.query("Inventories", "/list", context);
        }
        catch(Exception e) {
            System.out.println("not an inventory");
        }
        
        // filter out the non-inventory
        try {
            for(int i=0; i<inventories.size(); i++) {
                try {
                    Inventory thisInvetory = inventories.get(i);
                }
                catch(Exception e) {
                    System.out.println("not an inventory");
                    inventories.remove(inventories.get(i));
                    i--;
                }
            }
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("The array shrunk and needed to abort");
        }
        
        if (inventories.size() > 0) {

            dirty.clear();

            this.fireTableDataChanged();
        
        }
        
        

        
    }

    public boolean update(int row)
    {
        
        WorkletContext context = WorkletContext.getInstance();
        
        Inventory inventory = inventories.get(row);

        return Helper.insert(inventory, "Inventories", context);

    }

    public Inventory getRow(int row)
    {
        return inventories.get(row);
    }

    public HashMap<Integer,Inventory> getDirty()
    {
        return dirty;
    }
    
    public Inventory getInventory()
    {
        return inventory;
    }
    
    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }
}
