package com.kimbob.multiplication.service;

import com.kimbob.multiplication.domain.Multiplication;
import com.kimbob.multiplication.domain.MultiplicationResultAttempt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class MultiplicationServiceImpl implements MultiplicationService {

    private final RandomGeneratorService randomGeneratorService;

    @Override
    public Multiplication createMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
        boolean correct = resultAttempt.getResultAttempt() ==
                resultAttempt.getMultiplication().getFactorA() *
                        resultAttempt.getMultiplication().getFactorB();

        Assert.isTrue(!resultAttempt.isCorrect(), "채점한 상태로 보낼 수 없습니다!");

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                resultAttempt.getUser(),
                resultAttempt.getMultiplication(),
                resultAttempt.getResultAttempt(),
                correct
        );

        return correct;
    }
}
