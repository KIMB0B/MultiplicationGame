package com.kimbob.multiplication.domain;

import lombok.Getter;
import lombok.ToString;

/**
 * 애플리케이션에서 곱셈을 나타내는 클래스
 */
@Getter
@ToString
public class Multiplication {

    // 인수
    private int factorA;
    private int factorB;

    // A * B의 결과
    private int result;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = factorA * factorB;
    }
}
