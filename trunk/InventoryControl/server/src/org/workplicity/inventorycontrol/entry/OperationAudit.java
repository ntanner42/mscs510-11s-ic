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
public class OperationAudit extends Entry {

    private static long serialVersionUID = -6482381378329769196L;
    private Item.Status status;
    private Date stamp;
    private Integer location;

    public OperationAudit() {
    }

    /**
     * @return the status
     */
    public Item.Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Item.Status status) {
        this.status = status;
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
        this.stamp = stamp;
    }

    /**
     * @return the location
     */
    public Integer getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Integer location) {
        this.location = location;
    }
}
