package com.operativ_tarsulat.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AmnesiaVirusTest {

    public Virologist v;
    public Virologist v2;
    public AmnesiaVirus pv;
    public Field f1;
    public DanceGeneticCode dgc;
    public boolean containsDanceVirus;

    @BeforeEach
    void setUp() {
        dgc = new DanceGeneticCode();
        f1 = new FreeField();
        v2 = new Virologist();
        pv = new AmnesiaVirus(2, v2);
        v = new Virologist();
        v.SetField(f1);
        v.SetAmino(100);
        v.SetNucleo(100);
        v.AddGeneticCode(dgc);
        containsDanceVirus = false;
    }
    @Test
    void testCreateAgent_WithAmnesiaVirus_shouldNotAgentInventoryContainCreatedAgent() {
        v.ReceiveAgentUse(pv, v2);
        v.CreateAgent(dgc);
        for(Agent a : v.getAgentInventory()){
            if(a instanceof DanceVirus){
                containsDanceVirus = true;
                break;
            }
        }
        assertFalse(containsDanceVirus);
    }
}