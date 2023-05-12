package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.ProtectionGeneticCode;

import java.io.Serializable;

import javax.swing.*;

public class ProtectionGeneticCodeObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    ProtectionGeneticCode subject;

    /**
     * Constructor.
     * @param subject
     */
    public ProtectionGeneticCodeObserver(ProtectionGeneticCode subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getProtectionLabel().setIcon(new ImageIcon("protectionEmblem.png"));
        MainWindow.getInstance().getProtectionLabel().revalidate();
    }
}
