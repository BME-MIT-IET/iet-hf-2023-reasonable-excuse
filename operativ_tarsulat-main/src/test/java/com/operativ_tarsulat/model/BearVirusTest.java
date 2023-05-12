package com.operativ_tarsulat.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BearVirusTest {

    public Virologist v;
    public Virologist v2;
    public Field f1;
    public Laboratory f2;
    public DanceGeneticCode dgc;

    @BeforeEach
    void setUp() {
        f1 = new FreeField();
        v2 = new Virologist();
        dgc = new DanceGeneticCode();
        f2 = new Laboratory(dgc, true);
        v = new Virologist("name", f2);
    }

    @Test
    void checkIfVirologistGetBearVirus() {
        v.InteractWithField();
        assertNotEquals(0, v.getActiveAgent().size());
    }
}