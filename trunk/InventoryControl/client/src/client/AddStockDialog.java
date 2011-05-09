/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddStockDialog.java
 *
 * Created on Apr 1, 2011, 12:14:22 AM
 */

package client;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.inventorycontrol.entry.Stock;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SandeepMJ
 */
public class AddStockDialog extends javax.swing.JDialog {


    private ArrayList<Item> currentItem = new ArrayList<Item>( );
    private ArrayList<Inventory> currentInventory = new ArrayList<Inventory>( );
    private ArrayList<Stock> currentStock = new ArrayList<Stock>( );

    /** Creates new form AddStockDialog */
    public AddStockDialog(JDialog parent,Inventory inventory, Item item, Stock stock, boolean modal) {
        super(parent, modal);
       
        initComponents();
         try {
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {

            }
        this.setLocationRelativeTo(null);

        currentItem.add(item);
        currentInventory.add(inventory);
        currentStock.add(stock);

        // custom initialization for add stock.
        init( inventory,item, stock);
        
    }// end AddStockDialod constructor

    private void init(Inventory inventory, Item item, Stock stock){

        if (!( stock.getPartNumber() == null)){
              partNumberTextField.setText(stock.getPartNumber().toString());
        }
        if (!( stock.getAssetTag() == null)){
            assetTagTextField.setText(stock.getAssetTag().toString());
        }
        if (! (stock.getRmaNumber() == null)){
            rmaNumberTextField.setText(stock.getRmaNumber().toString());
        }
        if (! (stock.getSerialNumber() == null)){
            serialNumberTextField.setText(stock.getSerialNumber().toString());
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        partNumberTextField = new javax.swing.JTextField();
        serialNumberTextField = new javax.swing.JTextField();
        assetTagTextField = new javax.swing.JTextField();
        rmaNumberTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(client.MainFrame.class).getContext().getResourceMap(AddStockDialog.class);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        partNumberTextField.setText(resourceMap.getString("partNumberTextField.text")); // NOI18N
        partNumberTextField.setName("partNumberTextField"); // NOI18N
        partNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partNumberTextFieldActionPerformed(evt);
            }
        });

        serialNumberTextField.setText(resourceMap.getString("serialNumberTextField.text")); // NOI18N
        serialNumberTextField.setName("serialNumberTextField"); // NOI18N

        assetTagTextField.setText(resourceMap.getString("assetTagTextField.text")); // NOI18N
        assetTagTextField.setName("assetTagTextField"); // NOI18N

        rmaNumberTextField.setText(resourceMap.getString("rmaNumberTextField.text")); // NOI18N
        rmaNumberTextField.setName("rmaNumberTextField"); // NOI18N

        saveButton.setText(resourceMap.getString("saveButton.text")); // NOI18N
        saveButton.setName("saveButton"); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(serialNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(partNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(assetTagTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(rmaNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(92, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton)
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, saveButton});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {assetTagTextField, partNumberTextField});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(partNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(serialNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(assetTagTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rmaNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cancelButton, saveButton});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {assetTagTextField, partNumberTextField, rmaNumberTextField, serialNumberTextField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void partNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partNumberTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_partNumberTextFieldActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add save handling code here:

        String partNumber = partNumberTextField.getText().toString();
        String rmaNumber = rmaNumberTextField.getText().toString();
        String serialNumber = serialNumberTextField.getText().toString();
        String assetTag = assetTagTextField.getText().toString();


        Stock newStock = currentStock.get(0);
        Item item = currentItem.get(0);
        Inventory inventory = currentInventory.get(0);
       
        String errorMessage = "";

        if((partNumber.equals("")  || partNumber.equals("Enter part number")) || partNumber.isEmpty())
        {
            errorMessage += "Please enter a part number\n";
           
        }

        if ((rmaNumber.equals("")) || rmaNumber.equals("Enter rma number") || rmaNumber.isEmpty())
        {
            errorMessage += "Please enter a rma number\n";
            
        }

        if(serialNumber.equals("") || serialNumber.equals("Enter serial number") || serialNumber.isEmpty())
        {
            errorMessage += "Please enter a serial number\n";
       
        }

        if (assetTag.equals("") || assetTag.equals("Enter asset tag") || assetTag.isEmpty())
        {
            errorMessage += "Please enter a asset tag\n";
            
        }

        if(errorMessage.length() > 0) {

            JOptionPane.showMessageDialog(this,errorMessage,
                                        "Required parameters missing",
                                        JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            newStock.setPartNumber(partNumber);
            newStock.setAssetTag(assetTag);
            newStock.setSerialNumber(serialNumber);
            newStock.setRmaNumber(rmaNumber);
            newStock.setItemId(item.getId());
            
            insertStock(item,inventory,newStock);

            dispose();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

 private void insertStock(Item item,Inventory inventory,Stock newStock){


        final AddStockDialog frame = this;
        WorkletContext context = WorkletContext.getInstance();

        //StockTableModel model = (StockTableModel) stockTable.getModel();

        String criteria = "/list[inventoryId=" + inventory.getId().toString() +"]";
        ArrayList<Item> items = Helper.query("Inventories", criteria, context);


        for(int i=0; i<items.size(); i++) {
               Item nextItem = items.get(i);

               if (nextItem.getId().toString().equals(item.getId().toString()))
               {
                    //query the stock for the item
                    //Print all items in the inventory
                    String criteria3 = "/list[itemId=" + item.getId().toString() + "]";

                    item.insert(newStock);

                             System.out.println(newStock.getId());

                             if (!Helper.insert(newStock, "Inventories", context)) {

                                 JOptionPane.showMessageDialog(frame, "insert Item into Inventory failed!",
                                    "Stocks", JOptionPane.ERROR_MESSAGE);
                                 return;

                             }// end if

                                System.out.println(newStock.getId());

                    }// end if
                }// end for

    }// end insert




    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add cancel handling code here:
        
         dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Attempt to set the appearance to the system default


                AddStockDialog dialog = new AddStockDialog(new javax.swing.JDialog(),null, null , null, true);
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
    private javax.swing.JTextField assetTagTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField partNumberTextField;
    private javax.swing.JTextField rmaNumberTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField serialNumberTextField;
    // End of variables declaration//GEN-END:variables

   

}
