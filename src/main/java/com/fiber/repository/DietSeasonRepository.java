package com.fiber.repository;

import com.fiber.entity.DietSeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietSeasonRepository extends JpaRepository<DietSeasonEntity, Long> {

    List<DietSeasonEntity> findByUser_id(Long id);

}
