/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.workplicity.inventorycontrol.repos;

import org.workplicity.inventorycontrol.entry.Training;
import org.workplicity.repos.Repository;

/**
 *
 * @author SHAN
 */
public class Trainings extends Repository<Training> {

     public final static String TITLE = "Trainings";

    /**
     * Constructor
     */
    public Trainings() {
        super(TITLE);
    }
}
