/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

import java.util.ArrayList;
import org.workplicity.entry.Entry;

/**
 *
 * @author SHAN
 */
public class Stock extends Entry {

    private static final long serialVersionUID = -6482381378329769196L;
    private String partNumber;
    private String serialNumber;
    private String assetTag;
    private String rmaNumber;
    private Integer itemId;
    private ArrayList<OperationAudit> operationHistory = new ArrayList<OperationAudit>();

    public Stock() {
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * @param partNumber the partNumber to set
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the assetTag
     */
    public String getAssetTag() {
        return assetTag;
    }

    /**
     * @param assetTag the assetTag to set
     */
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    /**
     * @return the rmaNumber
     */
    public String getRmaNumber() {
        return rmaNumber;
    }

    /**
     * @param rmaNumber the rmaNumber to set
     */
    public void setRmaNumber(String rmaNumber) {
        this.rmaNumber = rmaNumber;
    }

    /**
     * @return the itemId
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the operationHistory
     */
    public ArrayList<OperationAudit> getOperationHistory() {
        return operationHistory;
    }

    /**
     * @param operationHistory the operationHistory to set
     */
    public void setOperationHistory(ArrayList<OperationAudit> operationHistory) {
        this.operationHistory = operationHistory;
    }
}
