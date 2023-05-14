package com.operativ_tarsulat.model;
import com.operativ_tarsulat.view.VirologistObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Virologist extends Observable implements Steppable, Serializable {
    private String name;
    private Field field;
    private boolean hasMoved = false;

    /**
     * @return The description of the class
     */
    public String toString() {
        return "Virologus, name: " + name;
    }
    // Virologist can possess 3 gears
    private List<Gear> gears = new ArrayList<Gear>();

    //ArrayLists that store active agents, agents in inventory and learned genetic codes.
    private List<Agent> activeAgents = new ArrayList<>();
    private List<Agent> agentInventory = new ArrayList<>();
    private List<GeneticCode> learnedGeneticCodes = new ArrayList<>();

    // integers for storing amino and nucleo count
    private int aminoCount;
    private int nucleoCount;

    private final int inventoryCapacity = 100;

    /**
     *
     * @param n Name of the virologist.
     */
    public Virologist(String n){
        Skeleton.LogFunctionCall("Virologist ctr",n);
        this.name = n;
        AddObserver(new VirologistObserver(this));
        Skeleton.LogReturn();
    }
    
    /**
     *
     * @param n Name of the virologist.
     * @param f The specific field, where the virologist stands.
     */
    public Virologist(String n, Field f){
        Skeleton.LogFunctionCall("Virologist ctr",n,f.getClass().toString());
        this.name = n;
        this.field = f;
        AddObserver(new VirologistObserver(this));
        Skeleton.LogReturn();
    }

    public Virologist(){
        Skeleton.LogFunctionCall("Virologist ctr");
        AddObserver(new VirologistObserver(this));
        Skeleton.LogReturn();
    }

    /**
     * The function sets the amino count.
     * @param amount Quantity of the aminos.
     */
    public void SetAmino(int amount) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), Integer.toString(amount));
        this.aminoCount = amount;
        Skeleton.LogReturn();
    }

    /**
     * The function sets the nucleo count.
     * @param amount Quantity of the nucleos.
     */
    public void SetNucleo(int amount) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), Integer.toString(amount));
        this.nucleoCount = amount;
        Skeleton.LogReturn();
    }
    /**
     * The function returns active agents.
     */
    public List<Agent> getActiveAgent()
    {
    	return activeAgents;
    }
    
    /**
     * The function returns active agents.
     */
    public List<Agent> getAgentInventory()
    {
    	return agentInventory;
    }
    
    /**
     * The function returns active agents.
     */
    public List<Gear> getGears()
    {
    	return gears;
    }

    /**
     * returns amino count for test purposes
     * @return
     */
    public int getAminoCount(){
        return aminoCount;
    }

    /**
     * return nucleo count for test purposes
     * @return
     */
    public int getNucleoCount(){
        return nucleoCount;
    }
    
    /**
     * The function returns active agents.
     */
    public List<GeneticCode> getGeneticCodes()
    {
    	return learnedGeneticCodes;
    }

    /**
     * This virologist tries to use agent on another virologist
     * @param v2 The virologist that will be under the effect of Agent a
     * @param a The used agent
     */
    public void UseAgent(Virologist v2, Agent a) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v2.getName());
        // Movement is basically allowed, but if an active agent on the
        // virologist blocks the virologist's ability to move it will be
        // changed to false.
        boolean movement = checkMovement();

        // if movement is allowed
        if(movement) {
        	v2.ReceiveAgentUse(a,this);
    		agentInventory.remove(a);
        }
        NotifyAllObservers();
        Skeleton.LogReturn();
    }

    /**
     * This function returns the name of the virologist
     * @return
     */
    public String getName(){
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        Skeleton.LogReturn(this.name);
        return this.name;
    }

    /**
     * Virologist receives touch from other virologist
      * @param a The agent that is used
     * @param v The other virologist
     */
    public void ReceiveAgentUse(Agent a, Virologist v) {
        //Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), a.getClass().toString(), v.getName());
        boolean allowtouch = checkTouch(a,v);
        if(allowtouch) {
            this.AddAgent(a);            
        }
        //Skeleton.LogReturn();
    }

    /**
     * Virologist learns genetic code and checks the endcondition
     * @param s The learned genetic code
     */
    public void LearnGeneticCode(GeneticCode s) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), s.getClass().toString());
        if(!learnedGeneticCodes.contains(s)){
            learnedGeneticCodes.add(s);
            GeneticCodeCheckList gcc = new GeneticCodeCheckList();
            for(int i = 0; i<learnedGeneticCodes.size();i++)
                learnedGeneticCodes.get(i).CheckList(gcc);
            s.NotifyAllObservers();
            gcc.CheckEndCondition();
        }
        Skeleton.LogReturn();
    }

    /**
     * The virologist gets a gear, returns with null if the slot was empty, otherwise returns its own gear
     * @param g
     * @return
     */
    public Gear GetGear(Gear g) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), g.getClass().toString());
        GearSlot gs = g.GetSlot();
        Gear temp;
        for(int i = 0;i<gears.size();i++)
            if(gears.get(i).GetSlot() == gs){
                temp = gears.get(i);
                gears.remove(gears.get(i));
                gears.add(g);
                g.NotifyAllObservers();
                Skeleton.LogReturn(temp.getClass().toString());
                return temp;
            }
        gears.add(g);
        g.NotifyAllObservers();
        Skeleton.LogReturn(null);
        return null;
    }

    /**
     * Removes a gear from the virologist
     * @param r
     */
    public void RemoveGear(Gear r) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),r.getClass().toString());
        gears.remove(r);
        Skeleton.LogReturn();
    }

    /**
     * Adds a genetic code to learned genetic codes
     * @param gc Genetic code to be added
     */
    public void AddGeneticCode(GeneticCode gc) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),gc.getClass().toString());
        learnedGeneticCodes.add(gc);
        gc.NotifyAllObservers();
        Skeleton.LogReturn();
    }

    /**
     * Moves the virologist from the current field to the field in the parameter if it is allowed
     * @param f2
     */
    public void Move(Field f2) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),f2.getClass().toString());
        if(!hasMoved && this.checkMovement())
            if(field.Remove(this,f2)) {
                f2.Accept(this);
                field = f2;
                for(Agent a : activeAgents) {
                    a.HandleMovedToField(this, f2);
                }
                for(Gear g : gears) {
                    g.HandleMovedToField(this, f2);
                }
            }
        hasMoved = true;
        Skeleton.LogReturn();
        
    }

    /**
     * Calls the appropriate functions after every step
     */
    public void Step() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        hasMoved = false;
        for(int i = 0;i<activeAgents.size();i++) {
            activeAgents.get(i).HandleTurnStart(this);
            activeAgents.get(i).Step();
        }
        this.NotifyAllObservers();
        Skeleton.LogReturn();
    }

    /**
     * Initiates gear stealing
     * @param v2 virologist to be stolen from
     * @param r Gear to be stolen
     */
    public void Steal(Virologist v2, Gear r) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v2.getName(),r.getClass().toString());
        if(this.checkMovement())
            v2.StolenFrom(this,r);
        Skeleton.LogReturn();
    }

    /**
     * Handles gear stealing
     * @param v the virologist who is stealing
     * @param r Gear to be stolen
     * @return
     */
    public Boolean StolenFrom(Virologist v, Gear r) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName(),r.getClass().toString());
        if(!this.checkMovement()){
            Gear g = v.GetGear(r);
            gears.remove(r);
            if(g != null) {
                gears.add(g);
                g.NotifyAllObservers();
            }
        }
        Skeleton.LogReturn("true");
        Skeleton.LogReturn("true");
        return true;
    }

    /**
     * Returns the field where the virologist is standing
     * @return
     */
    public Field GetField() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        Skeleton.LogReturn(this.field.toString());
        return this.field;
    }

    /**
     * Sets the field of the virologist
     * @param f The given field
     */
    public void SetField(Field f) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),f.getClass().toString());
        this.field = f;
        Skeleton.LogReturn();
    }

    /**
     * Initiates interaction with a field
     */
    public void InteractWithField() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        if(checkMovement())
        	field.Interact(this);
        Skeleton.LogReturn();
    }

    /**
     * Stealign material from a virologist
     * @param v Virologist to be stolen from
     */
    public void StealMaterials(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),v.getName());
        if(this.checkMovement())
            v.StolenMaterialsFrom(this);
        Skeleton.LogReturn();
    }

    /**
     * Giving away material from this virologist
     * @param v
     */
    public void StolenMaterialsFrom(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),v.getName());
        if(!this.checkMovement()){
            int aminoToGiveAway = v.GetVacantAmino();
            int nucleoToGiveAway = v.GetVacantNucleo();

            if(aminoToGiveAway > this.aminoCount)
                aminoToGiveAway = this.aminoCount; 
            if(nucleoToGiveAway > this.nucleoCount)
                nucleoToGiveAway = this.nucleoCount;

            v.GetMaterial(aminoToGiveAway,nucleoToGiveAway);            
        }
        Skeleton.LogReturn();
    }

    /**
     * Incrementing amino and nucleo count
     * @param amino
     * @param nucleo
     */
    public void GetMaterial(int amino, int nucleo) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), Integer.toString(amino), Integer.toString(nucleo));
        this.aminoCount += amino;
        this.nucleoCount += nucleo;
        this.NotifyAllObservers();
        Skeleton.LogReturn();
    }

    /**
     * This function returns with the number of free amino slots
     * @return
     */
    public int GetVacantAmino() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        int extraAmino = 0;
        for(int i = 0; i<gears.size();i++)
            extraAmino += gears.get(i).HandleInventoryCapacity(this);
        Skeleton.LogReturn(Integer.toString((inventoryCapacity + extraAmino) - aminoCount));
        return (inventoryCapacity + extraAmino) - aminoCount;
    }

    /**
     * This function returns with the number of free nucleo slots
     * @return
     */
    public int GetVacantNucleo() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        int extraNucleo = 0;
        for(int i = 0;i<gears.size();i++)
            extraNucleo += gears.get(i).HandleInventoryCapacity(this);
        Skeleton.LogReturn(Integer.toString((inventoryCapacity + extraNucleo) - aminoCount));
        return (inventoryCapacity + extraNucleo) - nucleoCount;
    }

    /**
     * Creates an agent from the given genetic code
     * @param code
     */
    public void CreateAgent(GeneticCode code) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),code.getClass().toString());
        if(checkMovement()&&!checkAmnesia(code)) {
        	int amino = code.GetAminoCost();
        	int nucleo = code.GetNucleoCost();
        	if(aminoCount>=amino&&nucleoCount>=nucleo) {
        		aminoCount-=amino;
        		nucleoCount-=nucleo;
        		Agent createdAgent = code.CreateInstance(this);
    	        agentInventory.add(createdAgent);
                createdAgent.NotifyAllObservers();
        	}	        
        }
        NotifyAllObservers();
        Skeleton.LogReturn();
    }

    /**
     * Ends the current turn
     */
    public void EndTurn() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        Game.GetInstance().NextTurn();
        Skeleton.LogReturn();

    }

    /**
     * Adds an agent to the active agents of the virologist
     * @param a
     */
    public void AddAgent(Agent a) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),a.getClass().toString());
    	a.RemoveObservers();
    	a.AddObserver(a.CreateActiveObserver());
        activeAgents.add(a);
        a.NotifyAllObservers();
        a.setVirologist(this);
        Skeleton.LogReturn();
    }

    /**
     * Adds an agents to the agent inventory of the virologist
     * @param a
     */
    public void AddAgentToInventory(Agent a) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),a.getClass().toString());
        agentInventory.add(a);
        a.NotifyAllObservers();
        a.setVirologist(this);
        Skeleton.LogReturn();
    }

    /**
     * Removes an agent from the active agents
     * @param a
     */
    public void RemoveAgent(Agent a) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),a.getClass().toString());
        activeAgents.remove(a);
        Skeleton.LogReturn();
    }

    /**
     * Checks whether the virologist can move or not
     * @return
     */
    private boolean checkMovement(){
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        for (int i = 0;i < activeAgents.size(); i++)
            if(activeAgents.get(i).HandleMove(this)) {
                Skeleton.LogReturn("false");
                return false;
            }
        Skeleton.LogReturn("true");
        return true;
    }

    /**
     * Checks whether the virologist is under amnesia or not
     * @param gc
     * @return
     */
    private boolean checkAmnesia(GeneticCode gc){
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        for (int i = 0;i < activeAgents.size(); i++)
            if(activeAgents.get(i).HandleCreateAgent(this,gc)) {
                Skeleton.LogReturn("true");
                return true;
            }
        Skeleton.LogReturn("false");
        return false;
    }

    /**
     * Checks whether the virologist can be touched or not
     * @param a
     * @param v
     * @return
     */
    private boolean checkTouch(Agent a, Virologist v) {
        //Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),a.getClass().toString(),v.getName());
        for (int i = 0; i < activeAgents.size(); i++)
            if (activeAgents.get(i).HandleTouch(this, a, v)){
                Skeleton.LogReturn("false");
                return false;
        }
        for(int i = 0;i<gears.size();i++)
            if(gears.get(i).HandleTouch(this,a,v)) {
                Skeleton.LogReturn("false");
                return false;
            }
        //Skeleton.LogReturn("true");
        return true;
    }

    /**
     * Handles the act of getting material from a Warehouse
     * @param amino the amino amount to get
     * @param nucleo the nucleo amount to get
     */
    public void GetMaterialFromWarehouse(int amino,int nucleo) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), String.valueOf(amino), String.valueOf(nucleo));
        for (Agent a : activeAgents) {
            if (a.HandleGetMaterialFromWarehouse(this)){
                Skeleton.LogReturn();
                return;
            }
        }
        for (Gear g : gears) {
            if (g.HandleGetMaterialFromWarehouse(this)){
                Skeleton.LogReturn();
                return;
            }
        }
        GetMaterial(amino,nucleo);
        Skeleton.LogReturn();
    }

    /**
     * A virologist attacks another virologist
     * @param v the virologist to attack
     */
    public void Attack(Virologist v) {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(), v.getName());
        for (Agent a : activeAgents) {
            if (a.HandleMove(this)){
                Skeleton.LogReturn();
                return;
            }
        }
        for (Gear g : gears) {
            if (g.HandleMove(this)){
                Skeleton.LogReturn();
                return;
            }
        }
        Weapon w = null;
        for (Gear g : gears) {
            if (g.GetSlot() == GearSlot.Weapon) {
                w = (Weapon) g;
            }
        }
        if (w != null) {
            w.Attack(v);
        }
        Skeleton.LogReturn();
    }

    /**
     * A virologist gets attacked by another virologist
     */
    public void Attacked() {
        Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName());
        field.Remove(this);
        Game.GetInstance().RemoveVirologist(this);
        Skeleton.LogReturn();
    }
}
