/*
 * MainFrameView.java
 * @author Brian Gormanly
 * 
 */

package client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 * The application's main frame.
 */
public class MainFrameView extends FrameView {
    private static WorkletContext context = WorkletContext.getInstance();

    public MainFrameView(SingleFrameApplication app) {
        super(app);

        // Attempt to set the appearance to the system default
        try
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex)
        {
            System.out.println("Failed to set the look and feel.");
        }

        initComponents();
        initTableEditor();
        initInventoriesTable();

        login();
        
        refresh();

    }

    private void initTableEditor()
    {
        inventoriesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    //int column = target.getSelectedColumn();
                    editRequest(row);
                }
            }
        });
    }

    private void initInventoriesTable()
    {
        // Fix the column widths
        inventoriesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        final int[] WIDTHS = {
             40,  // Id
             80,  // Updated
            180,  // Name
            314,  // Description
        };

        for(int i=0; i < WIDTHS.length; i++)
        {
            TableColumn col = inventoriesTable.getColumnModel().getColumn(i);

            col.setPreferredWidth(WIDTHS[i]);
        }
    }

    private void login()
    {
       try {
            NetTask.setUrlBase("http://localhost:8080/netprevayle/task");

            if(!Helper.login("admin","gaze11e",context))
                throw new Exception("login failed");

            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Logger.log(e.toString());
        }
    }

   private void editRequest(int row)
   {
        InventoriesTableModel model = (InventoriesTableModel) inventoriesTable.getModel();

        Inventory inventory = model.getRow(row);

        System.out.println(inventory);
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = MainFrame.getApplication().getMainFrame();
            aboutBox = new MainFrameAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        MainFrame.getApplication().show(aboutBox);
    }
    
    private void refresh() {
        
        final JFrame frame = this.getFrame();

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                InventoriesTableModel model = (InventoriesTableModel) inventoriesTable.getModel();

                final HashMap<Integer, Inventory> dirty = model.getDirty();

                if (model.getDirty().isEmpty()) {
                    model.refresh();
                    return;
                }

                String msg = "Some inventories have changed.";
                msg += "\nSave them?";

                int n = JOptionPane.showConfirmDialog(
                        frame,
                        msg,
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);

                if (n == 1) {
                    model.refresh();
                    return;
                }
                
                int row = inventoriesTable.getSelectedRow();
                
                boolean updateSuccessful = model.update(row);

                if(!updateSuccessful)
                {
                    JOptionPane.showMessageDialog(frame, "Saved failed!",
                        "Inventory Control - Inventories",
                        JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    model.refresh();
                }
            }
        });
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inventoriesTable = new javax.swing.JTable();
        byeButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        drillButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        adminMenu = new javax.swing.JMenu();
        locationsMenuItem = new javax.swing.JMenuItem();
        usersMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        inventoriesTable.setModel(new InventoriesTableModel());
        inventoriesTable.setName("inventoriesTable"); // NOI18N
        jScrollPane1.setViewportView(inventoriesTable);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(client.MainFrame.class).getContext().getResourceMap(MainFrameView.class);
        byeButton.setIcon(resourceMap.getIcon("byeButton.icon")); // NOI18N
        byeButton.setText(resourceMap.getString("byeButton.text")); // NOI18N
        byeButton.setName("byeButton"); // NOI18N
        byeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byeButtonActionPerformed(evt);
            }
        });

        refreshButton.setIcon(resourceMap.getIcon("refreshButton.icon")); // NOI18N
        refreshButton.setText(resourceMap.getString("refreshButton.text")); // NOI18N
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        deleteButton.setIcon(resourceMap.getIcon("deleteButton.icon")); // NOI18N
        deleteButton.setText(resourceMap.getString("deleteButton.text")); // NOI18N
        deleteButton.setName("deleteButton"); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addButton.setIcon(resourceMap.getIcon("addButton.icon")); // NOI18N
        addButton.setText(resourceMap.getString("addButton.text")); // NOI18N
        addButton.setName("addButton"); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        drillButton.setIcon(resourceMap.getIcon("drillButton.icon")); // NOI18N
        drillButton.setText(resourceMap.getString("drillButton.text")); // NOI18N
        drillButton.setName("drillButton"); // NOI18N
        drillButton.setPreferredSize(new java.awt.Dimension(49, 25));
        drillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(drillButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteButton)
                        .addGap(18, 18, 18)
                        .addComponent(byeButton)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(drillButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(byeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(client.MainFrame.class).getContext().getActionMap(MainFrameView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        adminMenu.setText(resourceMap.getString("adminMenu.text")); // NOI18N

        locationsMenuItem.setText(resourceMap.getString("locationsMenuItem.text")); // NOI18N
        locationsMenuItem.setName("locationsMenuItem"); // NOI18N
        locationsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationsMenuItemActionPerformed(evt);
            }
        });
        adminMenu.add(locationsMenuItem);

        usersMenuItem.setText(resourceMap.getString("usersMenuItem.text")); // NOI18N
        usersMenuItem.setName("usersMenuItem"); // NOI18N
        usersMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersMenuItemActionPerformed(evt);
            }
        });
        adminMenu.add(usersMenuItem);

        menuBar.add(adminMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        final JFrame frame = this.getFrame();
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Displays add stock dialog
                final Inventory inventory = new Inventory("");
                AddInventoryDialog stockDialog = new AddInventoryDialog(null, inventory, true);
                stockDialog.setVisible(true);
                stockDialog.setTitle("Add new stock");
                
                refresh();
            }
          
        });
    }//GEN-LAST:event_addButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        final JFrame frame = this.getFrame();

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                int row = inventoriesTable.getSelectedRow();

                InventoriesTableModel model = (InventoriesTableModel) inventoriesTable.getModel();
                boolean deleteSuccessful = model.delete(row);

                if(!deleteSuccessful)
                {
                        JOptionPane.showMessageDialog(frame, "Saved failed!",
                            "Inventory Control - Inventories",
                            JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    model.refresh();
                }
                
                refresh();
            }
        });
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void byeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byeButtonActionPerformed
        
        final JFrame frame = this.getFrame();
        
        InventoriesTableModel model = (InventoriesTableModel) inventoriesTable.getModel();

        final HashMap<Integer, Inventory> dirty = model.getDirty();

        if (model.getDirty().isEmpty()) {
            model.refresh();
            this.getFrame().dispose();
            return;
        }

        String msg = "Some inventories have changed.";
        msg += "\nSave them?";

        int n = JOptionPane.showConfirmDialog(
                frame,
                msg,
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (n == 1) {
            model.refresh();
            this.getFrame().dispose();
            return;
        }

        int row = inventoriesTable.getSelectedRow();

        boolean updateSuccessful = false;
        
        if(row >= 0) {
            updateSuccessful = model.update(row);
        }

        if(!updateSuccessful)
        {
            JOptionPane.showMessageDialog(frame, "Saved failed! Plesae select the row that need to be saved!",
                "Inventory Control - Inventories",
                JOptionPane.ERROR_MESSAGE);
                return;
        }
        else
        {
            model.refresh();
        }
        
        this.getFrame().dispose();
    }//GEN-LAST:event_byeButtonActionPerformed

    private void locationsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationsMenuItemActionPerformed
        LocationsDialog dialog = new LocationsDialog(this.getFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_locationsMenuItemActionPerformed

    private void usersMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersMenuItemActionPerformed
        UsersDialog dialog = new UsersDialog(this.getFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_usersMenuItemActionPerformed

    private void drillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drillButtonActionPerformed
        final JFrame frame = this.getFrame();

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                int row = inventoriesTable.getSelectedRow();

                InventoriesTableModel model = (InventoriesTableModel) inventoriesTable.getModel();

                Inventory inventoryToExamine = model.getRow(row);

                InventoryDialog inventoryDialog = new InventoryDialog(frame,
                                                                      true,
                                                                      inventoryToExamine);
                inventoryDialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_drillButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JMenu adminMenu;
    private javax.swing.JButton byeButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton drillButton;
    private javax.swing.JTable inventoriesTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem locationsMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton refreshButton;
    private javax.swing.JMenuItem usersMenuItem;
    // End of variables declaration//GEN-END:variables
  
    private JDialog aboutBox;
}
