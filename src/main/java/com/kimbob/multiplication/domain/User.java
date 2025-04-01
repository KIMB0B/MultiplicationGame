package com.kimbob.multiplication.domain;

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
public class User {

    private final String alias;

    // 기본 생성자
    User() {
        alias = "";
    }
}
