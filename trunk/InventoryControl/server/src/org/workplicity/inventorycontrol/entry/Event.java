/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

import org.workplicity.entry.Entry;
import org.workplicity.entry.WorkRequest;

/**
 *
 * @author briangormanly
 */
public class Event extends Entry {

    private WorkRequest template;
    private Integer workSlateId;
    private Boolean fired;
    private Boolean enabled;

    public Event() {
    }

    /**
     * @return the workSlateId
     */
    public Integer getWorkSlateId() {
        return workSlateId;
    }

    /**
     * @param workSlateId the workSlateId to set
     */
    public void setWorkSlateId(Integer workSlateId) {
        this.workSlateId = workSlateId;
    }

    /**
     * @return the fired
     */
    public Boolean getFired() {
        return fired;
    }

    /**
     * @param fired the fired to set
     */
    public void setFired(Boolean fired) {
        this.fired = fired;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return WorkOrder
     */
    public WorkRequest getTemplate() {
        return this.template;
    }

    /**
     * @param
     */
    public void setTemplate(WorkRequest template) {
        this.template = template;
    }
}
