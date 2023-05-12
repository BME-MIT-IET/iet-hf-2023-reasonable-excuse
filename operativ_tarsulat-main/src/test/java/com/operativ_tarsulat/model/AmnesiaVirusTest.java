package com.operativ_tarsulat.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmnesiaVirusTest {

    public Virologist v;
    public Virologist v2;
    public DanceGeneticCode dgc;
    public AmnesiaVirus av;

    @BeforeEach
    void setUp() {
        v = new Virologist();
        v2 = new Virologist();
        dgc = new DanceGeneticCode();
        av = new AmnesiaVirus(2, v2);
        v.AddGeneticCode(dgc);
        v.GetMaterial(50, 50);
    }
    @Test
    void checkIfVirologistKnowGeneticCode() {
        assertNotNull(v.getGeneticCodes());
    }
    @Test
    void checkIfVirologistForget() {
        v.ReceiveAgentUse(av, v2);
        v.CreateAgent(dgc);
        assertEquals(0, v.getAgentInventory().size());
    }
}