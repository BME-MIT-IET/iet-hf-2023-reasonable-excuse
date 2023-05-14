package com.operativ_tarsulat.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ParalyzeVirusTest {

    public Virologist v;
    public Virologist v2;
    public ParalyzeVirus pv;
    public Field f1;
    public Field f2;
    public DanceGeneticCode dgc;
    public Axe axe;

    @BeforeEach
    void setUp() {
        dgc = new DanceGeneticCode();
        f1 = new FreeField();
        f2 = new FreeField();
        v2 = new Virologist();
        pv = new ParalyzeVirus(2, v2);
        v = new Virologist();
        axe = new Axe();
        v.SetField(f1);
        v2.SetField(f2);
    }

    @Test
    void testMove_WithParalyzeVirus_shouldNotMove() {
        v.ReceiveAgentUse(pv, v2);
        v.Move(f2);
        assertNotEquals(f2, v.GetField());
    }

    @Test
    void testCreateAgent_WithParalyzeVirus_shouldNotAgentInventoryContainCreatedAgent() {
        v.ReceiveAgentUse(pv, v2);
        v.CreateAgent(dgc);
        assertFalse(v.getAgentInventory().contains(dgc));
    }

    @Test
    void testAttack_WithParalyzeVirus_shouldNotRemoveAttackedVirologist() {
        v.ReceiveAgentUse(pv, v2);
        v.Attack(v2);
        assertFalse(Arrays.asList(f2.GetVirologists()).contains(v2));
    }


}