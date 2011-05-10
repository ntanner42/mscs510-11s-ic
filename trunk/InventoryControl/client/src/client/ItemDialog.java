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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.inventorycontrol.entry.Stock;
import org.workplicity.inventorycontrol.entry.Training;
import org.workplicity.inventorycontrol.entry.OrderAudit;
import org.workplicity.task.NetTask;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;


/**
 *
 * @author Sandeep MJ
 */
public class ItemDialog extends javax.swing.JDialog {


     private static WorkletContext context = WorkletContext.getInstance();

     private ArrayList<Item> currentItem = new ArrayList<Item>( );
     private ArrayList<Inventory> currentInventory = new ArrayList<Inventory>( );
    
     /** Creates new form ItemDialog */
     public ItemDialog(java.awt.Frame parent,Inventory inventory , Item item, boolean modal) {
         super(parent, modal);
         try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
         } catch (Exception e) {

         }

        initComponents();

        currentItem.add(item);
        currentInventory.add(inventory);
        init(inventory,item);

    }

    /**
     * Initializes the dialog.
     */
     private void init(Inventory inventory, Item item){

         // positions the dialog to the centre of the screen
         this.setLocationRelativeTo(null);

         // sets the title of the dialog
         this.setTitle("Item details");


         initBasicItemInformation(item);
         initStockTable(inventory,item);
         initTrainingTable();
         initOrderingTable();

     }

     /**
     * Initializes the Basic item information.
     */
     private void initBasicItemInformation( Item item){

         this.itemNameTextField.setText(item.getName().toString());
         this.itemDescriptionTextField.setText(item.getDescription().toString());
         this.itemModelTextField.setText(item.getModelNumber().toString());
         this.itemReOrderThresholdTextField.setText(item.getStockThreshold().toString());
         this.itemOEMTextField.setText(item.getOem().toString());
         
     }

    /**
     * Initializes the Stock table.
     */
    private void initStockTable(Inventory inventory, Item item) {
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

                           // call method to perform task on double click
                            editStockRequest(row);
                       
                }
            }
        });

        StockTableModel model = (StockTableModel) stockTable.getModel();
        model.refresh(inventory,item);
    }

    private Stock editStockRequest(int row) {
        StockTableModel stockModel = (StockTableModel) stockTable.getModel();

        Stock selectedStock = stockModel.getRow(row);

        return selectedStock;
        //System.out.println(selectedStock);
    }

    /**
     * Initializes the Training table.
     */
    private void initTrainingTable() {
        // Fix the column widths
        trainingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        final int[] WIDTHS = {

             40,   // Training Id
             80,   // Training Date
             55,   // Model id
             40,   // User
             70,   // Status
             87    // Trainer

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
                            editTrainingRequest(row);
                        }
                    });
                }
            }
        });

        TrainingTableModel model = (TrainingTableModel) trainingTable.getModel();
        Item item = currentItem.get(0);
        model.refresh(item);
    }

    private Training editTrainingRequest(int row) {
        TrainingTableModel trainingModel = (TrainingTableModel) trainingTable.getModel();

        Training selectedTraining = trainingModel.getRow(row);

        return selectedTraining;
       
    }

    /**
     * Initializes the Ordering table.
     */
    private void initOrderingTable() {
        // Fix the column widths
        ordersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        final int[] WIDTHS = {

             40,  // Order ID
            100,  // Order Date
             60,  // Size
            100,  // PO #
             70   // Order #

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
                            editOrdersRequest(row);
                        }
                    });
                }
            }
        });

        OrderingTableModel model = (OrderingTableModel) ordersTable.getModel();
        Item item = currentItem.get(0);
        model.refresh(item);
    }

    private OrderAudit editOrdersRequest(int row) {

        OrderingTableModel ordersModel = (OrderingTableModel) ordersTable.getModel();

        OrderAudit selectedorder = ordersModel.getRow(row);

        return selectedorder;

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
        itemReOrderThresholdTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        stockPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        deleteStockButton = new javax.swing.JButton();
        addStockButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        trainingPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        trainingTable = new javax.swing.JTable();
        deleteTrainingButton = new javax.swing.JButton();
        addTrainingButton = new javax.swing.JButton();
        drillDownTrainingButton = new javax.swing.JButton();
        orderingPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        deleteOrderButton = new javax.swing.JButton();
        addOrderButton = new javax.swing.JButton();
        drillDownOrderButton = new javax.swing.JButton();
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

        itemReOrderThresholdTextField.setText(resourceMap.getString("itemReOrderThresholdTextField.text")); // NOI18N
        itemReOrderThresholdTextField.setName("itemReOrderThresholdTextField"); // NOI18N

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
                .addGroup(basicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(itemReOrderThresholdTextField)
                    .addComponent(itemOEMTextField)
                    .addComponent(itemDescriptionTextField)
                    .addComponent(itemModelTextField)
                    .addComponent(itemNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
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
                    .addComponent(itemReOrderThresholdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
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

        editButton.setIcon(resourceMap.getIcon("editButton.icon")); // NOI18N
        editButton.setText(resourceMap.getString("editButton.text")); // NOI18N
        editButton.setName("editButton"); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stockPanelLayout = new javax.swing.GroupLayout(stockPanel);
        stockPanel.setLayout(stockPanelLayout);
        stockPanelLayout.setHorizontalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(stockPanelLayout.createSequentialGroup()
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addStockButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteStockButton)))
                .addContainerGap())
        );

        stockPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addStockButton, deleteStockButton, editButton});

        stockPanelLayout.setVerticalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteStockButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addStockButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap())
        );

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

        drillDownTrainingButton.setIcon(resourceMap.getIcon("drillDownTrainingButton.icon")); // NOI18N
        drillDownTrainingButton.setName("drillDownTrainingButton"); // NOI18N
        drillDownTrainingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillDownTrainingButtonActionPerformed(evt);
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
                        .addComponent(drillDownTrainingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addTrainingButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteTrainingButton)))
                .addContainerGap())
        );

        trainingPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addTrainingButton, deleteTrainingButton, drillDownTrainingButton});

        trainingPanelLayout.setVerticalGroup(
            trainingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(trainingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trainingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deleteTrainingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addTrainingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(drillDownTrainingButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap())
        );

        trainingPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addTrainingButton, deleteTrainingButton, drillDownTrainingButton});

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

        drillDownOrderButton.setIcon(resourceMap.getIcon("drillDownOrderButton.icon")); // NOI18N
        drillDownOrderButton.setName("drillDownOrderButton"); // NOI18N
        drillDownOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillDownOrderButtonActionPerformed(evt);
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
                        .addComponent(drillDownOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addOrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteOrderButton)))
                .addContainerGap())
        );

        orderingPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addOrderButton, deleteOrderButton, drillDownOrderButton});

        orderingPanelLayout.setVerticalGroup(
            orderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deleteOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(drillDownOrderButton, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addContainerGap())
        );

        orderingPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addOrderButton, deleteOrderButton, drillDownOrderButton});

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(doneButton)
                .addContainerGap())
            .addComponent(itemTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {doneButton, saveButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(itemTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {doneButton, saveButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteOrderButtonActionPerformed

        final ItemDialog frame = this;
        final Inventory inventory = currentInventory.get(0);
        final Item item = currentItem.get(0);

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                OrderingTableModel model = (OrderingTableModel) ordersTable.getModel();
                int row = ordersTable.getSelectedRow();
                if (row == -1){
                        JOptionPane.showMessageDialog(frame,
                        "Please select an order from the table to delete ",
                        "No selection made",
                        JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                        OrderAudit order = editOrdersRequest(row);
                       // TODO add delete Location handling code here:

                        String msg = "Are sure to delete this order?";

                        int n = JOptionPane.showConfirmDialog(
                                null,
                                msg,
                                "Confirm",
                                JOptionPane.YES_NO_OPTION);

                        if (n == 1) {
                            return;
                        }

                        try
                        {

                            refreshOrder();

                            ArrayList<OrderAudit> itemOrders = new ArrayList<OrderAudit>();

                            for (OrderAudit itemorder: item.getOrderHistory()){

                                if(itemorder.getId().toString().equals(order.getId().toString()))
                                {
                                    item.getOrderHistory().remove(order);
                                }
                                else
                                {
                                    itemOrders.add(itemorder);
                                }
                            }


                            item.getOrderHistory().clear();
                            item.setOrderHistory(itemOrders);

                            inventory.insert(item);
                            System.out.println(item.getId());

                            if (!Helper.insert(item, "Inventories", context)) {
                                System.out.println("insert Item into Inventory failed!");
                                return;
                            }

                            currentItem.clear();
                            currentItem.add(item);

                            System.out.println(item.getId());

                            if (!Helper.delete(order, "Orders", context)) {
                                System.out.print("Delete failed!");
                            }
                            else
                            {
                                System.out.print("Delete successful");
                            }

                          }catch(Exception ex){

                            }
                    }
                 refreshOrder();
                 model.fireTableDataChanged();

                }

          });
    }//GEN-LAST:event_deleteOrderButtonActionPerformed

    private void itemNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameTextFieldActionPerformed

    private void deleteStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStockButtonActionPerformed

         final ItemDialog frame = this;

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                StockTableModel model = (StockTableModel) stockTable.getModel();
                int row = stockTable.getSelectedRow();
                if (row == -1){

                        JOptionPane.showMessageDialog(frame,
                        "Please select a stock from the table to delete ",
                        "No selection made",
                        JOptionPane.ERROR_MESSAGE);

                }
                else
                 {
                    Stock stock = editStockRequest(row);
                    
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

                    try{
                        if (!Helper.delete(stock, "Inventories", context)) {
                            System.out.print("Delete failed!");
                        }
                        else
                        {
                            System.out.print("Delete successful");
                        }
                     }catch(Exception ex){

                     }
                     }

                     refreshStock();
                     model.fireTableDataChanged();
                    
                }

          });
        
    }//GEN-LAST:event_deleteStockButtonActionPerformed


    private void refreshStock(){

            final ItemDialog frame = this;


            Item item = currentItem.get(0);
            Inventory inventory = currentInventory.get(0);
            StockTableModel model = (StockTableModel) stockTable.getModel();

            final HashMap<Integer, Stock> dirty = model.getDirty();

            if (model.getDirty().isEmpty()) {
                model.refresh(inventory, item);
                return;
            }

            String msg = "Some stocks have changed.";
            msg += "\nSave them?";

            int n = JOptionPane.showConfirmDialog(
                    frame,
                    msg,
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if (n == 1) {
                model.refresh(inventory, item);
                return;
            }

            saveStock(inventory,item);
                


    }// end refresh stock

    private void saveStock(Inventory inventory,Item item){

        final ItemDialog frame = this;
        //WorkletContext context = WorkletContext.getInstance();

        StockTableModel model = (StockTableModel) stockTable.getModel();
        final HashMap<Integer, Stock> dirty = model.getDirty();

        String criteria = "/list[inventoryId=" + inventory.getId().toString() +"]";
        ArrayList<Item> items = Helper.query("Inventories", criteria, context);


        for(int i=0; i<items.size(); i++) {
               Item nextItem = items.get(i);

               if (nextItem.getId().toString().equals(item.getId().toString()))
               {

                    //query the stock for the item
                    //Print all items in the inventory
                    String criteria3 = "/list[itemId=" + item.getId().toString() + "]";

                    for(Integer id : dirty.keySet()) {
                    Stock stock = dirty.get(id);

                             item.insert(stock);

                             System.out.println(stock.getId());

                             if (!Helper.insert(stock, "Inventories", context)) {
                                 JOptionPane.showMessageDialog(frame, "insert Item into Inventory failed!",
                                    "Stocks", JOptionPane.ERROR_MESSAGE);

                             }

                                System.out.println(stock.getId());

                    }// end foreach
                }// end if

              }// end for

        model.refresh(inventory, item);

    }// end saveStock

    private void refreshBasicItemInformation(){

        final ItemDialog frame = this;

        Item item = currentItem.get(0);
        Inventory inventory = currentInventory.get(0);
        boolean isBasicItemInformationChanged = false;


        if (!(this.itemNameTextField.getText().toString().equals(item.getName().toString())))
        {
            isBasicItemInformationChanged = true;
        }
        if (!(this.itemDescriptionTextField.getText().toString().equals(item.getDescription().toString())))
        {
            isBasicItemInformationChanged = true;
        }
        if (!(this.itemModelTextField.getText().toString().equals(item.getModelNumber().toString())))
        {
            isBasicItemInformationChanged = true;
        }
        if (!(this.itemReOrderThresholdTextField.getText().toString().equals(item.getStockThreshold().toString())))
        {
            isBasicItemInformationChanged = true;
        }
        if (!(this.itemOEMTextField.getText().toString().equals(item.getOem().toString())))
        {
            isBasicItemInformationChanged = true;
        }

        if(isBasicItemInformationChanged)
        {
            String msg = "Some item details have changed.";
            msg += "\nSave them?";

            int n = JOptionPane.showConfirmDialog(
                    frame,
                    msg,
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if (n == 1) {
                
                initBasicItemInformation(item);
                return;
            }

            saveBasicItemInformation();

        }
}

    private void saveBasicItemInformation(){

        final ItemDialog frame = this;

        Item item = currentItem.get(0);
        Inventory inventory = currentInventory.get(0);

        String itemName = itemNameTextField.getText().toString();
        String itemDescription = itemDescriptionTextField.getText().toString();
        String itemOem = itemOEMTextField.getText().toString();
        String itemModel = itemModelTextField.getText().toString();
        String itemReOrderThreshold = itemReOrderThresholdTextField.getText().toString();

        Integer reOrderThreshold = 0;

        try{
                reOrderThreshold = Integer.parseInt(itemReOrderThreshold);
        }catch(Exception ex1){

            String errorMessage = "Re-order threshold must be an integer";
            JOptionPane.showMessageDialog(this,errorMessage,
                                    "Required parameters missing",
                                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Item newItem = item;
        newItem.setName(itemName);
        newItem.setDescription(itemDescription);
        newItem.setModelNumber(itemModel);
        newItem.setOem(itemOem);
        newItem.setStockThreshold(reOrderThreshold);

        inventory.insert(item);

        System.out.println(item.getId());

        if (!Helper.insert(item, "Inventories", context)) {
            System.out.println("insert Item into Inventory failed!");
        }

        System.out.println(item.getId());
 
    }

    private boolean validateBasicItemInformation(){

        Item item = currentItem.get(0);
        Inventory inventory = currentInventory.get(0);

        String errorMessage = "";

        String itemName = itemNameTextField.getText().toString();
        String itemDescription = itemDescriptionTextField.getText().toString();
        String itemOem = itemOEMTextField.getText().toString();
        String itemModel = itemModelTextField.getText().toString();
        String itemReOrderThreshold = itemReOrderThresholdTextField.getText().toString();
        Integer stockReOrderThreshold = 0;

        if(itemName.equals("")  || itemName.isEmpty())
        {
            errorMessage += "Please enter a item name\n";

        }

        if ((itemDescription.equals("")) || itemDescription.isEmpty())
        {
            errorMessage += "Please enter a item description\n";

        }

        if(itemOem.equals("") || itemOem.isEmpty())
        {
            errorMessage += "Please enter a item oem number\n";

        }

        if (itemModel.equals("") || itemModel.isEmpty())
        {
            errorMessage += "Please enter a item model\n";

        }
        if (itemReOrderThreshold.equals("") || itemReOrderThreshold.isEmpty())
        {
            errorMessage += "Please enter a item re-order threshold\n";

        }
        else
        {
            try
            {
                stockReOrderThreshold = Integer.parseInt(itemReOrderThreshold.toString());
                if (stockReOrderThreshold < 0)
                {
                   errorMessage += "Re-order Threshold must be a positive integer\n";
                }
            }catch(Exception ex){

                errorMessage += "Re-order Threshold must be an integer\n";
            }
        }


        if(errorMessage.length() > 0) {

            JOptionPane.showMessageDialog(this,errorMessage,
                                        "Required parameters missing",
                                        JOptionPane.ERROR_MESSAGE);
            
            return false ;
        }

        return true;

    }

    private void refreshTraining()
    {
        final ItemDialog frame = this;
        final Item item = currentItem.get(0);


        TrainingTableModel model = (TrainingTableModel) trainingTable.getModel();

        final HashMap<Integer, Training> dirty = model.getDirty();

        if (model.getDirty().isEmpty()) {
            model.refresh(item);
            return;
        }

        String msg = "Some trainings have changed.";
        msg += "\nSave them?";

        int n = JOptionPane.showConfirmDialog(
                frame,
                msg,
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (n == 1) {
            model.refresh(item);
            return;
        }

        saveTraining();

    }

    private void saveTraining()
    {
        final ItemDialog frame = this;
        //WorkletContext context = WorkletContext.getInstance();
        final Item item = currentItem.get(0);

        TrainingTableModel model = (TrainingTableModel) trainingTable.getModel();
        final HashMap<Integer, Training> dirty = model.getDirty();

        for(Integer id : dirty.keySet()) {

            Training changedTraining = dirty.get(id);

            System.out.println(changedTraining.getId());

            if (!Helper.insert(changedTraining, "Trainings", context)) {
                    JOptionPane.showMessageDialog(frame, "insert Training into Trainings failed!",
                    "Trainings", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            System.out.println(changedTraining.getId());

        }// end foreach
 
        model.refresh(item);
        
    }

     private void refreshOrder()
     {
        final ItemDialog frame = this;
        final Item item = currentItem.get(0);


        OrderingTableModel model = (OrderingTableModel) ordersTable.getModel();

        final HashMap<Integer, OrderAudit> dirty = model.getDirty();

        if (model.getDirty().isEmpty()) {
            model.refresh(item);
            return;
        }

        String msg = "Some orders have changed.";
        msg += "\nSave them?";

        int n = JOptionPane.showConfirmDialog(
                frame,
                msg,
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (n == 1) {
            model.refresh(item);
            return;
        }

        saveOrders();

    }

    private void saveOrders()
    {
        final ItemDialog frame = this;
        final Item item = currentItem.get(0);
        //WorkletContext context = WorkletContext.getInstance();

        OrderingTableModel model = (OrderingTableModel) ordersTable.getModel();
        final HashMap<Integer, OrderAudit> dirty = model.getDirty();

        for(Integer id : dirty.keySet()) {

            OrderAudit changedOrder = dirty.get(id);

            System.out.println(changedOrder.getId());

            if (!Helper.insert(changedOrder, "Orders", context)) {
                    JOptionPane.showMessageDialog(frame, "insert order into Trainings failed!",
                    "Orders", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            System.out.println(changedOrder.getId());

        }// end foreach

        model.refresh(item);

    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add refresh button handling code here:
       final ItemDialog frame = this;
       SwingUtilities.invokeLater(new Runnable() {

           public void run() {

                int selectedTab = frame.itemTabbedPane.getSelectedIndex();
                switch(selectedTab)
                {
                    case 0:
                             boolean isItemValid = validateBasicItemInformation();
                             if(isItemValid)
                             {
                                   refreshBasicItemInformation();
                             }
                             break;

                    case 1:
                            refreshStock();
                            break;

                    case 2:
                            refreshTraining();
                            break;
                    case 3: 
                            refreshOrder();
                            break;

                    default:
                             break;
                          
                }

             }// end run

        });// end swing utilities


    }//GEN-LAST:event_refreshButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add save handling code here:
        final ItemDialog frame = this;
        SwingUtilities.invokeLater(new Runnable() {

           public void run() {

               Item item = currentItem.get(0);
               Inventory inventory = currentInventory.get(0);

               boolean isItemValid = validateBasicItemInformation();
               if(isItemValid)
               {
                   saveBasicItemInformation();

                   saveStock(inventory,item);

                   saveTraining();

                   saveOrders();

                   dispose();
               }        


             }// end run

        });// end swing utilities


        
    }//GEN-LAST:event_saveButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        // TODO add done handling code here:
        // TODO add refresh button handling code here:
       SwingUtilities.invokeLater(new Runnable() {

           public void run() {

               boolean isItemValid = validateBasicItemInformation();
               if(isItemValid)
               {
                   refreshBasicItemInformation();

                   refreshStock();

                   refreshTraining();

                   refreshOrder();

                   dispose();
               }
               
              

             }// end run

        });// end swing utilities

        
    }//GEN-LAST:event_doneButtonActionPerformed

    private void addStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockButtonActionPerformed
        
        
        final ItemDialog frame = this;
        final  Item item = currentItem.get(0);
        final  Inventory inventory = currentInventory.get(0);
        final  Stock stock = new Stock();


        refreshStock();
       
        SwingUtilities.invokeLater(new Runnable() {            
            public void run() {
                // Displays add stock dialog
                AddStockDialog stockDialog =
                        new AddStockDialog( null , inventory, item, stock, true);
                stockDialog.setVisible(true);
                stockDialog.setTitle("Add new stock");

                refreshStock();
            }
        });
    }//GEN-LAST:event_addStockButtonActionPerformed

    private void addTrainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTrainingButtonActionPerformed
        
        final ItemDialog dialog = this;
        final Inventory inventory = currentInventory.get(0);
        final Item item = currentItem.get(0);
        final Training training = new Training();

        refreshTraining();
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                              
                    // Displays add training dialog
                    AddTrainingDialog trainingDialog =
                            new AddTrainingDialog( null ,inventory, item, training, true);

                    trainingDialog.setTitle("Add new Training");
                    trainingDialog.setVisible(true);
                    

                    refreshTraining();

            }
        });
    }//GEN-LAST:event_addTrainingButtonActionPerformed

    private void deleteTrainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTrainingButtonActionPerformed
        // TODO add delete training button handling code here:
        
        final ItemDialog frame = this;
        final Inventory inventory = currentInventory.get(0);
        final Item item = currentItem.get(0);

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                TrainingTableModel model = (TrainingTableModel) trainingTable.getModel();
                int row = trainingTable.getSelectedRow();
                if (row == -1){
                        JOptionPane.showMessageDialog(frame,
                        "Please select a location from the table to delete ",
                        "No selection made",
                        JOptionPane.ERROR_MESSAGE);
                }
                else
                 {
                        Training training = editTrainingRequest(row);
                       // TODO add delete Location handling code here:
                        
                        String msg = "Are sure to delete this training?";

                        int n = JOptionPane.showConfirmDialog(
                                null,
                                msg,
                                "Confirm",
                                JOptionPane.YES_NO_OPTION);

                        if (n == 1) {
                            return;
                        }

                        try
                        {

                            refreshTraining();

                            ArrayList<Training> itemTrainings = new ArrayList<Training>(); 
              
                            for (Training itemTraining: item.getTrainings()){

                                if(itemTraining.getId().toString().equals(training.getId().toString()))
                                {
                                    item.getTrainings().remove(training);
                                }
                                else
                                {
                                    itemTrainings.add(itemTraining);
                                }
                            }


                            item.getTrainings().clear();
                            item.setTrainings(itemTrainings);




                            inventory.insert(item);
                            System.out.println(item.getId());

                            if (!Helper.insert(item, "Inventories", context)) {
                                System.out.println("insert Item into Inventory failed!");
                                return;
                            }

                            currentItem.clear();
                            currentItem.add(item);
                            
                            System.out.println(item.getId());
                           
                            if (!Helper.delete(training, "Trainings", context)) {
                                System.out.print("Delete failed!");
                            }
                            else
                            {
                                System.out.print("Delete successful");
                            }

                }catch(Exception ex){

               }
              }
                 refreshTraining();
                 model.fireTableDataChanged();

                }

          });
    }//GEN-LAST:event_deleteTrainingButtonActionPerformed

    private void addOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderButtonActionPerformed
        // TODO add your handling code here:
         final ItemDialog dialog = this;
         final Inventory inventory = currentInventory.get(0);
         final Item item = currentItem.get(0);
         final OrderAudit order = new OrderAudit();
         refreshOrder();

         SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
                try {
                     // Displays add ordering dialog
                    AddOrderingDialog orderingDialog =
                            new AddOrderingDialog( null ,inventory, item, order, true);
                   
                    orderingDialog.setTitle("Add new order");
                    orderingDialog.setVisible(true);

                    refreshOrder();

                } catch (Exception e) {
                    //Logger.log(e.toString());
                }
            }
        });
    }//GEN-LAST:event_addOrderButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:

        final ItemDialog frame = this;
        final  Item item = currentItem.get(0);
        final  Inventory inventory = currentInventory.get(0);
       
       //refreshStock();

        SwingUtilities.invokeLater(new Runnable() {


            public void run() {

                int row = stockTable.getSelectedRow();
                if (row == -1){

                        JOptionPane.showMessageDialog(frame,
                        "Please select a stock from the table to view ",
                        "No selection made",
                        JOptionPane.ERROR_MESSAGE);

                }
                else
                 {
                        // Displays add stock dialog
                        Stock stock = new Stock();
                        stock = editStockRequest(row);
                        AddStockDialog stockDialog =
                                new AddStockDialog( null , inventory, item, stock, true);
                        stockDialog.setVisible(true);
                        stockDialog.setTitle("Edit stock");

                        refreshStock();
                }
            }
        });


        
    }//GEN-LAST:event_editButtonActionPerformed

    private void drillDownTrainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drillDownTrainingButtonActionPerformed
        // TODO add your handling code here:
        final ItemDialog frame = this;
        final Inventory inventory = currentInventory.get(0);
        final Item item = currentItem.get(0);

        SwingUtilities.invokeLater(new Runnable() {


            public void run() {

                int row = trainingTable.getSelectedRow();
                if (row == -1){

                        JOptionPane.showMessageDialog(frame,
                        "Please select a training from the table to edit ",
                        "No selection made",
                        JOptionPane.ERROR_MESSAGE);

                }
                else
                 {
                        // Displays add stock dialog
                        Training training = new Training();
                        training = editTrainingRequest(row);

                        refreshTraining();

                        AddTrainingDialog stockDialog =
                                new AddTrainingDialog( null ,inventory, item, training, true);

                        stockDialog.setTitle("Edit training");
                        stockDialog.setVisible(true);
                        
                        refreshTraining();
                }
            }
        });

    }//GEN-LAST:event_drillDownTrainingButtonActionPerformed

    private void drillDownOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drillDownOrderButtonActionPerformed
        // TODO add your handling code here:
        final ItemDialog frame = this;
        final Inventory inventory = currentInventory.get(0);
        final Item item = currentItem.get(0);

        SwingUtilities.invokeLater(new Runnable() {


            public void run() {

                int row = ordersTable.getSelectedRow();
                if (row == -1){

                        JOptionPane.showMessageDialog(frame,
                        "Please select an order from the table to edit ",
                        "No selection made",
                        JOptionPane.ERROR_MESSAGE);

                }
                else
                 {
                        // Displays add stock dialog
                        OrderAudit order = new OrderAudit();
                        order = editOrdersRequest(row);

                        refreshOrder();

                        AddOrderingDialog orderDialog =
                                new AddOrderingDialog( null ,inventory, item, order, true);

                        orderDialog.setTitle("Edit Order");
                        orderDialog.setVisible(true);
                        

                        refreshOrder();
                }
            }
        });


    }//GEN-LAST:event_drillDownOrderButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {

//                   NetTask.setUrlBase("http://localhost:8080/netprevayle/task");
//
//                        if(!Helper.login("admin","gaze11e",context))
//                            throw new Exception("login failed");
//
//                    String criteria1 = "/list[1]";
//                    //Issuing the query using the helper to the Inventories repository
//                   ArrayList<Inventory> inventories = Helper.query("Inventories", criteria1, context);
//                    if (inventories == null) {
//                        throw new Exception("bad query");
//                    }
//
//                    Inventory inventory = inventories.get(0);
//
//                    String criteria2 = "/list[inventoryId=" + inventory.getId().toString() + "]";
//                    ArrayList<Item> items = Helper.query("Inventories", criteria2, context);
//
//                    Item item = items.get(0);
//
//                    ItemDialog dialog = new ItemDialog(new javax.swing.JFrame(),inventory, item, true);
                    ItemDialog dialog = new ItemDialog(new javax.swing.JFrame(),null, null, true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                    });
                    dialog.setVisible(true);
                 } catch (Exception ex) {

                }
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
    private javax.swing.JButton drillDownOrderButton;
    private javax.swing.JButton drillDownTrainingButton;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField itemDescriptionTextField;
    private javax.swing.JTextField itemModelTextField;
    private javax.swing.JTextField itemNameTextField;
    private javax.swing.JTextField itemOEMTextField;
    private javax.swing.JTextField itemReOrderThresholdTextField;
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
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel stockPanel;
    private javax.swing.JTable stockTable;
    private javax.swing.JPanel trainingPanel;
    private javax.swing.JTable trainingTable;
    // End of variables declaration//GEN-END:variables

}
