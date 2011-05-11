/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddTrainingDialog.java
 *
 * Created on Apr 1, 2011, 12:27:15 AM
 */

package client;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.workplicity.elog.entry.ElogUser;
import org.workplicity.entry.User;
import org.workplicity.inventorycontrol.entry.Inventory;
import org.workplicity.inventorycontrol.entry.Training;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.util.DateFormatter;
import org.workplicity.util.Helper;
import org.workplicity.util.WorkDate;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SandeepMJ
 */
public class AddTrainingDialog extends javax.swing.JDialog {

    private ArrayList<Training> currentTraining = new ArrayList<Training>( );
    private ArrayList<Inventory> currentInventory = new ArrayList<Inventory>( );
    private ArrayList<Item> currentItem = new ArrayList<Item>( );
    private ArrayList<ElogUser> users = new ArrayList<ElogUser>( );
    /** Creates new form AddTrainingDialog */
    public AddTrainingDialog(java.awt.Frame parent,Inventory inventory, Item item, Training training, boolean modal) {
        super(parent, modal);

        initComponents();
        try {
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {

            }
        this.setLocationRelativeTo(null);

        currentInventory.add(inventory);
        currentItem.add(item);
        currentTraining.add(training);

        if (training.getId() == -1 )
        {
            this.setTitle("Add new training");
        }else{
            this.setTitle("Edit training");
        }

        // custom initialization for add stock.
        init(training);
             
    }

    private void init(Training training){

        initUserComboBox();


        if (!( training.getDate() == null)){

            String trainingDate = DateFormatter.toString(training.getDate());
            trainingDateTextField.setText(trainingDate);
        }
        else
        {
            Calendar calendar = Calendar.getInstance();

            String trainingDate =DateFormatter.toString(calendar.getTime());

            trainingDateTextField.setText(trainingDate);

        }

        if (!( training.getModelId() == null)){
            modelIdTextField.setText(training.getModelId().toString());
        }
        if (! (training.getStatus() == null)){
            statusComboBox.setSelectedItem(training.getStatus().toString());
        }
        if (! (training.getUserId() == null)){
            userComboBox.setSelectedItem(training.getUserId().toString());
        }
        if (!( training.getTrainer() == null)){
            trainerTextField.setText(training.getTrainer().toString());
        }

    }

    private void initUserComboBox()
    {
        Training training = currentTraining.get(0);

        WorkletContext context = WorkletContext.getInstance();

        String criteria = "/list";

        users = Helper.query("Accounts", criteria, context);

        for(int i= 0; i < users.size();i++)
        {
            User user = users.get(i);
            userComboBox.addItem(user.getId());
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

        trainingDateTextField = new javax.swing.JTextField();
        modelIdTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        userComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        calendarButton = new javax.swing.JButton();
        trainerTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        trainingDateTextField.setEditable(false);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(client.MainFrame.class).getContext().getResourceMap(AddTrainingDialog.class);
        trainingDateTextField.setText(resourceMap.getString("trainingDateTextField.text")); // NOI18N
        trainingDateTextField.setName("trainingDateTextField"); // NOI18N
        trainingDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainingDateTextFieldActionPerformed(evt);
            }
        });

        modelIdTextField.setText(resourceMap.getString("modelIdTextField.text")); // NOI18N
        modelIdTextField.setName("modelIdTextField"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

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

        userComboBox.setName("userComboBox"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PENDING", "ONGOING", "COMPLETE" }));
        statusComboBox.setName("statusComboBox"); // NOI18N

        calendarButton.setIcon(resourceMap.getIcon("calendarButton.icon")); // NOI18N
        calendarButton.setText(resourceMap.getString("calendarButton.text")); // NOI18N
        calendarButton.setName("calendarButton"); // NOI18N
        calendarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarButtonActionPerformed(evt);
            }
        });

        trainerTextField.setText(resourceMap.getString("trainerTextField.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(modelIdTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(trainingDateTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(userComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(calendarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(trainerTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(statusComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 141, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton)))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(trainingDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(modelIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(userComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))))
                    .addComponent(calendarButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(trainerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trainingDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainingDateTextFieldActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_trainingDateTextFieldActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:

        String trainingDate = trainingDateTextField.getText().toString();
        String modelID = modelIdTextField.getText().toString();
        String userID = userComboBox.getSelectedItem().toString();
        String status = statusComboBox.getSelectedItem().toString();
        String trainer = trainerTextField.getText().toString();

        Integer convertedModelID = 0;

        Training newTraining = currentTraining.get(0);
        
        String errorMessage = "";

        if(trainingDate.equals("")  || trainingDate.isEmpty())
        {
            errorMessage += "Please select a training date.\n";
        }

        if ((modelID.equals("")) || modelID.equals("Enter model id") || modelID.isEmpty())
        {
            errorMessage += "Please enter model id.\n";

        }

        try{
            convertedModelID = Integer.parseInt(modelID);
        }catch(Exception ex){
            errorMessage += "Model id must be a positive integer value.\n";
        }
       
        if(userID.equals("") || userID.isEmpty())
        {
            errorMessage += "Please select a user id.\n";

        }

        if (status.equals("") || status.isEmpty())
        {
            errorMessage += "Please select training status.\n";

        }

        if (trainer.equals("") || trainer.equals("Enter trainer name") || trainer.isEmpty())
        {
            errorMessage += "Please enter trainer name.\n";

        }

        if(errorMessage.length() > 0) {

            JOptionPane.showMessageDialog(this,errorMessage,
                                        "Required parameters missing",
                                        JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            WorkDate date = Helper.toDate(trainingDateTextField.getText());
            newTraining.setDate(date);

            newTraining.setModelId(convertedModelID);

            if(status.equals("PENDING")){
               newTraining.setStatus(Training.Status.PENDING);
            }
            else if(status.equals("ONGOING")){
               newTraining.setStatus(Training.Status.ONGOING);
            }
            else if(status.equals("COMPLETE")){
               newTraining.setStatus(Training.Status.COMPLETE);
            }
            
            newTraining.setTrainer(trainer);
            newTraining.setUserId(Integer.parseInt(userID));

            insertTraining(newTraining);

            dispose();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void insertTraining(Training training){

        final AddTrainingDialog frame = this;
        WorkletContext context = WorkletContext.getInstance();
        Item item = currentItem.get(0);
        Inventory inventory = currentInventory.get(0);

        System.out.println(training.getId());

        if (!Helper.insert(training, "Trainings", context)) {
             JOptionPane.showMessageDialog(frame, "insert training into Trainings failed!",
                "Trainings", JOptionPane.ERROR_MESSAGE);

             return;
        }// end if

        System.out.println(training.getId()); 

        int trainingID = training.getId();
        boolean trainingExists = false;

        if(!(item.getTrainings().isEmpty() ))
        {
            for(int i=0; i < item.getTrainings().size();i++)
            {
                int itemTrainingID = item.getTrainings().get(i).getId();
                
                if ((itemTrainingID == trainingID))
                {
                    trainingExists = true;
                }// end if

            }// end for
        }// end if

        if(!(trainingExists))
        {
            ArrayList<Training> itemTrainings = item.getTrainings();
            
            itemTrainings.add(training);

            item.setTrainings(itemTrainings);

            inventory.insert(item);

            System.out.println(item.getId());

            if (!Helper.insert(item, "Inventories", context)) {
                System.out.println("insert Item into Inventory failed!");

            }

            System.out.println(item.getId());

        }
        
           

    }
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
         dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void calendarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarButtonActionPerformed
        // TODO add your handling code here:
         final AddTrainingDialog dialog = this;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WorkDate date = Helper.toDate(trainingDateTextField.getText());

                // displays the calendar dialog
                CalendarDialog calendar = new CalendarDialog(dialog,date);



                calendar.setVisible(true);
                if(calendar.getCalendar() == null)
                    return;

                int year = calendar.getCalendar().get(Calendar.YEAR);
                int mon = calendar.getCalendar().get(Calendar.MONTH) + 1;
                int day = calendar.getCalendar().get(Calendar.DAY_OF_MONTH);

                String mons = mon < 10 ? "0"+mon : mon+"";
                String days = day < 10 ? "0"+day : day+"";

                // sets the textfield with the date in mm/dd/yy format.
                dialog.trainingDateTextField.setText(mons + " / " + days + " / "+year);

            }
        });
    }//GEN-LAST:event_calendarButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                AddTrainingDialog dialog = new AddTrainingDialog(new javax.swing.JFrame(),null, null, null, true);
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
    private javax.swing.JButton calendarButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField modelIdTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JTextField trainerTextField;
    private javax.swing.JTextField trainingDateTextField;
    private javax.swing.JComboBox userComboBox;
    // End of variables declaration//GEN-END:variables

}
