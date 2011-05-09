/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.workplicity.inventorycontrol.entry.Location;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author Harish Giriraju
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
    };

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }


    public int getRowCount(){
        return locations.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

     @Override
    public boolean isCellEditable(int row, int col) {
        if(col == 1)
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
            
        }
        catch (Exception e) {
            System.out.println("Incorrect Type..");
            return null;
        }
        
        return valueToReturn;

    }

     /**
     * Get a locations by row;
     * @param row Row
     * @return locations
     */
    public Location getRow(int row) {

        if(row < 0 || row >= locations.size())
            return null;

        return locations.get(row);
    }

    /**
     * Removes a locations from the table
     */
    public void remove(int row) {
        // .remove(row);
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
    
    //Function to add a row
    public boolean add(Location locationToAdd)
    {
        WorkletContext context = WorkletContext.getInstance();

        return Helper.insert(locationToAdd, "Location",context);
    }

    //Function to delete a row
    public boolean delete(int row)
    {
        WorkletContext context = WorkletContext.getInstance();

        Location locationToDelete = locations.get(row);

        return Helper.delete(locationToDelete, "Locactions", context);
    }

/**
     * Refreshes the table of Locations;
     */
    
    public void refresh()
    {
        WorkletContext context = WorkletContext.getInstance();

           try
           {
               NetTask.setUrlBase("http://localhost:8080/netprevayle/task");

                    if(!Helper.login("admin","gaze11e",context))
                        throw new Exception("login failed");

                String criteria = "/list";


                locations = Helper.query("Locations", criteria, context);

                dirty.clear();
                this.fireTableDataChanged();

        }
         catch(Exception ex){
             
         }

    }



    public HashMap<Integer,Location> getDirty() {
        return dirty;
    }

}
