package com.operativ_tarsulat.model;

public interface Effect {
    public Boolean HandleTouch(Virologist v, Agent i, Virologist v2);
    public Boolean HandleMove(Virologist v) ;

    public int HandleInventoryCapacity(Virologist v);

    public Boolean HandleParalyzed(Virologist v) ;

    public Boolean HandleCreateAgent(Virologist v, GeneticCode code);
    
    public void HandleTurnStart(Virologist v);
    public void HandleMovedToField(Virologist v, Field f);
    public Boolean HandleGetMaterialFromWarehouse(Virologist v);
}
