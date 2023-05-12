package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.AmnesiaVirus;

import java.io.Serializable;

import javax.swing.*;

public class ActiveAmnesiaVirusObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    AmnesiaVirus subject;

    /**
     * Constructor.
     * @param subject
     */
    public ActiveAmnesiaVirusObserver(AmnesiaVirus subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getActiveAmnesiaLabel().setIcon(new ImageIcon("amnesiaEmblem.png"));
        MainWindow.getInstance().getActiveAmnesiaLabel().revalidate();
    }
}
