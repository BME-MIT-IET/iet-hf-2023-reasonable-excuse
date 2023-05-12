package com.operativ_tarsulat.model;

import com.operativ_tarsulat.view.ProtectionGeneticCodeObserver;

import java.io.Serializable;

public class ProtectionGeneticCode extends GeneticCode implements Serializable {

    /**
     * @return The description of the class
     */
    public String toString(){
        return "ProtectionGeneticCode "+ super.toString();
    }
    /**
     * Calls the GeneticCode blank constructor
     */
    ProtectionGeneticCode() {
        super();
        SetDuration(2);
        AddObserver(new ProtectionGeneticCodeObserver(this));
        Skeleton.LogFunctionCall("ProtectionGeneticCode ctr");
        Skeleton.LogReturn();
    }

    /**
     *
     * @param a The amino cost of this GeneticCode
     * @param n The nucleo cost of this GeneticCode
     */
    ProtectionGeneticCode(int a, int n) {
        super(a, n);
        SetDuration(2);
        AddObserver(new ProtectionGeneticCodeObserver(this));
        Skeleton.LogFunctionCall("ProtectionGeneticCode ctr", String.valueOf(a), String.valueOf(n));
        Skeleton.LogReturn();
    }

    /**
     *
     * @param v The virologist that this Agent will belong to
     * @return The newly created Agent
     */
    public ProtectionVaccine CreateInstance(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        Skeleton.LogReturn(this.getClass().getName());
        return new ProtectionVaccine(duration, v);
    }

    /**
     * Sets the value that belongs to this genetic code to true in the given CheckList
     * @param ls The list which will be set
     */
    public void CheckList(GeneticCodeCheckList ls) {
    	Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), ls.getClass().getName());
        ls.Protection = true;
        Skeleton.LogReturn();
    }
}
