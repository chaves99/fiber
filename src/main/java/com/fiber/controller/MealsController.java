package com.fiber.controller;

import com.fiber.entity.UserEntity;
import com.fiber.error.excption.GenericException;
import com.fiber.payload.http.meal.MealRequestPayload;
import com.fiber.payload.http.meal.MealResponsePayload;
import com.fiber.service.AuthenticatedUserRetrieverService;
import com.fiber.service.MealsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    private final AuthenticatedUserRetrieverService authenticatedUserRetrieverService;

    @PostMapping("create")
    @Operation(
            description = "Create a new meal",
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}
    )
    public ResponseEntity<MealResponsePayload> createMeal(@RequestBody @Valid MealRequestPayload payload, Authentication authentication) {
        UserEntity user = authenticatedUserRetrieverService
                .retrieve(authentication)
                .orElseThrow(() -> new GenericException("Error retrieving authenticated user"));
        return ResponseEntity.ok(mealsService.create(payload, user.getId()));
    }

    @GetMapping("{idMeal}")
    @Operation(
            description = "Get a meal by user and meal id",
            security = {
                    @SecurityRequirement(name = SECURITY_SCHEME_NAME, scopes = READ)
            }
    )
    public ResponseEntity<MealResponsePayload> getMeal(@PathVariable Long idMeal, Authentication authentication) {
        UserEntity user = authenticatedUserRetrieverService
                .retrieve(authentication)
                .orElseThrow(() -> new GenericException("Error retrieving authenticated user"));
        return ResponseEntity.ok(mealsService.fetch(idMeal));
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
