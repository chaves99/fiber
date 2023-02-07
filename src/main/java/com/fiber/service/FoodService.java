package com.fiber.service;

import com.fiber.entity.FoodEntity;
import com.fiber.payload.RegisterFoodRequestPayload;
import com.fiber.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodService {

    private FoodRepository foodRepository;

    public FoodEntity create(RegisterFoodRequestPayload payload) {
        return foodRepository.save(FoodEntity.from(payload));
    }

    public void delete(Long id) {
        foodRepository.deleteById(id);
    }

    public FoodEntity update(RegisterFoodRequestPayload payload, Long id) {
        return foodRepository.findById(id).map(fe -> {
            fe.update(payload);
            return foodRepository.save(fe);
        }).orElseGet(() -> {
            FoodEntity entity = FoodEntity.from(payload);
            entity.setId(id);
            return foodRepository.save(entity);
        });
    }

    public List<FoodEntity> getAll() {
        return foodRepository.findAll(Sort.sort(FoodEntity.class).by(FoodEntity::getName));
    }

    public FoodEntity getById(Long id) {
        return foodRepository.findById(id).orElseThrow();
    }

    public List<FoodEntity> getByName(String name) {
        return foodRepository.findByName(name);
    }
}
