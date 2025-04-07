package com.kimbob.multiplication.repository;

import com.kimbob.multiplication.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@link User}를 저장하고 조회하기 위한 인터페이스
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByAlias(String userAlias);
}
