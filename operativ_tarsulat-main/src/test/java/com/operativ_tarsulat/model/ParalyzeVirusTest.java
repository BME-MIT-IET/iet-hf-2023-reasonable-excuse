package com.operativ_tarsulat.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParalyzeVirusTest {

    public Virologist v;
    public Virologist v2;
    public ParalyzeVirus pv;
    public Field f1;
    public Field f2;
    public DanceGeneticCode dgc;

    @BeforeEach
    void setUp() {
        v2 = new Virologist();
        pv = new ParalyzeVirus(2, v2);
        dgc = new DanceGeneticCode();
        f1 = new FreeField();
        f2 = new FreeField();
        v = new Virologist("name", f1);
    }

    @Test
    void checkIfVirologistCanMove() {
        v.ReceiveAgentUse(pv, v2);
        v.Move(f2);
        assertNotEquals(f2, v.GetField());
    }

    @Test
    void checkIfVirologistCanCreateAgent() {
        v.ReceiveAgentUse(pv, v2);
        v.CreateAgent(dgc);
        assertEquals(0, v.getAgentInventory().size());
    }
    @Test
    void checkIfVirologistCanAttack() {
        v.ReceiveAgentUse(pv, v2);
        v.Attack(v2);
        assertNotNull(v2);
    }

}