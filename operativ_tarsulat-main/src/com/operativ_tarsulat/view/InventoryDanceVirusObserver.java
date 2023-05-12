package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.DanceVirus;

import java.io.Serializable;

import javax.swing.*;

public class InventoryDanceVirusObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    DanceVirus subject;

    /**
     * Constructor.
     * @param subject
     */
    public InventoryDanceVirusObserver(DanceVirus subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getGotDanceLabel().setIcon(new ImageIcon("danceEmblem.png"));
        MainWindow.getInstance().getGotDanceLabel().revalidate();
    }
}
