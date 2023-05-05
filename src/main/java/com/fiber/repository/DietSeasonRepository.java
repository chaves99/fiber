package com.fiber.repository;

import com.fiber.entity.DietSeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietSeasonRepository extends JpaRepository<DietSeasonEntity, Long> {

    @Query(value = "SELECT * FROM diet_season where id_user = ?1", nativeQuery = true)
    List<DietSeasonEntity> findByUserId(Long id);

}
