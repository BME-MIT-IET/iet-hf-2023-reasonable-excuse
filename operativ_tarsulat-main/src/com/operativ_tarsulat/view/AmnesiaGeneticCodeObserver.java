package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.AmnesiaGeneticCode;

import java.io.Serializable;

import javax.swing.*;

public class AmnesiaGeneticCodeObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    AmnesiaGeneticCode subject;

    /**
     * Constructor.
     * @param subject
     */
    public AmnesiaGeneticCodeObserver(AmnesiaGeneticCode subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getAmnesiaLabel().setIcon(new ImageIcon("amnesiaEmblem.png"));
        MainWindow.getInstance().getAmnesiaLabel().revalidate();
    }
}
