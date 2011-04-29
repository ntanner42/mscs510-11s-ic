/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

import java.util.Date;
import org.workplicity.entry.Entry;

/**
 *
 * @author SHAN
 */
public class OrderAudit extends Entry {

    private static long serialVersionUID = -6482381378329769196L;
    private Date stamp;
    private Integer ordersize;
    private String poNumber;
    private String orderNumber;

    public OrderAudit() {
    }

    /**
     * @return the stamp
     */
    public Date getStamp() {
        return stamp;
    }

    /**
     * @param stamp the stamp to set
     */
    public void setStamp(Date stamp) {
        this.stamp.setTime(stamp.getTime());
    }

    /**
     * @return the ordersize
     */
    public Integer getOrdersize() {
        return ordersize;
    }

    /**
     * @param ordersize the ordersize to set
     */
    public void setOrdersize(Integer ordersize) {
        this.ordersize = ordersize;
    }

    /**
     * @return the poNumber
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     * @param poNumber the poNumber to set
     */
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
