package com.kimbob.multiplication.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

/**
 * 애플리케이션에서 곱셈을 나타내는 클래스
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
public class Multiplication {

    @Id
    @GeneratedValue
    @Column(name = "MULTIPLICATION_ID")
    private Long id;

    // 인수
    private final int factorA;
    private final int factorB;

    // 기본 생성자
    protected Multiplication() {
        this(0, 0);
    }
}
