package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.ProtectionVaccine;

import java.io.Serializable;

import javax.swing.*;

public class InventoryProtectionVaccineObserver implements Observer, Serializable {
    /**
     * Observed object.
     */
    ProtectionVaccine subject;

    /**
     * Constructor.
     * @param subject
     */
    public InventoryProtectionVaccineObserver(ProtectionVaccine subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getGotProtectionLabel().setIcon(new ImageIcon("protectionEmblem.png"));
        MainWindow.getInstance().getGotProtectionLabel().revalidate();
    }
}
