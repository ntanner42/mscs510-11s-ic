/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.workplicity.elog.entry.ElogUser;
import org.workplicity.util.DateFormatter;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author vageesh & Krishnan
 */
public class UsersTableModel extends AbstractTableModel {

    private ArrayList<ElogUser> users = new ArrayList<ElogUser>( );
    private HashMap<Integer,ElogUser> dirty = new HashMap<Integer,ElogUser>( );
    private static String[] columnNames = {
        "Id",
        "Updated",
        "Username",
        "Password",
        "First name",
        "Last name",
        "Phone #",
        "E-mail"
    };

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }


    public int getRowCount() {
        return users.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    //--Made the cells editable except the userID
    public boolean isCellEditable(int row, int col)
    {
        boolean isEditable = false;
        
        if(col > 1)
        {
            isEditable = true;
        }

        return isEditable;
    }

    @Override
    public void setValueAt(Object value, int row, int col)
    {
        ElogUser user = getRow(row);

        switch(col)
        {
            case 2:
                user.setLogname((String) value);
                break;
            case 3:
                user.setPassword((String) value);
                break;
            case 4:
                user.setFirstName((String)value);
                break;
            case 5:
                user.setLastName((String)value);
                break;
            case 6:
                user.setPhone((String)value);
                break;
            case 7:
                user.setEmail((String) value);
                break;
        }

        dirty.put(user.getId(),user);

        this.fireTableDataChanged();
    }


    public Object getValueAt(int rowIndex, int columnIndex)
    {
        ElogUser user = users.get(rowIndex);

        try
        {
            switch(columnIndex)
            {
                case 0:
                    String indicator = "";
                    Integer id = user.getId();
                    if(dirty.get(id) != null)
                    indicator = "* ";
                    return id + indicator;
                case 1:
                    return DateFormatter.toString(user.getUpdateDate());
                case 2:
                    return user.getLogname();
                case 3:
                    return user.getPassword();
                case 4:
                    return user.getFirstName();
                case 5:
                    return user.getLastName();
                case 6:
                    return user.getPhone();
                case 7:
                    return user.getEmail();
            }
        }
        catch(Exception e) {
            System.out.println("user did not render..");
        }

        return null;
    }

    public ElogUser getRow(int row)
    {
        ElogUser userToReturn = null;

        if(row < 0 || row >= users.size())
        {
            userToReturn = null;
        }
        else
        {
            userToReturn = users.get(row);
        }

        return userToReturn;
    }
    

   public void refresh()
   {
       try
       {
            WorkletContext context = WorkletContext.getInstance();

            NetTask.setUrlBase("http://localhost:8080/netprevayle/task");

            if(!Helper.login("admin","gaze11e",context))
                throw new Exception("login failed");
            
            String criteria = "/list";

            users = Helper.query("Accounts", criteria, context);

            dirty.clear();

            this.fireTableDataChanged();
        }
        catch (Exception e)
        {
            System.out.println("User refresh failed..");
        }
    }

    public boolean add(ElogUser userToAdd)
    {
        WorkletContext context = WorkletContext.getInstance();

        return Helper.insert(userToAdd, "Accounts", context);
    }

    public boolean delete(int row)
    {
        WorkletContext context = WorkletContext.getInstance();

        ElogUser userToDelete = users.get(row);

        return Helper.delete(userToDelete, "Accounts", context);
    }

    public HashMap<Integer,ElogUser> getDirty()
    {
        return dirty;
    }
}
