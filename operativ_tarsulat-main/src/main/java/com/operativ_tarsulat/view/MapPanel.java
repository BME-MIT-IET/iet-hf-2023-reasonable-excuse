package com.operativ_tarsulat.view;

import javax.swing.JPanel;

import com.operativ_tarsulat.model.Field;
import com.operativ_tarsulat.model.Game;

import java.awt.Graphics;
import java.awt.Dimension;

public class MapPanel extends JPanel {
    /**
     * Constructor of the map panel
     */
	MapPanel() {
        setPreferredSize(new Dimension(1000,300));
		this.setLayout(null);
    }

    /**
     * Paint all the components of the map
     * @param g the graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    	Field[] fields = Game.GetInstance().GetFields();
        for(Field field : fields) { // for each field
        	for(Field neighbour : field.GetNeighbours()) {        		
        		g.drawLine(field.getPosX(),field.getPosY(),neighbour.getPosX(),neighbour.getPosY());
        	}
        }
    }
}
