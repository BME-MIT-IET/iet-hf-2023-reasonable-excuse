package com.operativ_tarsulat.view;

import java.awt.Component;
import java.io.Serializable;

import com.operativ_tarsulat.model.City;
import com.operativ_tarsulat.model.FreeField;
public class CityObserver implements Observer, Serializable{
    private City subject;
    private FieldPanel panel;

    /**
     * Constructor for the CityObserver
     * @param city subject of the observer
     */
    public CityObserver(City city){
        subject = city;
        panel = new FieldPanel();
        panel.setNames(updateNames());
        panel.setImage("city.png");
        MainWindow.getInstance().getMapPanel().add(panel);
    }

    /**
     * Function that is called when the subject is changed.
     */
    @Override
    public void Update() {
    	MapPanel map = MainWindow.getInstance().getMapPanel();
    	for(Component c : map.getComponents())
    	{
    		if(c == panel) {
    			map.remove(panel);
    		}
    	}
    	panel = new FieldPanel();
    	map.add(panel);
    	panel.setImage("city.png");
        panel.setNames(updateNames());
        panel.setPosX(subject.getPosX());
        panel.setPosY(subject.getPosY());
        panel.UpdateBounds();
    }

    /**
     * Function that updates the names of the fields
     * @return the names of the fields
     */
    private String[] updateNames(){
        String[] names = new String[subject.GetVirologists().length];
        for(int i = 0; i<subject.GetVirologists().length;i++)
            names[i] = subject.GetVirologists()[i].getName();
        return names;
    }

}
