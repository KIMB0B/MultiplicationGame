package com.kimbob.multiplication.domain;

import lombok.*;

/**
 * 애플리케이션에서 곱셈을 나타내는 클래스
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Multiplication {

    // 인수
    private final int factorA;
    private final int factorB;

    // 기본 생성자
    Multiplication() {
        this(0, 0);
    }
}
