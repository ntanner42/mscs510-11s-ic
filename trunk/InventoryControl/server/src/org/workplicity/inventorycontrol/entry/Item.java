/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

import java.util.ArrayList;
import org.workplicity.entry.Entry;
import org.workplicity.repos.Repository;

/**
 *
 * @author SHAN
 */
public class Item extends Entry {

    private static long serialVersionUID = -6482381378329769196L;

    public enum Status {

        RECEIVED, IN_STOCK, IN_SERVICE, RETURNED, PM, REPAIR,
        RETIRED, EXPENDED
    };
    private String name;
    private String description;
    private String oem;
    private String modelNumber;
    private Integer inventoryId;
    private Repository<Stock> supplies;
    private ArrayList<Training> trainings = new ArrayList<Training>();
    private ArrayList<OrderAudit> orderHistory = new ArrayList<OrderAudit>();
    private Integer stockThreshold = 0;
    private boolean trigger = false;
    private Integer reorderEventId = -1;
    private Status countIf = Status.IN_STOCK;

    public Item() {
    }

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the oem
     */
    public String getOem() {
        return oem;
    }

    /**
     * @param oem the oem to set
     */
    public void setOem(String oem) {
        this.oem = oem;
    }

    /**
     * @return the modelNumber
     */
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * @param modelNumber the modelNumber to set
     */
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    /**
     * @return the inventoryId
     */
    public Integer getInventoryId() {
        return inventoryId;
    }

    /**
     * @param inventoryId the inventoryId to set
     */
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * @return the supplies
     */
    public Repository<Stock> getSupplies() {
        return supplies;
    }

    /**
     * @param supplies the supplies to set
     */
    public void setSupplies(Repository<Stock> supplies) {
        this.setSupplies(supplies);
    }

    /**
     * @return the stockThreshold
     */
    public Integer getStockThreshold() {
        return stockThreshold;
    }

    /**
     * @param stockThreshold the stockThreshold to set
     */
    public void setStockThreshold(Integer stockThreshold) {
        this.stockThreshold = stockThreshold;
    }

    /**
     * @return the trigger
     */
    public boolean isTrigger() {
        return trigger;
    }

    /**
     * @param trigger the trigger to set
     */
    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }

    /**
     * @return the reorderEventId
     */
    public Integer getReorderEventId() {
        return reorderEventId;
    }

    /**
     * @param reorderEventId the reorderEventId to set
     */
    public void setReorderEventId(Integer reorderEventId) {
        this.reorderEventId = reorderEventId;
    }

    /**
     * @return the countIf
     */
    public Status getCountIf() {
        return countIf;
    }

    /**
     * @param countIf the countIf to set
     */
    public void setCountIf(Status countIf) {
        this.countIf = countIf;
    }


    /**
     * @return the trainings
     */
    public ArrayList<Training> getTrainings() {
        return trainings;
    }

    /**
     * @param trainings the trainings to set
     */
    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
    }

    /**
     * @return the orderHistory
     */
    public ArrayList<OrderAudit> getOrderHistory() {
        return orderHistory;
    }

    /**
     * @param orderHistory the orderHistory to set
     */
    public void setOrderHistory(ArrayList<OrderAudit> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
