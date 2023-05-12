package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.DanceGeneticCode;

import java.io.Serializable;

import javax.swing.*;

public class DanceGeneticCodeObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    DanceGeneticCode subject;

    /**
     * Constructor.
     * @param subject
     */
    public DanceGeneticCodeObserver(DanceGeneticCode subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getDanceLabel().setIcon(new ImageIcon("danceEmblem.png"));
        MainWindow.getInstance().getDanceLabel().revalidate();
    }
}
