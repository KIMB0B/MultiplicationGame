package com.kimbob.multiplication.service;

import com.kimbob.multiplication.domain.Multiplication;
import com.kimbob.multiplication.domain.MultiplicationResultAttempt;

import java.util.List;

public interface MultiplicationService {

    /**
     * 두 개의 무작위 인수를 담은 {@link Multiplication} 객체를 생성한다.
     * 무작위로 생성되는 숫자의 범위는 11~99
     *
     * @return 두 개의 무작위 인수를 담은 {@link Multiplication} 객체
     */
    Multiplication createMultiplication();

    /**
     * @return 곱셈 계산 결과가 맞으면 true, 아니면 false
     */
    boolean checkAttempt(MultiplicationResultAttempt resultAttempt);

    /**
     * @return 닉네임에 해당하는 사용자의 최근 답안 5개
     */
    List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
}
