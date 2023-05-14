package com.operativ_tarsulat.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BearVirusTest {

    public Virologist v;
    public Virologist v2;
    public Laboratory f;
    public DanceGeneticCode dgc;
    public boolean containsBearVirus;

    @BeforeEach
    void setUp() {
        v2 = new Virologist();
        dgc = new DanceGeneticCode();
        f = new Laboratory(dgc, true);
        v = new Virologist();
        v.SetField(f);
        containsBearVirus = false;
    }

    @Test
    void testInteractWithField_FieldContainBearVirus_shouldActiveAgentContainBearVirus() {
        v.InteractWithField();
        for(Agent a : v.getActiveAgent()){
            if(a instanceof BearVirus){
                containsBearVirus = true;
                break;
            }
        }
        assertTrue(containsBearVirus);
    }
}