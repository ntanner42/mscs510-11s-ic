/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.workplicity.entry.User;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author vageesh & Krishnan
 */
public class UsersTableModel extends AbstractTableModel {

    private ArrayList<User> Users = new ArrayList<User>( );
    private HashMap<Integer,User> dirty = new HashMap<Integer,User>( );
    private static String[] columnNames = {
        "Id",
        "UpdateDate",
        "FirstName",
        "LastName",
        "Phone#"
    };

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }


    public int getRowCount() {
        return Users.size();
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

    @Override
     public void setValueAt(Object value, int row, int col) {
        User user = getRow(row);

        switch(col) {
            case 2:
                   user.setFirstName((String)value);
                   break;
            case 3:
                   user.setLastName((String)value);
                   break;
            case 4:
                   user.setPhone((String)value);
                   break;
        }

        dirty.put(user.getId(),user);

        this.fireTableDataChanged();

//        WorkletContext context = WorkletContext.getInstance();
//
//        Helper.insert(workSlate, NetTask.REPOS_WORKSLATES,context);
////        getRow(row).setName((String)value);
    }


    public Object getValueAt(int rowIndex, int columnIndex) {

        User user = Users.get(rowIndex);

        try {
            switch(columnIndex) {
                case 0:
                        String indicator = "";
                        Integer id = user.getId();
                        if(dirty.get(id) != null)
                        indicator = "* ";
                        return id + indicator;

                case 1:
                        Date date = user.getUpdateDate();
                        return date;

                case 2:
                        String Firstname = user.getLogname();
                        return Firstname;
                case 3:
                        String Lastname = user.getLastName();
                        return Lastname;

                case 4:
                        String phoneNumber = user.getPhone();
                        return phoneNumber;

            }
        }
        catch(Exception e) {
            System.out.println("user did not render..");
        }

        return null;
    }

    public User getRow(int row) {

        if(row < 0 || row >= Users .size())
            return null;

        return Users.get(row);
    }
    

   public void refresh() {



        
       try {

            WorkletContext context = WorkletContext.getInstance();


            NetTask.setUrlBase("http://localhost:8080/netprevayle/task");

            if(!Helper.login("admin","gaze11e",context))
                throw new Exception("login failed");

            
            String criteria = "/list";


            Users = Helper.query("Accounts", criteria, context);


            dirty.clear();
            this.fireTableDataChanged();

        }
        catch (Exception e) {
            System.out.println("Stock refresh failed..");
        }

    }

  
    
     public HashMap<Integer,User> getDirty() {
        return dirty;
    }
}
