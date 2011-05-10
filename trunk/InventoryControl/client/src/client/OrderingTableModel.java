/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import org.workplicity.util.DateFormatter;
import org.workplicity.worklet.WorkletContext;
import org.workplicity.inventorycontrol.entry.OrderAudit;
import org.workplicity.util.Helper;

/**
 *
 * @author SandeepMJ
 */

public class OrderingTableModel extends AbstractTableModel {

   ArrayList<OrderAudit> orders = new ArrayList<OrderAudit>();
   private HashMap<Integer,OrderAudit> dirty = new HashMap<Integer,OrderAudit>( );
   WorkletContext context = WorkletContext.getInstance();
    /**
     * Names of the columns
     */
    private static String[] columnNames = {
        "Id",
        "OrderDate",
        "Size",
        "PO#",
        "Order#"
    };

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }


    public int getRowCount() {
        return orders.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

     @Override
    public boolean isCellEditable(int row, int col) {
        if(col == 2)
            return true;
        if(col == 3)
            return true;

        return false;
    }


   public Object getValueAt(int rowIndex, int columnIndex) {

        OrderAudit order = orders.get(rowIndex);

        try {
            switch(columnIndex) {
                case 0:
                        String indicator = "";
                        Integer id = order.getId();
                        if(dirty.get(id) != null)
                        indicator = "* ";
                        return id + indicator;

                case 1: return DateFormatter.toString(order.getStamp());

                case 2: return order.getOrdersize();

                case 3: return order.getPoNumber();

                case 4: return order.getOrderNumber();

             }
        }
        catch(Exception e) {
            System.out.println("Order did not render..");
        }

        return null;
    }

/**
     * Get a stock by row;
     * @param row Row
     * @return Stock
     */
    public OrderAudit getRow(int row) {

        if(row < 0 || row >= orders.size())
            return null;

        return orders.get(row);
    }

    /**
     * Removes a stock from the table
     */
    public void remove(int row) {
        orders.remove(row);
    }

     @Override
    public void setValueAt(Object value, int row, int col) {

         OrderAudit order = getRow(row);

        switch(col) {
            case 2: int size = 0;
                    try
                    {
                    size = Integer.parseInt(value.toString());
                    }catch(Exception ex){

                        JOptionPane.showMessageDialog(null,"Orders size must be a positive integer.",
                                        "Required parameters missing",
                                        JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    order.setOrdersize(size);
                    break;
            case 3:
                   order.setPoNumber((String)value);
                   break;

            case 4:
                   order.setOrderNumber((String)value);
                   break;

        }

        dirty.put(order.getId(),order);

        this.fireTableDataChanged();

    }

    /**
     * Refreshes the table of stock;
     */
    public void refresh() {

        try {

            String criteria = "/list";


            orders = Helper.query("Orders", criteria, context);

            dirty.clear();
            this.fireTableDataChanged();

        }
        catch (Exception e) {
            System.out.println("Trainings refresh failed..");
        }
    }



    public HashMap<Integer,OrderAudit> getDirty() {
        return dirty;
    }

}
