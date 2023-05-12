package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.AmnesiaVirus;

import java.io.Serializable;

import javax.swing.*;

public class InventoryAmnesiaVirusObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    AmnesiaVirus subject;

    /**
     * Constructor.
     * @param subject
     */
    public InventoryAmnesiaVirusObserver(AmnesiaVirus subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getGotAmnesiaLabel().setIcon(new ImageIcon("amnesiaEmblem.png"));
        MainWindow.getInstance().getGotAmnesiaLabel().revalidate();
    }
}
