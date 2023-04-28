package com.fiber.controller;

import com.fiber.entity.FoodEntity;
import com.fiber.payload.http.food.RegisterFoodRequestPayload;
import com.fiber.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fiber.util.OpenApiConstants.READ;
import static com.fiber.util.OpenApiConstants.SECURITY_SCHEME_NAME;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "food")
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    @Operation(
            description = "Create new food.",
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}

    )
    public ResponseEntity<FoodEntity> register(@RequestBody RegisterFoodRequestPayload body) {
        log.info("register - body:{}", body);
        return ResponseEntity.ok(foodService.create(body));
    }

    @PutMapping("/{id}")
    @Operation(
            description = "Update a existing food.",
            parameters = @Parameter(
                    name = "id",
                    schema = @Schema(
                            implementation = Long.class
                    )
            ),
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}

    )
    public ResponseEntity<FoodEntity> update(@RequestBody RegisterFoodRequestPayload payload,
                                             @PathVariable Long id) {
        log.info("update - id:{}", id);
        return ResponseEntity.ok(foodService.update(payload, id));
    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "Delete a food.",
            parameters = @Parameter(
                    name = "id",
                    schema = @Schema(
                            implementation = Long.class
                    )
            ),
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}

    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("delete - id:{}", id);
        foodService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(
            description = "Delete a food.",
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}

    )
    public ResponseEntity<List<FoodEntity>> getAll() {
        log.info("getAll");
        return ResponseEntity.ok(foodService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(
            description = "Get a food by id.",
            parameters = @Parameter(
                    name = "id",
                    schema = @Schema(
                            implementation = Long.class
                    )
            ),
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}

    )
    public ResponseEntity<FoodEntity> getById(@PathVariable Long id) {
        log.info("getById - id:{}", id);
        return ResponseEntity.ok(foodService.getById(id));
    }

    @GetMapping("/search/{name}")
    @Operation(
            description = "Search a food by name",
            parameters = @Parameter(
                    name = "name",
                    description = "Any character for search",
                    schema = @Schema(
                            implementation = String.class
                    )
            ),
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}

    )
    public ResponseEntity<List<FoodEntity>> getByName(@PathVariable String name) {
        log.info("getByName - name:{}", name);
        return ResponseEntity.ok(foodService.getByName(name));
    }
}
