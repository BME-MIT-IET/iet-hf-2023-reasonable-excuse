package com.operativ_tarsulat.model;

import com.operativ_tarsulat.view.DanceGeneticCodeObserver;

import java.io.Serializable;

public class DanceGeneticCode extends GeneticCode implements Serializable {

    /**
     * @return The description of the class
     */
    public String toString(){
        return "DanceGeneticCode "+ super.toString();
    }
    /**
     * Calls the GeneticCode blank constructor
     */
    DanceGeneticCode() {
        super();
        SetDuration(3);
        AddObserver(new DanceGeneticCodeObserver(this));
        Skeleton.LogFunctionCall("DanceGeneticCode ctr");
        Skeleton.LogReturn();
    }

    /**
     *
     * @param a The amino cost of this GeneticCode
     * @param n The nucleo cost of this GeneticCode
     */
    DanceGeneticCode(int a, int n) {
        super(a, n);
        SetDuration(3);
        AddObserver(new DanceGeneticCodeObserver(this));
        Skeleton.LogFunctionCall("DanceGeneticCode ctr", String.valueOf(a), String.valueOf(n));
        Skeleton.LogReturn();
    }

    /**
     *
     * @param v The virologist that this Agent will belong to
     * @return The newly created Agent
     */
    public DanceVirus CreateInstance(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Skeleton.LogReturn(this.getClass().getName());
        return new DanceVirus(duration, v);
    }

    /**
     * Sets the value that belongs to this genetic code to true in the given CheckList
     * @param ls The list which will be set
     */
    public void CheckList(GeneticCodeCheckList ls) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), ls.getClass().getName());
        ls.Dance = true;
        Skeleton.LogReturn();
    }
}
