/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.util.DateFormatter;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author Neal
 * @author Brian Gormanly
 */
public class ItemsTableModel extends AbstractTableModel
{
    private ArrayList<Item> items = new ArrayList<Item>( );
    private HashMap<Integer,Item> dirty = new HashMap<Integer,Item>( );
    private Inventory inventory = null;

    private static String[] columnNames =
    {
        "Id",
        "Updated",
        "Model #",
        "Name",
        "Description"
    };

    public boolean add(Item itemToAdd)
    {
        return items.add(itemToAdd);

        //return this.update();
    }

    public boolean delete(int row)
    {
        
        
        WorkletContext context = WorkletContext.getInstance();

        Item itemToDelete = items.get(row);
        items.remove(row);

        return Helper.delete(itemToDelete, "Inventories", context);

    }

    public boolean update(int row, Inventory inventory)
    {
        WorkletContext context = WorkletContext.getInstance();
        
        Item item = items.get(row);
        
        inventory.insert(item);

        return Helper.insert(item, "Inventories", context);

    }

    public Inventory getInventory()
    {
        return inventory;
    }
    
    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }
    
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

    public Object getValueAt(int row, int col)
    {
        Item item = items.get(row);

        Object valueToReturn = null;

        try {
        
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
                valueToReturn = DateFormatter.toString(item.getUpdateDate());
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

    public void refresh()
    {
        WorkletContext context = WorkletContext.getInstance();

        int id = inventory.getId();

        // Get the query
        ArrayList<Inventory> list = Helper.query("Inventories", "/list [ id = '" + id + "' ]", context);

        //get the inventory
        if (list.size() > 0)
        {
            inventory = list.get(0);
            
            String criteria2 = "/list[inventoryId=" + inventory.getId().toString() + "]";
            items = Helper.query("Inventories", criteria2, context);

            //items = new ArrayList<Item>(inventory.getList());

            dirty.clear();

            this.fireTableDataChanged();
        }//end if
    }

    public Item getRow(int row)
    {
        return items.get(row);
    }

    public HashMap<Integer,Item> getDirty()
    {
        return dirty;
    }
}
