package com.operativ_tarsulat.model;

import java.io.Serializable;

public class GeneticCodeCheckList {
    public Boolean Protection = false;
    public Boolean Amnesia = false;
    public Boolean Paralyze = false;
    public Boolean Dance = false;

    /**
     * @return The description of the class
     */
    public String toString(){
        return "Protection: "+Protection+"Amnesia: "+Amnesia+"Paralyze: "+Paralyze+"Dance: "+Dance;
    }
    public void CheckEndCondition() {
    	Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
    	if(Protection&&Amnesia&&Paralyze&&Dance) {
    		Game.GetInstance().EndGame();
    	}
    	Skeleton.LogReturn();
    	
    }
}
