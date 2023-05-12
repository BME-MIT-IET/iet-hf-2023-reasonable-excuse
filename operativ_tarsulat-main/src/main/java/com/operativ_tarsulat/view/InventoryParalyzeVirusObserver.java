package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.ParalyzeVirus;

import java.io.Serializable;

import javax.swing.*;

public class InventoryParalyzeVirusObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    ParalyzeVirus subject;

    /**
     * Constructor.
     * @param subject
     */
    public InventoryParalyzeVirusObserver(ParalyzeVirus subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getGotParalyzeLabel().setIcon(new ImageIcon("paralyzeEmblem.png"));
        MainWindow.getInstance().getGotParalyzeLabel().revalidate();
    }
}
