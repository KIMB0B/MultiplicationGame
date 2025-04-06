package com.kimbob.multiplication.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 사용자 정보를 저장하는 클래스
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private final String alias;

    // 기본 생성자
    protected User() {
        alias = "";
    }
}
