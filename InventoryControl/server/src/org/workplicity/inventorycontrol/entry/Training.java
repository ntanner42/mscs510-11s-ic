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
public class Training extends Entry {

    private static long serialVersionUID = -6482381378329769196L;

    public enum Status {

        PENDING, COMPLETE, ONGOING
    };
    private Status status;
    private Integer modelId;
    private Integer userId;
    private Date date;
    private String trainer;

    public Training() {
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the modelId
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * @param modelId the modelId to set
     */
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the trainer
     */
    public String getTrainer() {
        return trainer;
    }

    /**
     * @param trainer the trainer to set
     */
    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
}
