package com.fiber.controller;

import com.fiber.payload.http.season.SeasonResponsePayload;
import com.fiber.service.SeasonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fiber.util.OpenApiConstants.READ;
import static com.fiber.util.OpenApiConstants.SECURITY_SCHEME_NAME;

@RestController
@AllArgsConstructor
@RequestMapping(value = "season")
public class SeasonController {

    private final SeasonService seasonService;

    @GetMapping("/{idUser}")
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

}
