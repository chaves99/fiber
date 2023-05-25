package com.fiber.controller;

import com.fiber.service.MealsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "meals")
public class MealsController {

    private final MealsService mealsService;

    @PostMapping()
    public ResponseEntity<?> createMeal() {
        return ResponseEntity.ok().build();
    }

}
