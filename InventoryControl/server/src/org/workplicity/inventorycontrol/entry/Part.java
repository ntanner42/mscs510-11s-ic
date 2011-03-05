/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.entry;

/**
 *
 * @author SHAN
 */
public class Part extends Stock {

    private static final long serialVersionUID = -6482381378329769196L;
    private Integer modelId;

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
}
