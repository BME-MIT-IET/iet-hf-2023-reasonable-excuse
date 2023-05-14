package com.operativ_tarsulat.model;

import java.io.Serializable;

public class Warehouse implements Building, Serializable {
	
	
	private int nucleo;
	private int amino;

	/**
	 * @return The description of the class
	 */
	public String toString() {
		return "Warehouse"+ " nucleo: " + nucleo + " amino: " + amino;
	}
	/**
     * The constructor of the Warehouse class
     * @param nuc The amount of nucleo that will be stored 
     * @param am The amount of amino that will be stored 
     */
	public Warehouse(int nuc,int am)
	{
		Skeleton.LogFunctionCall("Warehouse ctr");
		nucleo = nuc;
		amino = am;
		Skeleton.LogReturn();
	}
	public Warehouse() {
		Skeleton.LogFunctionCall("Warehouse ctr");
		Skeleton.LogReturn();
	}
	/**
     * Set the new amount of amino.
     * @param amount: This will be the new amount of amino. 
     */
	public void SetAmino(int amount) {
		Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
		amino = amount;
		Skeleton.LogReturn();
	}
	/**
     * This function set new amount for nucleo.
     * @param amount: This will be the new amount of nucleo. 
     */
	public void SetNucleo(int amount) {
		Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
		nucleo = amount;
		Skeleton.LogReturn();
	}
	/**
     * The virologist recieves as much materials as possible. 
     * @param v The virologist who wants to get materials.
     */
    public void Interact(Virologist v) {
    	Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),v.getName(),v.getClass().getName());
    	int aminoNeed=v.GetVacantAmino();
    	if(aminoNeed > amino) aminoNeed = amino;
    	int nucleoNeed=v.GetVacantNucleo();
    	if(nucleoNeed>nucleo) nucleoNeed = nucleo;
    	nucleo -= nucleoNeed;
    	amino -= aminoNeed;
		v.GetMaterialFromWarehouse(aminoNeed,nucleoNeed);
    	Skeleton.LogReturn();
    }
}
