package com.kimbob.multiplication.repository;

import com.kimbob.multiplication.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Multiplication}을 저장하고 조회하기 위한 인터페이스
 */
@Repository
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
}
