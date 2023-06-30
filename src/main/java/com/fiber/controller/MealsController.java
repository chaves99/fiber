package com.fiber.controller;

import com.fiber.payload.http.season.MealRequestPayload;
import com.fiber.payload.http.season.MealResponsePayload;
import com.fiber.service.MealsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fiber.util.OpenApiConstants.READ;
import static com.fiber.util.OpenApiConstants.SECURITY_SCHEME_NAME;

@RestController
@AllArgsConstructor
@RequestMapping(value = "meals")
@Tag(name = "Meal")
public class MealsController {

    private final MealsService mealsService;

    @PostMapping
    @Operation(
            description = "Create a new meal",
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}
    )
    public ResponseEntity<MealResponsePayload> createMeal(@RequestBody @Valid MealRequestPayload payload) {
        return ResponseEntity.ok(mealsService.create(payload));
    }

//    @GetMapping("/user/{userId}")
//    @Operation(
//            description = "Get meal by user",
//            security = {@SecurityRequirement(
//                    name = SECURITY_SCHEME_NAME,
//                    scopes = READ
//            )}
//    )
//    public ResponseEntity<List<MealResponsePayload>> getByUser(@PathVariable Long userId) {
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("season/{idSeason}")
    @Operation(
            description = "Get all meal by season",
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}
    )
    public ResponseEntity<List<MealResponsePayload>> getBySeason(@PathVariable Long idSeason) {
        return ResponseEntity.ok(mealsService.get(idSeason));
    }

//    @GetMapping("/today/{userId}")
//    @Operation(
//            description = "Get today's meal by user id",
//            security = {@SecurityRequirement(
//                    name = SECURITY_SCHEME_NAME,
//                    scopes = READ
//            )}
//    )
//    public ResponseEntity<List<MealResponsePayload>> today(@PathVariable Long userId) {
//        return ResponseEntity.ok().build();
//    }

}
