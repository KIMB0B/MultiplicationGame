package com.kimbob.multiplication.service;

import com.kimbob.multiplication.domain.Multiplication;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class MultiplicationServiceTest {

    @Mock
    private RandomGeneratorService randomGeneratorService;

    private MultiplicationService multiplicationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        multiplicationService = new MultiplicationServiceImpl(randomGeneratorService);
    }

    @Test
    void createRandomMultiplicationTest() {
        when(randomGeneratorService.generateRandomFactor()).thenReturn(50, 30);

        Multiplication multiplication = multiplicationService.createMultiplication();

        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(1500);
    }
}