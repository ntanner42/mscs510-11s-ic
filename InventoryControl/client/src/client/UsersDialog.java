/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UsersDialog.java
 *
 * Created on Mar 27, 2011, 12:07:43 PM
 */

package client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;
import org.workplicity.elog.entry.ElogUser;

import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author Krishnan & Vageesh
 */
public class UsersDialog extends javax.swing.JDialog
{
    private ArrayList<ElogUser> Users = new ArrayList<ElogUser>( );

    /** Creates new form UsersDialog */
    public UsersDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        init();
        initUserTable();
    }

    // --Code for setting look&feel and centering to Users dialog
    private void init()
    {
        this.setTitle("Users");
        this.setLocationRelativeTo(null);

        try
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {

        }
    }

    // --Code for setting the width of the usersTable columns
    private void initUserTable()
    {
        usersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        final int[] WIDTHS = {
            42,     // User ID
            80,    // Updated
            80,     // Username
            80,     // Password
            80,     // First name
            80,     // Last name
            80,     // Phone #
            110      // E-mail address
        };

        for(int i = 0 ; i<WIDTHS.length; i++)
        {
            TableColumn col = usersTable.getColumnModel().getColumn(i);
            col.setPreferredWidth(WIDTHS[i]);

        }
        usersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    final int row = target.getSelectedRow();

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                           // call method to perform task on double click
                        }
                    });
                }
            }
        });
        
        UsersTableModel model = (UsersTableModel) usersTable.getModel();

        model.refresh();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        usersTable.setModel(new UsersTableModel());
        usersTable.setName("usersTable"); // NOI18N
        jScrollPane1.setViewportView(usersTable);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(client.MainFrame.class).getContext().getResourceMap(UsersDialog.class);
        refreshButton.setIcon(resourceMap.getIcon("refreshButton.icon")); // NOI18N
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        doneButton.setText(resourceMap.getString("doneButton.text")); // NOI18N
        doneButton.setName("doneButton"); // NOI18N
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        deleteButton.setIcon(resourceMap.getIcon("deleteButton.icon")); // NOI18N
        deleteButton.setName("deleteButton"); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(client.MainFrame.class).getContext().getActionMap(UsersDialog.class, this);
        addButton.setAction(actionMap.get("click")); // NOI18N
        addButton.setIcon(resourceMap.getIcon("addButton.icon")); // NOI18N
        addButton.setName("addButton"); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 396, Short.MAX_VALUE)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteButton)
                        .addGap(18, 18, 18)
                        .addComponent(doneButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteButton)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(refreshButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        // TODO add your handling code here:
         SwingUtilities.invokeLater(new Runnable() {

           public void run() {

                refreshUser();


             }// end run

        });// end swing utilities

        dispose();
    }//GEN-LAST:event_doneButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Displays add item dialog
                AddUserDialog userDialog =
                        new AddUserDialog( null , true);
                userDialog.setVisible(true);
                userDialog.setTitle("Add new User");

                if(userDialog.addedUser())
                {
                    UsersTableModel model = (UsersTableModel) usersTable.getModel();
                    ElogUser userToAdd = userDialog.newUser();
                    model.add(userToAdd);

                    refreshUser();
                }//end if
            }
        });
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        final UsersDialog frame = this;

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                UsersTableModel model = (UsersTableModel) usersTable.getModel();
                int row = usersTable.getSelectedRow();
                if (row == -1)
                {
                    JOptionPane.showMessageDialog(frame,
                        "Please select a user from the table to delete ",
                        "No selection made",
                        JOptionPane.ERROR_MESSAGE);
                }//end if
                else
                {
                    String msg = "Are you sure you want to delete this user?";

                    int n = JOptionPane.showConfirmDialog(
                            null,
                            msg,
                            "Confirm",
                            JOptionPane.YES_NO_OPTION);

                    if (n == 1)
                    {
                        return;
                    }

                    try
                    {
                        if (model.delete(row))
                        {

                            System.out.print("Delete successful");
                        }
                        else
                        {
                            System.out.print("Delete failed!");
                        }
                    }
                    catch(Exception ex)
                    {

                    }
                 }

                 model.refresh();
            }
          });        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
         SwingUtilities.invokeLater(new Runnable()
         {
            public void run()
            {
                refreshUser();
             }// end run
         });// end swing utilities
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void refreshUser()
    {
        final UsersDialog frame = this;

        UsersTableModel model = (UsersTableModel) usersTable.getModel();

        final HashMap<Integer, ElogUser> dirty = model.getDirty();

        if (model.getDirty().isEmpty())
        {
            model.refresh();
        }//end if
        else
        {
            String msg = "Some Users have changed.";
            msg += "\nSave them?";

            int n = JOptionPane.showConfirmDialog(
                        frame,
                        msg,
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);

            if (n == 1)
            {
                model.refresh();
            }//end if
            else
            {
                saveUser();
            }//end else
        }//end else
    }// end refresh users

    private void saveUser()
    {
        final UsersDialog frame = this;

        WorkletContext context = WorkletContext.getInstance();

        UsersTableModel model = (UsersTableModel) usersTable.getModel();
        final HashMap<Integer, ElogUser> dirty = model.getDirty();

        for(Integer id : dirty.keySet())
        {
            ElogUser user = dirty.get(id);

            if (!Helper.insert(user, "Accounts", context))
            {
                JOptionPane.showMessageDialog(frame, "insert Users failed!",
                    "Users", JOptionPane.ERROR_MESSAGE);
            }//end if

            System.out.println(user.getId());
        }// end foreach

        model.refresh();
    }// saveUser

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                try
                {

                   WorkletContext context = WorkletContext.getInstance();
                   NetTask.setUrlBase("http://localhost:8080/netprevayle/task");

                        if(!Helper.login("admin","gaze11e",context))
                            throw new Exception("login failed");

                    String criteria = "/list";
                    //Issuing the query using the helper to the Accounts     repository
                   ArrayList<ElogUser> users = Helper.query("Accounts", criteria, context);
                    if (users == null)
                    {
                        throw new Exception("bad query");
                    }

                    ElogUser user = users.get(0);

                    

                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {

                }
                UsersDialog dialog = new UsersDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables

    private JFrame getFrame() {
         throw new RuntimeException("Compiled Code");
    }
}

   
