package com.operativ_tarsulat.model;

import com.operativ_tarsulat.view.AxeObserver;

import java.io.Serializable;

public class Axe extends Gear implements Serializable, Weapon{
    private final GearSlot slot = GearSlot.Weapon;
    private Boolean hasAttacked = false;


    /**
     * Calls the blank Axe constructor
     */
    Axe() {
        AddObserver(new AxeObserver(this));

    }
    /**
     * @return The description of the class
     */
    public String toString(){
        return "Axe, used: "+hasAttacked;
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
    /**
     *
     * @param v The Virologist who wants to reach another Virologist
     * @param i An Agent, which v wants to use
     * @param v2 The targeted Virologist
     * @return false, because v can reach v2
     */
    public Boolean HandleTouch(Virologist v, Agent i, Virologist v2) {        
        return false;
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
     * @param v The Virologist who owns the Bag
     * @return a number, which shows how much the capacity increased
     */
    public int HandleInventoryCapacity(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getClass().getName());
        Skeleton.LogReturn(Integer.toString(0));
        return 0;
    }

    /**
     *
     * @param v The Virologist who we are examining to see if it is paralyzed, because of the Axe
     * @return false because it isn't
     */
    public Boolean HandleParalyzed(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getClass().getName());
        Skeleton.LogReturn("false");
        return false;
    }

    /**
     *
     * @param v The Virologist who we are examining to see if it can create an Agent, because of the BAxe
     * @param code The GeneticCode which it wants to use to create an Agent
     * @return false, because it can create Agent
     */
    public Boolean HandleCreateAgent(Virologist v, GeneticCode code) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getClass().getName());
        Skeleton.LogReturn("false");
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
     * @return The Slot which contains the Axe
     */
    public GearSlot GetSlot() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        Skeleton.LogReturn(slot.toString());
        return slot;
    }


    /**
     * Handles an Attack
     * @param v The Virologist, who is being attacked
     */
    public void Attack(Virologist v){
        if(!hasAttacked){
            v.Attacked();
            hasAttacked = true;
        }
    }
}
