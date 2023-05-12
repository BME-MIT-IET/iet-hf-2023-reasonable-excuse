package com.operativ_tarsulat.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}