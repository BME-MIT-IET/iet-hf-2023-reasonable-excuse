package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.ProtectiveCape;

import java.io.Serializable;

import javax.swing.*;

public class ProtectiveCapeObserver implements Observer, Serializable{
    /**
     * Observed object.
     */
    ProtectiveCape subject;

    /**
     * Constructor.
     * @param subject
     */
    public ProtectiveCapeObserver(ProtectiveCape subject){
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getGotProtectiveCapeLabel().setIcon(new ImageIcon("cloak.png"));
        MainWindow.getInstance().getGotProtectiveCapeLabel().revalidate();
    }
}
