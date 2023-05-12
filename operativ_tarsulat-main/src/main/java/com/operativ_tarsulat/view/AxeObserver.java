package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.Axe;

import java.io.Serializable;

import javax.swing.*;

public class AxeObserver implements Observer, Serializable{
    /**
     * Observed object.
     */
    Axe subject;

    /**
     * Constructor.
     * @param subject
     */
    public AxeObserver(Axe subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getGotAxeLabel().setIcon(new ImageIcon("axe.png"));
        MainWindow.getInstance().getGotAxeLabel().revalidate();
    }
}
