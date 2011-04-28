/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.workplicity.inventorycontrol.entry.Location;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SandeepMJ
 */
public class LocationsTableModel extends AbstractTableModel {
    
    private ArrayList<Location> locations = new ArrayList<Location>( );
    private HashMap<Integer,Location> dirty = new HashMap<Integer,Location>( );

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


    public int getRowCount()
    {
        return locations.size();
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


    public Object getValueAt(int row, int col) {
        
        Object valueToReturn = null;
        
        try{
            Location location = locations.get(row);
            
            if(col == 0)
            {
                String indicator = "";

                Integer id = location.getId();

                if(dirty.get(id) != null)
                {
                    indicator = "* ";
        }

                valueToReturn = id + indicator;
            }
            else if(col == 0)
            {
                valueToReturn = location.getId().toString();
            }
            else if(col == 1)
            {
                valueToReturn = location.getName();
            }
            else if(col == 2)
            {
                valueToReturn = "3399 North Road";
            }
            else 
            {
                valueToReturn = "NY";
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
        Location location = getRow(row);

        if(col == 1)
        {
            location.setName((String)value);
        }

        dirty.put(location.getId(),location);

        this.fireTableDataChanged();
    }
    
    public boolean add(Location locationToAdd)
    {
        WorkletContext context = WorkletContext.getInstance();

        return Helper.insert(locationToAdd, "Location",context);
    }

    public boolean delete(int row)
    {
        WorkletContext context = WorkletContext.getInstance();

        Location locationToDelete = locations.get(row);

        return Helper.delete(locationToDelete, "Locactions", context);
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
     
    public void refresh() {

        this.fireTableDataChanged();
    }
     * */
    
    public void refresh()
    {
        WorkletContext context = WorkletContext.getInstance();

        locations = Helper.query("Locations", "/list", context);

        dirty.clear();

        this.fireTableDataChanged();
    }
    
    public Location getRow(int row)
    {
        return locations.get(row);
    }

    public HashMap<Integer,Location> getDirty()
    {
        return dirty;
    }


}
