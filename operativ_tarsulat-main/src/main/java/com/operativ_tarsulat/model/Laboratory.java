package com.operativ_tarsulat.model;

import com.operativ_tarsulat.view.LaboratoryObserver;

import java.io.Serializable;

public class Laboratory extends Field implements Serializable {
	private boolean hasBearVirus = false;
	private GeneticCode localGeneticCode;

	/**
	 * @return The description of the class
	 */
	public String toString(){
		return "Laboratory, virologists on the field: "+super.toString()+" has BearVirus: "+hasBearVirus+" genetic code in the laboratory: "+localGeneticCode;
	}

	/**
	 * The constructor of the Laboratory class 
	 * @param gc This genetic code can be found here. 
	 */
	public Laboratory(GeneticCode gc, boolean hasBearVirus) {
		Skeleton.LogFunctionCall("Laboratory ctr");
		localGeneticCode = gc;
		this.hasBearVirus = hasBearVirus;
		AddObserver(new LaboratoryObserver(this));
		Skeleton.LogReturn();
	}
	public Laboratory() {
		Skeleton.LogFunctionCall("Laboratory ctr");
		AddObserver(new LaboratoryObserver(this));
		Skeleton.LogReturn();
	}
	public boolean getHasBearVirus()
	{
		return hasBearVirus;
	}
	/**
	 * Set a new genetic code. 
	 * @param gc This will be the new genetic code that can be found here.
	 */
    public void SetGeneticCode(GeneticCode gc) {
    	Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),gc.getClass().getName());
    	localGeneticCode = gc;
    	Skeleton.LogReturn();
    }
    
    /**
     * A virologist learns  the local genetic code. 
     * @param v The virologist who wants to learn the genetic code. 
     */
    public void Interact(Virologist v) {
    	Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),v.getName());
    	v.LearnGeneticCode(localGeneticCode);
		if (hasBearVirus) {
			v.ReceiveAgentUse(new BearVirus(), null);
		}
    	Skeleton.LogReturn();
    }
}
