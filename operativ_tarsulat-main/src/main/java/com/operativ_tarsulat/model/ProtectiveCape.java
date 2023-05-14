package com.operativ_tarsulat.model;

import com.operativ_tarsulat.view.ProtectiveCapeObserver;

import java.io.Serializable;
import java.util.Random;

public class ProtectiveCape extends Gear implements Serializable {
    private final GearSlot slot = GearSlot.ProtectiveCape;
    private static Random rand = new Random();
    /**
     * Calls the blank ProtectiveCape constructor
     */
    ProtectiveCape() {
        AddObserver(new ProtectiveCapeObserver(this));

    }
    /**
     * @return The description of the class
     */
    public String toString(){
        return "ProtectiveCape";
    }

    /**
     *
     * @param v The Virologist who wants to reach another Virologist
     * @param i An Agent, which v wants to use
     * @param v2 The targeted Virologist
     * @return true in 82,3% of the cases
     */
    public Boolean HandleTouch(Virologist v, Agent i, Virologist v2) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName(), i.getClass().getName(), v2.getName());
    	//Generating a random number (0-999)
    	int num = rand.nextInt(1000);
    	//If the number is under 823 it returns true (82.3%)
    	if(num < 823){
            Skeleton.LogReturn("true");
    	    return true;
        }
    	// In other cases it's return false
        Skeleton.LogReturn("false");
    	return false;
    }

    /**
     *
     * @param v The Virologist who wants to move
     * @return false, because v can move
     */
    public Boolean HandleMove(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getClass().getName());
        Skeleton.LogReturn("false");
    	return false;
    }

    /**
     *
     * @param v The Virologist who owns the ProtectiveCape
     * @return a number, which shows how much the capacity increased, it's 0 because the Gloves doesn't provide bigger capacity
     */
    public int HandleInventoryCapacity(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getClass().getName());
        Skeleton.LogReturn(Integer.toString(0));
    	return 0;
    }

    /**
     *
     * @param v The Virologist who we are examining to see if it is paralyzed, because of the ProtectiveCape
     * @return false because it isn't
     */
    public Boolean HandleParalyzed(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getClass().getName());
        Skeleton.LogReturn("false");
    	return false;
    }

    /**
     *
     * @param v The Virologist who we are examining to see if it can create an Agent, because of the ProtectiveCape
     * @param code The GeneticCode which it wants to use to create an Agent
     * @return false, because it can create Agent
     */
    public Boolean HandleCreateAgent(Virologist v, GeneticCode code) {
        return false;
    }

    /**
     * Handles the start of the turn
     * @param v The Virologist, whose turn starts
     */
    public void HandleTurnStart(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Skeleton.LogReturn();
    }

    /**
     *
     * @return The Slot which contains the ProtectiveCape
     */
    public GearSlot GetSlot() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        Skeleton.LogReturn(slot.toString());
        return slot;
    }

    /**
     * Handles movement of a Virologist to a new Field
     * @param v The Virologist who moves
     * @param f the Field, where the Virologist moves to
     */
    public void HandleMovedToField(Virologist v, Field f) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Skeleton.LogReturn();
    }

    /**
     * Handles a Virologist getting materials from a Warehouse
     * @param v The Virologist, who gets materials
     */
    public Boolean HandleGetMaterialFromWarehouse(Virologist v){
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Skeleton.LogReturn("false");
        return false;
    }
}
