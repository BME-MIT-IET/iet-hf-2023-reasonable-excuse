package com.operativ_tarsulat.view;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class FieldPanel extends JPanel implements Serializable {
    private String image;
    private JLabel imageLabel = new JLabel();
    private JLabel namesLabel = new JLabel();
    private int posX;
    private int posY;

    /**
     * Constructor for the class FieldPanel
     */
    public FieldPanel(){
        this.add(imageLabel);
        this.add(namesLabel);
        this.setBounds(posX,posY,40,100);

        imageLabel.setBounds(0,0,40,40);
        namesLabel.setBounds(0,0,40,60);
        imageLabel.setOpaque(false);
        namesLabel.setOpaque(false);
        this.setOpaque(false);

    }

    /**
     * Updates the position of the field
     */
    public void UpdateBounds() {
    	this.setBounds(posX-20,posY-40,40,100);
    }

    /**
     * Sets the image of the field
     * @param img to set
     */
    public void setImage(String img){
        image = img;
        imageLabel.setIcon(new ImageIcon(image));
    }

    /**
     * Sets the names of the field
     * @param nameArray names to set
     */
    public void setNames(String[] nameArray){
        String names ="<html>";

        for(int i =0;i<nameArray.length;i++)
            names += nameArray[i] + "<br>";

        namesLabel.setText(names);
    }

    /**
     * Sets the X position of the field
     * @param pos to set
     */
    public void setPosX(int pos){
        posX = pos;
    }

    /**
     * Sets the Y position of the field
     * @param pos to set
     */
    public void setPosY(int pos){
        posY = pos;        
    }

}
