package com.fiber.controller;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.payload.http.season.SeasonCreateRequestPayload;
import com.fiber.payload.http.season.SeasonResponsePayload;
import com.fiber.payload.http.season.SeasonUpdateDateRequestPayload;
import com.fiber.service.SeasonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.fiber.util.OpenApiConstants.READ;
import static com.fiber.util.OpenApiConstants.SECURITY_SCHEME_NAME;

@RestController
@AllArgsConstructor
@RequestMapping(value = "season")
@Tag(name = "Season")
public class SeasonController {

    private final SeasonService seasonService;

    @GetMapping("/user/{idUser}")
    @Operation(
            description = "Return all diet season by user id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = SeasonResponsePayload.class
                                            ),
                                            uniqueItems = true
                                    )
                            )
                    )
            },
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}
    )
    ResponseEntity<List<SeasonResponsePayload>> getAllByUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(seasonService.getByUserId(idUser));
    }

    @GetMapping("/active/{idUser}")
    @Operation(
            description = "Return the active season diet by user id.",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(
                                    implementation = SeasonResponsePayload.class
                            )
                    )
            ),
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}
    )
    ResponseEntity<SeasonResponsePayload> getActiveByUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(seasonService.getActiveByUser(idUser));
    }

    @PostMapping
    @Operation(
            description = "Create a new season diet.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(
                                    implementation = SeasonCreateRequestPayload.class
                            )
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(
                                    implementation = SeasonResponsePayload.class
                            )
                    )
            ),
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}
    )
    ResponseEntity<SeasonResponsePayload> create(@RequestBody SeasonCreateRequestPayload payload) {
        return ResponseEntity.ok(seasonService.create(payload).toResponsePayload());
    }

    @PutMapping("/final-date/{seasonId}")
    @Operation(
            description = "Update the diet season's final date.",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(
                                    implementation = SeasonResponsePayload.class
                            )
                    )
            ),
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}
    )
    ResponseEntity<SeasonResponsePayload> updateFinalDate(
            @PathVariable Long seasonId,
            @RequestBody SeasonUpdateDateRequestPayload payload
    ) {
        return ResponseEntity.ok(seasonService.updateFinalDate(seasonId, payload.finalDate()).toResponsePayload());
    }

    @GetMapping("/{id}")
    ResponseEntity<SeasonResponsePayload> getById(@PathVariable Long id) {
        Optional<DietSeasonEntity> dietSeason = seasonService.getDietSeason(id);
        return dietSeason
                .map(ds -> ResponseEntity.ok(ds.toResponsePayload()))
                .orElse(ResponseEntity.notFound().build());
    }

}
