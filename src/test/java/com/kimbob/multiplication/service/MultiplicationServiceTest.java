package com.kimbob.multiplication.service;

import com.kimbob.multiplication.domain.Multiplication;
import com.kimbob.multiplication.domain.MultiplicationResultAttempt;
import com.kimbob.multiplication.domain.User;
import com.kimbob.multiplication.repository.MultiplicationResultAttemptRepository;
import com.kimbob.multiplication.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MultiplicationServiceTest {

    @Autowired
    private MultiplicationResultAttemptRepository multiplicationResultAttemptRepository;

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
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
        boolean isCorrect = multiplicationService.checkAttempt(attempt);

        assertThat(isCorrect).isTrue();
        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);
        verify(multiplicationResultAttemptRepository).save(checkedAttempt);
    }

    @Test
    void checkWrongAttemptTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John_doe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010, false);

        boolean isCorrect = multiplicationService.checkAttempt(attempt);

        assertThat(isCorrect).isFalse();
        verify(multiplicationResultAttemptRepository).save(attempt);
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public UserRepository userRepository() {
            return Mockito.mock(UserRepository.class);
        }

        @Bean
        public MultiplicationResultAttemptRepository multiplicationResultAttemptRepository() {
            return Mockito.mock(MultiplicationResultAttemptRepository.class);
        }

        @Bean
        public RandomGeneratorService randomGeneratorService() {
            return Mockito.mock(RandomGeneratorService.class);
        }

        @Bean
        public MultiplicationService multiplicationService(RandomGeneratorService randomGeneratorService,
                                                            UserRepository userRepository,
                                                            MultiplicationResultAttemptRepository multiplicationResultAttemptRepository) {
            return new MultiplicationServiceImpl(randomGeneratorService, userRepository, multiplicationResultAttemptRepository);
        }
    }
}