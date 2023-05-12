package com.operativ_tarsulat.model;

import com.operativ_tarsulat.view.ActiveAmnesiaVirusObserver;
import com.operativ_tarsulat.view.ActiveBearVirusObserver;
import com.operativ_tarsulat.view.Observer;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class BearVirus extends Agent implements Serializable {

    /**
     * @return The description of the class
     */
    public String toString(){
        return "BearVirus "+super.toString();
    }
    /**
     * Calls the blank Agent constructor
     */
    BearVirus() {
        super();
        AddObserver(new ActiveBearVirusObserver(this));
        Skeleton.LogFunctionCall("BearVirus ctr");
        this.SetDuration(-1);
        Skeleton.LogReturn();
    }

    /**
     *
     * @param d The duration of this Agent
     * @param v The virologist that this Agent belongs to
     */
    BearVirus(int d, Virologist v) {
        super(d, v);
        AddObserver(new ActiveBearVirusObserver(this));
        Skeleton.LogFunctionCall("BearVirus ctr", String.valueOf(d), v.getName());
        Skeleton.LogReturn();
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
     *
     * @param v The Virologist whose inventory we want to check
     * @return a number, which shows how much the capacity increased, it's 0 because this effect doesn't provide bigger capacity
     */
    public int HandleInventoryCapacity(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Skeleton.LogReturn("0");
        return 0; }

    /**
     *
     * @param v The Virologist who wants to move
     * @return true, because v can't move
     */
    public Boolean HandleMove(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Skeleton.LogReturn("false");
        return false;
    }

    /**
     *
     * @param v The Virologist who we are examining to see if they are paralyzed
     * @return false because they aren't paralyzed
     */
    public Boolean HandleParalyzed(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Skeleton.LogReturn("false");
        return false;
    }

    /**
     *
     * @param v The Virologist who we are examining to see if it can create an Agent
     * @param code The GeneticCode which it wants to use to create an Agent
     * @return false, because it can create Agent
     */
    public Boolean HandleCreateAgent(Virologist v, GeneticCode code) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName(), code.getClass().getName());
        Skeleton.LogReturn("false");
        return false;
    }

    /**
     * Handles the start of the turn, moves to a random neighbour field
     * @param v The Virologist, whose turn starts
     */
    public void HandleTurnStart(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        List<Field> neighbours = v.GetField().GetNeighbours();
        Random rand = new Random();
        if (neighbours.size() == 1) {
            v.Move(neighbours.get(0));
        } else {
            v.Move(neighbours.get(rand.nextInt(neighbours.size()-1)));
        }
        Skeleton.LogReturn();
    }

    /**
     * Handles movement of a Virologist to a new Field, tries to infect virologists in the Field with BearVirus
     * @param v The Virologist who moves
     * @param f the Field, where the Virologist moves to
     */
    public void HandleMovedToField(Virologist v, Field f) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Virologist[] virologists = f.GetVirologists();
        for (Virologist v2 : virologists) {
            if (v2 != null && v2 != v) {
                v2.ReceiveAgentUse(new BearVirus(), v);
            }
        }
        Skeleton.LogReturn();
    }

    /**
     * Handles a Virologist getting materials from a Warehouse
     * @param v The Virologist, who gets materials
     */
    public Boolean HandleGetMaterialFromWarehouse(Virologist v){
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Skeleton.LogReturn("true");
        return true;
    }
	@Override
	protected Observer CreateActiveObserver() {
		return new ActiveBearVirusObserver(this);
	}
}
