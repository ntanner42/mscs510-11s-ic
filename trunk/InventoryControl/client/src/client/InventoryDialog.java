/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InventoryDialog.java
 *
 * Created on Mar 25, 2011, 11:57:35 PM
 */

package client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;

/**
 *
 * @author Neal
 * @author Brian Gormanly
 */
public class InventoryDialog extends javax.swing.JDialog {

    // Pointer to the parent frame, for passing to subsequent
    // dialogs.
    private java.awt.Frame parentFrame = null;
    private Inventory inventory = new Inventory("");

    /** Creates new form InventoryDialog */
    public InventoryDialog(java.awt.Frame parent, boolean modal, Inventory inventory)
    {
        super(parent, modal);
        parentFrame = parent;

        // Attempt to set the appearance to the system default
        try
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex)
        {
            System.out.println("Failed to set the look and feel.");
        }
        
        this.inventory = inventory;
            
        initComponents();
        init(inventory);
        initTableEditor();
        initItemsTable(inventory);
        
        refresh();
    }

    private void init(Inventory inventory)
    {
        // Get the screen size object
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        // Get the size of the screen
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // Get the size of the current window
        int windowHeight = this.getHeight();
        int windowWidth = this.getWidth();

        int newX = (screenWidth / 2) - (windowWidth / 2);
        int newY = (screenHeight / 2) - (windowHeight / 2);
        this.setLocation(newX, newY);

        inventoryNameTextField.setText(inventory.getName());
        inventoryNameTextField.setEditable(false);
    }

    private void initItemsTable(Inventory inventory)
    {
        // Fix the column widths
        itemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        final int[] WIDTHS = {
             40,  // Id
             60,  // Updated
             80,  // Model number
            110,  // Name
            174,  // Description
        };

        for(int i=0; i < WIDTHS.length; i++) {
            TableColumn col = itemsTable.getColumnModel().getColumn(i);

            col.setPreferredWidth(WIDTHS[i]);
        }

        ItemsTableModel model = (ItemsTableModel) itemsTable.getModel();
        model.setInventory(inventory);
    }

    private void initTableEditor()
    {
        itemsTable.addMouseListener(new MouseAdapter() {
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
    
    private void refresh() {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                ItemsTableModel model = (ItemsTableModel) itemsTable.getModel();

                final HashMap<Integer, Item> dirty = model.getDirty();

                if (model.getDirty().isEmpty()) {
                    model.refresh();
                    return;
                }

                String msg = "Some items have changed.";
                msg += "\nSave them?";

                int n = JOptionPane.showConfirmDialog(
                        parentFrame,
                        msg,
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);

                if (n == 1) {
                    model.refresh();
                    return;
                }

                int row = itemsTable.getSelectedRow();
                
                boolean updateSuccessful = model.update(row, inventory );
                

                if(!updateSuccessful)
                {
                    JOptionPane.showMessageDialog(parentFrame, "Saved failed!",
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

        jLabel1 = new javax.swing.JLabel();
        inventoryNameTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemsTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        drillButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(client.MainFrame.class).getContext().getResourceMap(InventoryDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        inventoryNameTextField.setText(resourceMap.getString("inventoryNameTextField.text")); // NOI18N
        inventoryNameTextField.setName("inventoryNameTextField"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        itemsTable.setModel(new ItemsTableModel());
        itemsTable.setName("itemsTable"); // NOI18N
        jScrollPane1.setViewportView(itemsTable);

        refreshButton.setIcon(resourceMap.getIcon("refreshButton.icon")); // NOI18N
        refreshButton.setText(resourceMap.getString("refreshButton.text")); // NOI18N
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
        drillButton.setName("drillButton"); // NOI18N
        drillButton.setPreferredSize(new java.awt.Dimension(49, 25));
        drillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inventoryNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                        .addComponent(drillButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton)
                        .addGap(18, 18, 18)
                        .addComponent(doneButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inventoryNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(drillButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(doneButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void editRequest(int row)
   {
        ItemsTableModel model = (ItemsTableModel) itemsTable.getModel();

        Item item = model.getRow(row);

        System.out.println(item);
        
        model.refresh();
        refresh();
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        
        
        final InventoryDialog frame = this;
        final Inventory inventoryP = this.inventory;
        final Item item = new Item("");

        refresh();
       
        SwingUtilities.invokeLater(new Runnable() {            
            public void run() {
                // Displays add stock dialog
                AddItemDialog itemDialog = new AddItemDialog( null, inventoryP, item, true);
                itemDialog.setVisible(true);
                itemDialog.setTitle("Add new item");

                refresh();
            }
        });
  
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                
                int row = itemsTable.getSelectedRow();

                ItemsTableModel model = (ItemsTableModel) itemsTable.getModel();
                boolean deleteSuccessful = model.delete(row);

                if(!deleteSuccessful)
                {
                        JOptionPane.showMessageDialog(null, "Delete failed!",
                            "Inventory Control - Item",
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

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        refresh();
        this.setVisible(false);
    }//GEN-LAST:event_doneButtonActionPerformed

    private void drillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drillButtonActionPerformed
        
        final InventoryDialog frame = this;

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                int row = itemsTable.getSelectedRow();

                ItemsTableModel model = (ItemsTableModel) itemsTable.getModel();

                Item itemToExamine = model.getRow(row);

                ItemDialog itemDialog = new ItemDialog(null, model.getInventory(), itemToExamine, true);
                itemDialog.setVisible(true);
                
                refresh();
            }
        });
        
    }//GEN-LAST:event_drillButtonActionPerformed
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InventoryDialog dialog = new InventoryDialog(new javax.swing.JFrame(), true, new Inventory(""));
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
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
    private javax.swing.JButton drillButton;
    private javax.swing.JTextField inventoryNameTextField;
    private javax.swing.JTable itemsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables

}
