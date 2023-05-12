package com.operativ_tarsulat.view;

import com.operativ_tarsulat.model.Gloves;

import java.io.Serializable;

import javax.swing.*;

public class GlovesObserver implements Observer, Serializable{
    /**
     * Observed object.
     */
    Gloves subject;

    /**
     * Constructor.
     * @param subject
     */
    public GlovesObserver(Gloves subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getGotGlovesLabel().setIcon(new ImageIcon("gloves.png"));
        MainWindow.getInstance().getGotGlovesLabel().revalidate();
    }
}
