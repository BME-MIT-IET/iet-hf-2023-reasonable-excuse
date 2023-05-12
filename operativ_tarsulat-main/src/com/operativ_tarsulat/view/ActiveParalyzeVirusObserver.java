package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.ParalyzeVirus;

import java.io.Serializable;

import javax.swing.*;

public class ActiveParalyzeVirusObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    ParalyzeVirus subject;

    /**
     * Constructor.
     * @param subject Observed object.
     */
    public ActiveParalyzeVirusObserver(ParalyzeVirus subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getActiveParalyzeLabel().setIcon(new ImageIcon("paralyzeEmblem.png"));
        MainWindow.getInstance().getActiveParalyzeLabel().revalidate();
    }
}
