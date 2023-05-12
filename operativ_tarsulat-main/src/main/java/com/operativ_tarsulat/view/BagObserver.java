package com.operativ_tarsulat.view;


import com.operativ_tarsulat.model.Bag;

import java.io.Serializable;

import javax.swing.*;

public class BagObserver implements Observer, Serializable{
    /**
     * Observed object.
     */
    Bag subject;

    /**
     * Constructor.
     * @param subject
     */
    public BagObserver(Bag subject) {
        this.subject = subject;
    }

    /**
     * Function that is called when the subject is changed.
     */
    public void Update(){
        MainWindow.getInstance().getGotBagLabel().setIcon(new ImageIcon("bag.png"));
        MainWindow.getInstance().getGotBagLabel().revalidate();
    }
}
