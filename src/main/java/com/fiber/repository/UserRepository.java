package com.fiber.repository;

import com.fiber.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByNameAndPassword(String name, String password);

    Optional<UserEntity> findByName(String name);



}
