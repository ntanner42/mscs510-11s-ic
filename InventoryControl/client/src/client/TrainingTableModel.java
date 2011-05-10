/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import org.workplicity.inventorycontrol.entry.Item;
import org.workplicity.inventorycontrol.entry.Training;
import org.workplicity.inventorycontrol.entry.Training.Status;
import org.workplicity.util.DateFormatter;
import org.workplicity.util.Helper;
import org.workplicity.worklet.WorkletContext;

/**
 *
 * @author SandeepMJ
 */
public class TrainingTableModel extends AbstractTableModel{

     ArrayList<Training> trainings = new ArrayList<Training>();
     ArrayList<Training> requiredTrainings = new ArrayList<Training>();
     private HashMap<Integer,Training> dirty = new HashMap<Integer,Training>( );
     WorkletContext context = WorkletContext.getInstance();
     /**
     * Names of the columns
     */
     private static String[] columnNames = {
        "Id",
        "Training Date",
        "Model id",
        "User",
        "Status",
        "Trainer"
        
    };

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }


    public int getRowCount() {
        return requiredTrainings.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

     @Override
    public boolean isCellEditable(int row, int col) {

         if(col == 2)
             return true;
        return false;
    }


    public Object getValueAt(int rowIndex, int columnIndex) {

        Training training = requiredTrainings.get(rowIndex);
        
        try {
            switch(columnIndex) {
                case 0:
                        String indicator = "";
                        Integer id = training.getId();
                        if(dirty.get(id) != null)
                        indicator = "* ";
                        return id + indicator;

                case 1: return DateFormatter.toString(training.getDate());

                case 2: return training.getModelId().toString();                      

                case 3: return training.getUserId().toString();

                case 4: return training.getStatus().toString();

                case 5: return training.getTrainer().toString();


            }
        }
        catch(Exception e) {
            System.out.println("Item did not render..");
        }

        return null;
    }

/**
     * Get a stock by row;
     * @param row Row
     * @return Stock
     */
    public Training getRow(int row) {

        if(row < 0 || row >= requiredTrainings.size())
            return null;

        return requiredTrainings.get(row);
    }

    /**
     * Removes a stock from the table
     */
    public void remove(int row) {
        requiredTrainings.remove(row);
    }

     @Override
    public void setValueAt(Object value, int row, int col) {

         Training training = getRow(row);

        switch(col) {
            case 2: int modelID = 0;
                    try
                    {
                    modelID = Integer.parseInt(value.toString());
                    }catch(Exception ex){

                        JOptionPane.showMessageDialog(null,"Model Id must be a positive integer.",
                                        "Required parameters missing",
                                        JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    training.setModelId(modelID);
                    break;

        }

        dirty.put(training.getId(),training);

        this.fireTableDataChanged();

    }

    /**
     * Refreshes the table of stock;
     */
    public void refresh(Item item) {

        try
        {

            String criteria = "/list";
            trainings = Helper.query("Trainings", criteria, context);

            requiredTrainings.clear();

            for(int i=0; i< trainings.size();i++)
            {

                Training training = trainings.get(i);

                if(!(item.getTrainings().isEmpty() ))
                {
                    for(int j=0; j < item.getTrainings().size();j++)
                    {
                        int itemTrainingID = item.getTrainings().get(j).getId();

                        int trainingID = training.getId();
                         if ( itemTrainingID == trainingID)
                         {
                                requiredTrainings.add(training);
                         }// end if

                    }// end for
                }// end if

            }// end for

            dirty.clear();
            this.fireTableDataChanged();

        }
        catch (Exception e) {
            System.out.println("Trainings refresh failed..");
        }
    }



    public HashMap<Integer,Training> getDirty() {
        return dirty;
    }

}
