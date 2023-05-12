package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.BearVirus;

import java.io.Serializable;

import javax.swing.*;

public class ActiveBearVirusObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    BearVirus subject;

    /**
     * Constructor.
     * @param subject
     */
    public ActiveBearVirusObserver(BearVirus subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getActiveBearLabel().setIcon(new ImageIcon("bearEmblem.png"));
        MainWindow.getInstance().getActiveBearLabel().revalidate();
    }
}
