package com.operativ_tarsulat.model;

import java.io.Serializable;

public class Shelter implements Building, Serializable {
    private Gear localGear;

	/**
	 * @return The description of the class
	 */
	public String toString() {
		return "Shelter "+" gear in the shelter: " + localGear;
	}
    /**
     * The constructor of the Shelter class.
     * @param localG This gear will be stored here.
     */
    public Shelter(Gear localG)
    {
    	Skeleton.LogFunctionCall("Shelter ctr");
    	localGear = localG;
    	Skeleton.LogReturn();
    }
    public Shelter() {
    	Skeleton.LogFunctionCall("Shelter ctr");
    	Skeleton.LogReturn();
    }
    /**
     * The virologist wants to pick up or change the local gear.
     * @param v The virologist who wants to interact with the Shelter.
     */
    public void Interact(Virologist v) {
    	Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),v.getName(),v.getClass().getName());
    	if(localGear!=null)
    	{
    		Gear g=v.GetGear(localGear);
    		localGear = g;
    	}
    	Skeleton.LogReturn();
    }
    /**
     * This function set a new gear that will be stored here.
     * @param g The new gear that will be stored.
     */
    public void SetGear(Gear g) {
    	Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),g.getClass().getName());
    	localGear=g;
    	Skeleton.LogReturn();
    }
}
