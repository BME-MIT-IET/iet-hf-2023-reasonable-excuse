package com.operativ_tarsulat.model;

import com.operativ_tarsulat.view.FreeFieldObserver;

import java.io.Serializable;

public class FreeField extends Field implements Serializable {

	/**
	 * @return The description of the class
	 */
	public String toString(){
		return "FreeField, virologists on the field: "+super.toString();
	}

	public FreeField()
	{
		Skeleton.LogFunctionCall("FreeField ctr");
		AddObserver(new FreeFieldObserver(this));
		Skeleton.LogReturn();
	}
    public void Interact(Virologist v) {
    }
}
