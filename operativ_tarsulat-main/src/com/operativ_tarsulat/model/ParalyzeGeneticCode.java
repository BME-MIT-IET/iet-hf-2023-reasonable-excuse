package com.operativ_tarsulat.model;

import com.operativ_tarsulat.view.ParalyzeGeneticCodeObserver;

import java.io.Serializable;

public class ParalyzeGeneticCode extends GeneticCode implements Serializable {

    /**
     * @return The description of the class
     */
    public String toString(){
        return "ParalyzeGeneticCode "+ super.toString();
    }
    /**
     * Calls the GeneticCode blank constructor
     */
    ParalyzeGeneticCode() {
        super();
        SetDuration(1);
        AddObserver(new ParalyzeGeneticCodeObserver(this));
        Skeleton.LogFunctionCall("ParalyzeGeneticCode ctr");
        Skeleton.LogReturn();
    }

    /**
     *
     * @param a The amino cost of this GeneticCode
     * @param n The nucleo cost of this GeneticCode
     */
    ParalyzeGeneticCode( int a, int n) {
        super(a, n);
        SetDuration(1);
        AddObserver(new ParalyzeGeneticCodeObserver(this));
        Skeleton.LogFunctionCall("ParalyzeGeneticCode ctr", String.valueOf(a), String.valueOf(n));
        Skeleton.LogReturn();
    }

    /**
     *
     * @param v The virologist that this Agent will belong to
     * @return The newly created Agent
     */
    public ParalyzeVirus CreateInstance(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Skeleton.LogReturn(this.getClass().getName());
        return new ParalyzeVirus(duration, v);
    }


    /**
     * Sets the value that belongs to this genetic code to true in the given CheckList
     * @param ls The list which will be set
     */
    public void CheckList(GeneticCodeCheckList ls) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), ls.getClass().getName());
        ls.Paralyze = true;
        Skeleton.LogReturn();
    }
}
