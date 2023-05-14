package com.operativ_tarsulat.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VirologistTest {
    Virologist virologist;
    @BeforeEach
    void init(){
        virologist = new Virologist();
    }
    @Test
    void learnGeneticCode_shouldTheNumberOfGeneticCodeIncrease() {
        int numberBeforeLearning = virologist.getGeneticCodes().size();
        virologist.LearnGeneticCode(new ParalyzeGeneticCode());
        int numberAfterLearning = virologist.getGeneticCodes().size();
        assertNotSame(numberAfterLearning,numberBeforeLearning);
    }

    @Test
    void move_shouldThePlayerBeOnTheGivenFieldAfterMoving() {
        Field f2 = new FreeField();
        Field f1 = new FreeField();
        f2.AddNeighbour(f1);
        f1.AddNeighbour(f2);
        virologist.SetField(f1);
        virologist.Move(f2);
        List<Virologist> virologists = Arrays.asList(f2.GetVirologists());
        assertTrue(virologists.contains(virologist));
    }

    @Test
    void createAgent_shouldIncreaseTheAgentInTheInventoryAfterCreatingAgent(){
        virologist.SetAmino(100);
        virologist.SetNucleo(100);
        int beforeCreating = virologist.getAgentInventory().size();
        virologist.CreateAgent(new ParalyzeGeneticCode(20,20));
        int afterCreating = virologist.getAgentInventory().size();
        assertEquals(beforeCreating+1,afterCreating);
    }
}