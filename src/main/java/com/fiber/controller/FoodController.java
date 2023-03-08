package com.fiber.controller;

import com.fiber.entity.FoodEntity;
import com.fiber.payload.RegisterFoodRequestPayload;
import com.fiber.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "food")
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<FoodEntity> register(@RequestBody RegisterFoodRequestPayload body) {
        log.info("register - body:{}", body);
        return ResponseEntity.ok(foodService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodEntity> update(@RequestBody RegisterFoodRequestPayload payload,
                                             @PathVariable Long id) {
        log.info("update - id:{}", id);
        return ResponseEntity.ok(foodService.update(payload, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("delete - id:{}", id);
        foodService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FoodEntity>> getAll() {
        log.info("getAll");
        return ResponseEntity.ok(foodService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodEntity> getById(@PathVariable Long id) {
        log.info("getById - id:{}", id);
        return ResponseEntity.ok(foodService.getById(id));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<FoodEntity>> getByName(@PathVariable String name) {
        log.info("getByName - name:{}", name);
        return ResponseEntity.ok(foodService.getByName(name));
    }
}
