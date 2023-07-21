package com.fiber.controller;

import com.fiber.entity.FoodEntity;
import com.fiber.payload.http.food.RegisterFoodRequestPayload;
import com.fiber.payload.http.food.SimpleFoodResponsePayload;
import com.fiber.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static com.fiber.util.OpenApiConstants.READ;
import static com.fiber.util.OpenApiConstants.SECURITY_SCHEME_NAME;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "food")
@Tag(name = "Food")
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
    public ResponseEntity<SimpleFoodResponsePayload> register(@RequestBody RegisterFoodRequestPayload body) {
        log.info("register - body:{}", body);
        FoodEntity foodEntity = foodService.create(body);
        return ResponseEntity.ok(SimpleFoodResponsePayload.fromEntity(foodEntity));
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
    public ResponseEntity<SimpleFoodResponsePayload> update(@RequestBody RegisterFoodRequestPayload payload,
                                                            @PathVariable Long id) {
        log.info("update - id:{}", id);
        FoodEntity foodEntity = foodService.update(payload, id);
        return ResponseEntity.ok(SimpleFoodResponsePayload.fromEntity(foodEntity));
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
            description = "Get all food.",
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}

    )
    public ResponseEntity<List<SimpleFoodResponsePayload>> getAll() {
        log.info("getAll");
        List<FoodEntity> entities = foodService.getAll();
        return ResponseEntity.ok(SimpleFoodResponsePayload.fromEntity(entities));
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
    public ResponseEntity<SimpleFoodResponsePayload> getById(@PathVariable Long id) {
        log.info("getById - id:{}", id);
        FoodEntity foodEntity = foodService.getById(id);
        return ResponseEntity.ok(SimpleFoodResponsePayload.fromEntity(foodEntity));
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
    public ResponseEntity<List<SimpleFoodResponsePayload>> getByName(@PathVariable String name) {
        log.info("getByName - name:{}", name);
        List<FoodEntity> entities = foodService.getByName(name);
        return ResponseEntity.ok(SimpleFoodResponsePayload.fromEntity(entities));
    }
}
