package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.ProtectionVaccine;

import java.io.Serializable;

import javax.swing.*;

public class ActiveProtectionVaccineObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    ProtectionVaccine subject;

    /**
     * Constructor.
     * @param subject
     */
    public ActiveProtectionVaccineObserver(ProtectionVaccine subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getActiveProtectionLabel().setIcon(new ImageIcon("protectionEmblem.png"));
        MainWindow.getInstance().getActiveProtectionLabel().revalidate();
    }
}
