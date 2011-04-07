/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemDialog.java
 *
 * Created on Mar 26, 2011, 12:14:36 AM
 */

package client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;


/**
 *
 * @author Neal
 */
public class ItemDialog extends javax.swing.JDialog {

    /** Creates new form ItemDialog */
    public ItemDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // sets the title of the dialog
        this.setTitle("Item details");

        init();

    }

    /**
     * Initializes the dialog.
     */
     private void init(){

         // positions the dialog to the centre of the screen
         this.setLocationRelativeTo(null);

         initBasicItemInformation();
         initStockTable();
         initTrainingTable();
         initOrderingTable();

     }

     /**
     * Initializes the Basic item information.
     */
     private void initBasicItemInformation(){
       
     }

    /**
     * Initializes the Stock table.
     */
    private void initStockTable() {
        // Fix the column widths
        stockTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        final int[] WIDTHS = {
             42,  // Id
            100,  // Updated
            115,  // Asset tag
            115   // RMA#
            
        };

        for(int i=0; i < WIDTHS.length; i++) {
            TableColumn col = stockTable.getColumnModel().getColumn(i);

            col.setPreferredWidth(WIDTHS[i]);
        }

        // Add mouse support for the request table
        // See http://mycodepage.blogspot.com/2006/09/how-to-create-double-click-event-on.html
        stockTable.addMouseListener(new MouseAdapter() {
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

        //StockTableModel model = (StockTableModel) stockTable.getModel();
        //model.refresh();
    }


    /**
     * Initializes the Training table.
     */
    private void initTrainingTable() {
        // Fix the column widths
        trainingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        final int[] WIDTHS = {
             80,   // Training Date
             60,   // Model id
             75,   // User
             85,   // Status
             73,   // Trainer

        };

        for(int i=0; i < WIDTHS.length; i++) {
            TableColumn col = trainingTable.getColumnModel().getColumn(i);

            col.setPreferredWidth(WIDTHS[i]);
        }

        // Add mouse support for the request table
        // See http://mycodepage.blogspot.com/2006/09/how-to-create-double-click-event-on.html
        trainingTable.addMouseListener(new MouseAdapter() {
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

        //TrainingTableModel model = (TrainingTableModel) trainingTable.getModel();
        //model.refresh();
    }


    /**
     * Initializes the Ordering table.
     */
    private void initOrderingTable() {
        // Fix the column widths
        ordersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        final int[] WIDTHS = {
             90,  // Order Date
             82,  // Size
            100,  // PO #
            100   // Order #

        };

        for(int i=0; i < WIDTHS.length; i++) {
            TableColumn col = ordersTable.getColumnModel().getColumn(i);

            col.setPreferredWidth(WIDTHS[i]);
        }

        // Add mouse support for the orders table
        // See http://mycodepage.blogspot.com/2006/09/how-to-create-double-click-event-on.html
        stockTable.addMouseListener(new MouseAdapter() {
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

        //OrderingTableModel model = (OrderingTableModel) ordersTable.getModel();
        //model.refresh();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        itemTabbedPane = new javax.swing.JTabbedPane();
        basicPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        itemNameTextField = new javax.swing.JTextField();
        itemModelTextField = new javax.swing.JTextField();
        itemDescriptionTextField = new javax.swing.JTextField();
        itemOEMTextField = new javax.swing.JTextField();
        reorderThresholdTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        stockPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        deleteStockButton = new javax.swing.JButton();
        addStockButton = new javax.swing.JButton();
        trainingPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        trainingTable = new javax.swing.JTable();
        deleteTrainingButton = new javax.swing.JButton();
        addTrainingButton = new javax.swing.JButton();
        orderingPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        deleteOrderButton = new javax.swing.JButton();
        addOrderButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        itemTabbedPane.setName("itemTabbedPane"); // NOI18N

        basicPanel.setName("basicPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(client.MainFrame.class).getContext().getResourceMap(ItemDialog.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        itemNameTextField.setText(resourceMap.getString("itemNameTextField.text")); // NOI18N
        itemNameTextField.setName("itemNameTextField"); // NOI18N
        itemNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameTextFieldActionPerformed(evt);
            }
        });

        itemModelTextField.setText(resourceMap.getString("itemModelTextField.text")); // NOI18N
        itemModelTextField.setName("itemModelTextField"); // NOI18N

        itemDescriptionTextField.setText(resourceMap.getString("itemDescriptionTextField.text")); // NOI18N
        itemDescriptionTextField.setName("itemDescriptionTextField"); // NOI18N

        itemOEMTextField.setText(resourceMap.getString("itemOEMTextField.text")); // NOI18N
        itemOEMTextField.setName("itemOEMTextField"); // NOI18N

        reorderThresholdTextField.setText(resourceMap.getString("reorderThresholdTextField.text")); // NOI18N
        reorderThresholdTextField.setName("reorderThresholdTextField"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        javax.swing.GroupLayout basicPanelLayout = new javax.swing.GroupLayout(basicPanel);
        basicPanel.setLayout(basicPanelLayout);
        basicPanelLayout.setHorizontalGroup(
            basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(itemOEMTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(itemDescriptionTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(itemModelTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(itemNameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                    .addComponent(reorderThresholdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        basicPanelLayout.setVerticalGroup(
            basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemOEMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(reorderThresholdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        itemTabbedPane.addTab(resourceMap.getString("basicPanel.TabConstraints.tabTitle"), basicPanel); // NOI18N

        stockPanel.setName("stockPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        stockTable.setModel(new StockTableModel());
        stockTable.setName("stockTable"); // NOI18N
        jScrollPane1.setViewportView(stockTable);

        deleteStockButton.setIcon(resourceMap.getIcon("deleteStockButton.icon")); // NOI18N
        deleteStockButton.setText(resourceMap.getString("deleteStockButton.text")); // NOI18N
        deleteStockButton.setName("deleteStockButton"); // NOI18N
        deleteStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStockButtonActionPerformed(evt);
            }
        });

        addStockButton.setIcon(resourceMap.getIcon("addStockButton.icon")); // NOI18N
        addStockButton.setText(resourceMap.getString("addStockButton.text")); // NOI18N
        addStockButton.setName("addStockButton"); // NOI18N
        addStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stockPanelLayout = new javax.swing.GroupLayout(stockPanel);
        stockPanel.setLayout(stockPanelLayout);
        stockPanelLayout.setHorizontalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stockPanelLayout.createSequentialGroup()
                        .addComponent(addStockButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteStockButton)))
                .addContainerGap())
        );

        stockPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addStockButton, deleteStockButton});

        stockPanelLayout.setVerticalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteStockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStockButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        stockPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addStockButton, deleteStockButton});

        itemTabbedPane.addTab(resourceMap.getString("stockPanel.TabConstraints.tabTitle"), stockPanel); // NOI18N

        trainingPanel.setName("trainingPanel"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        trainingTable.setModel(new TrainingTableModel());
        trainingTable.setName("trainingTable"); // NOI18N
        jScrollPane2.setViewportView(trainingTable);

        deleteTrainingButton.setIcon(resourceMap.getIcon("deleteTrainingButton.icon")); // NOI18N
        deleteTrainingButton.setText(resourceMap.getString("deleteTrainingButton.text")); // NOI18N
        deleteTrainingButton.setName("deleteTrainingButton"); // NOI18N
        deleteTrainingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTrainingButtonActionPerformed(evt);
            }
        });

        addTrainingButton.setIcon(resourceMap.getIcon("addTrainingButton.icon")); // NOI18N
        addTrainingButton.setText(resourceMap.getString("addTrainingButton.text")); // NOI18N
        addTrainingButton.setName("addTrainingButton"); // NOI18N
        addTrainingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTrainingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout trainingPanelLayout = new javax.swing.GroupLayout(trainingPanel);
        trainingPanel.setLayout(trainingPanelLayout);
        trainingPanelLayout.setHorizontalGroup(
            trainingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trainingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trainingPanelLayout.createSequentialGroup()
                        .addComponent(addTrainingButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteTrainingButton)))
                .addContainerGap())
        );

        trainingPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addTrainingButton, deleteTrainingButton});

        trainingPanelLayout.setVerticalGroup(
            trainingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(trainingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteTrainingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTrainingButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        trainingPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addTrainingButton, deleteTrainingButton});

        itemTabbedPane.addTab(resourceMap.getString("trainingPanel.TabConstraints.tabTitle"), trainingPanel); // NOI18N

        orderingPanel.setName("orderingPanel"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        ordersTable.setModel(new OrderingTableModel());
        ordersTable.setName("ordersTable"); // NOI18N
        jScrollPane3.setViewportView(ordersTable);

        deleteOrderButton.setIcon(resourceMap.getIcon("deleteOrderButton.icon")); // NOI18N
        deleteOrderButton.setText(resourceMap.getString("deleteOrderButton.text")); // NOI18N
        deleteOrderButton.setName("deleteOrderButton"); // NOI18N
        deleteOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteOrderButtonActionPerformed(evt);
            }
        });

        addOrderButton.setIcon(resourceMap.getIcon("addOrderButton.icon")); // NOI18N
        addOrderButton.setText(resourceMap.getString("addOrderButton.text")); // NOI18N
        addOrderButton.setName("addOrderButton"); // NOI18N
        addOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout orderingPanelLayout = new javax.swing.GroupLayout(orderingPanel);
        orderingPanel.setLayout(orderingPanelLayout);
        orderingPanelLayout.setHorizontalGroup(
            orderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderingPanelLayout.createSequentialGroup()
                        .addComponent(addOrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteOrderButton)))
                .addContainerGap())
        );

        orderingPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addOrderButton, deleteOrderButton});

        orderingPanelLayout.setVerticalGroup(
            orderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addOrderButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        orderingPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addOrderButton, deleteOrderButton});

        itemTabbedPane.addTab(resourceMap.getString("orderingPanel.TabConstraints.tabTitle"), orderingPanel); // NOI18N

        doneButton.setText(resourceMap.getString("doneButton.text")); // NOI18N
        doneButton.setName("doneButton"); // NOI18N
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
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

        saveButton.setText(resourceMap.getString("saveButton.text")); // NOI18N
        saveButton.setName("saveButton"); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(itemTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(doneButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(itemTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doneButton)
                    .addComponent(refreshButton)
                    .addComponent(saveButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteOrderButtonActionPerformed
        OrderingTableModel model = (OrderingTableModel) ordersTable.getModel();
        // TODO add delete order handling code here:
        String msg = "Are sure to delete this order?";

        int n = JOptionPane.showConfirmDialog(
                null,
                msg,
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (n == 1) {
            return;

        }
        model.fireTableDataChanged();
    }//GEN-LAST:event_deleteOrderButtonActionPerformed

    private void itemNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameTextFieldActionPerformed

    private void deleteStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStockButtonActionPerformed

        StockTableModel model = (StockTableModel) stockTable.getModel();
       // TODO add delete stock handling code here:
        String msg = "Are sure to delete this stock?";
              
        int n = JOptionPane.showConfirmDialog(
                null,
                msg,
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (n == 1) {
            return;
            
        }

         model.fireTableDataChanged();
    }//GEN-LAST:event_deleteStockButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add refresh button handling code here:
        String msg = "Some items have changed.";
               msg += "\nSave them?";
        int n = JOptionPane.showConfirmDialog(
                null,
                msg,
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (n == 1) {
            return;
            
        }

    }//GEN-LAST:event_refreshButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add save handling code here:
         dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        // TODO add done handling code here:
        String msg = "Some items have changed.";
               msg += "\nSave them?";

        int n = JOptionPane.showConfirmDialog(
                null,
                msg,
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (n == 1) {
            //return;
            dispose();
        }

        dispose();
    }//GEN-LAST:event_doneButtonActionPerformed

    private void addStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockButtonActionPerformed
        
        final ItemDialog dialog = this;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Displays add stock dialog
                    AddStockDialog stockDialog =
                            new AddStockDialog( null , true);

                    stockDialog.setVisible(true);

                } catch (Exception e) {
                    //Logger.log(e.toString());
                }
            }
        });

    }//GEN-LAST:event_addStockButtonActionPerformed

    private void addTrainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTrainingButtonActionPerformed
        
        final ItemDialog dialog = this;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Displays add training dialog
                    AddTrainingDialog trainingDialog =
                            new AddTrainingDialog( null , true);

                    trainingDialog.setVisible(true);

                } catch (Exception e) {
                    //Logger.log(e.toString());
                }
            }
        });
    }//GEN-LAST:event_addTrainingButtonActionPerformed

    private void deleteTrainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTrainingButtonActionPerformed
        // TODO add delete training button handling code here:
        TrainingTableModel model = (TrainingTableModel) trainingTable.getModel();
        String msg = "Are sure to delete this Training?";

        int n = JOptionPane.showConfirmDialog(
                null,
                msg,
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (n == 1) {
            return;

        }
        model.fireTableDataChanged();
    }//GEN-LAST:event_deleteTrainingButtonActionPerformed

    private void addOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderButtonActionPerformed
        // TODO add your handling code here:
         final ItemDialog dialog = this;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                     // Displays add ordering dialog
                    AddOrderingDialog orderingDialog =
                            new AddOrderingDialog( null , true);

                    orderingDialog.setVisible(true);

                } catch (Exception e) {
                    //Logger.log(e.toString());
                }
            }
        });
    }//GEN-LAST:event_addOrderButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Attempt to set the appearance to the system default
                try {
                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {

                }
                ItemDialog dialog = new ItemDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addOrderButton;
    private javax.swing.JButton addStockButton;
    private javax.swing.JButton addTrainingButton;
    private javax.swing.JPanel basicPanel;
    private javax.swing.JButton deleteOrderButton;
    private javax.swing.JButton deleteStockButton;
    private javax.swing.JButton deleteTrainingButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JTextField itemDescriptionTextField;
    private javax.swing.JTextField itemModelTextField;
    private javax.swing.JTextField itemNameTextField;
    private javax.swing.JTextField itemOEMTextField;
    private javax.swing.JTabbedPane itemTabbedPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel orderingPanel;
    private javax.swing.JTable ordersTable;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField reorderThresholdTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel stockPanel;
    private javax.swing.JTable stockTable;
    private javax.swing.JPanel trainingPanel;
    private javax.swing.JTable trainingTable;
    // End of variables declaration//GEN-END:variables

}
