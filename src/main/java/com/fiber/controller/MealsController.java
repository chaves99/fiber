package com.fiber.controller;

import com.fiber.service.MealsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "meals")
public class MealsController {

    private final MealsService mealsService;

    @PostMapping
    public ResponseEntity<?> createMeal() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("season/{idSeason}")
    public ResponseEntity<?> getBySeason(@PathVariable Long idSeason) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/today/{userId}")
    public ResponseEntity<?> today(@PathVariable Long userId) {
        return ResponseEntity.ok().build();
    }

}
