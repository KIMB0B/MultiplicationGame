package com.kimbob.multiplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomGeneratorServiceTest {

    private RandomGeneratorService randomGeneratorService;

    @BeforeEach
    void setUp() {
        randomGeneratorService = new RandomGeneratorServiceImpl();
    }

    @Test
    void generateRandomFactor() {
        int factor = randomGeneratorService.generateRandomFactor();

        // Check if the generated factor is between 11 and 99
        assertTrue(factor >= 11 && factor <= 99, "Generated factor is out of range");
    }
}