package com.kimbob.multiplication.service;

import com.kimbob.multiplication.domain.Multiplication;
import com.kimbob.multiplication.domain.MultiplicationResultAttempt;
import com.kimbob.multiplication.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class MultiplicationServiceTest {

    @Autowired
    private RandomGeneratorService randomGeneratorService;

    @Autowired
    private MultiplicationService multiplicationService;

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

@TestConfiguration
class TestConfig {

    @Bean
    public RandomGeneratorService randomGeneratorService() {
        return Mockito.mock(RandomGeneratorService.class);
    }

    @Bean
    public MultiplicationService multiplicationService(RandomGeneratorService randomGeneratorService) {
        return new MultiplicationServiceImpl(randomGeneratorService);
    }
}