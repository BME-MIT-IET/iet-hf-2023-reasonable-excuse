package com.operativ_tarsulat.model;

import java.io.Console;
import java.io.Serializable;
import java.util.Random;

import com.operativ_tarsulat.view.Observer;

public abstract class Agent extends Observable implements Steppable, Effect,Serializable {
    public int Duration;
    private Virologist virologist;

    protected static Random rand = new Random();

    /**
     * Creates a blank Agent
     */
    Agent() {
        Skeleton.LogFunctionCall("Agent ctr");
        Duration = 0;
        virologist = null;
        Skeleton.LogReturn();
    }

    /**
     * @return The description of the class
     */
    public String toString(){
        if (virologist != null) {
            return "Agent: duration: "+Duration+" Virologist: "+virologist.getName();
        }
        return "Agent: duration: "+Duration;
    }

    /**
     *
     * @param d The duration of this Agent
     * @param v The virologist that this Agent will belong to
     */
    Agent(int d, Virologist v) {
        Skeleton.LogFunctionCall("Agent ctr");
        Duration = d;
        virologist = v;
        Skeleton.LogReturn();
    }

    /**
     * Decreases the duration of this Agent until it hits 0
     */
    public void DecreaseDuration() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
    	if (Duration > 0) {
            Duration--;
        }
        Skeleton.LogReturn();
    }

    /**
     * Sets the Agent's virologist
     * @param v The virologist that this Agent will belong to
     */
    public void setVirologist(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        virologist = v;
        Skeleton.LogReturn();
    }

    /**
     * Returns the Agent's virologist
     * @return The virologist of this Agent
     */
    public Virologist getVirologist() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        Skeleton.LogReturn(virologist.getName());
        return virologist;
    }

    /**
     * Calls the DecreaseDuration function
     * Removes the Agent if it's duration is 0
     */
    public void Step() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        DecreaseDuration();
        if(Duration == 0) {
            virologist.RemoveAgent(this);
        }
        Skeleton.LogReturn();
    }

    /**
     * Sets the duration of this Agent
     * @param length The length to set the duration to
     */
    public void SetDuration(int length) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), String.valueOf(length));
        Duration = length;
        Skeleton.LogReturn();
    }

	protected abstract Observer CreateActiveObserver();
}
