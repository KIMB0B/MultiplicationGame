package com.kimbob.multiplication.service;

import com.kimbob.multiplication.domain.Multiplication;
import com.kimbob.multiplication.domain.MultiplicationResultAttempt;
import com.kimbob.multiplication.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    }

    @Test
    void checkCorrectAttemptTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John_doe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000);

        boolean isCorrect = multiplicationService.checkAttempt(attempt);

        assertThat(isCorrect).isTrue();
    }

    @Test
    void checkWrongAttemptTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John_doe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010);

        boolean isCorrect = multiplicationService.checkAttempt(attempt);

        assertThat(isCorrect).isFalse();
    }
}