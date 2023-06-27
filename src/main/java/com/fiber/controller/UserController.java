package com.fiber.controller;

import com.fiber.payload.http.login.LoginRequestPayload;
import com.fiber.payload.http.login.LoginResponsePayload;
import com.fiber.payload.http.user.UserRegisterRequestPayload;
import com.fiber.payload.http.user.UserResponsePayload;
import com.fiber.payload.http.user.UserUpdateRequestPayload;
import com.fiber.service.LoginService;
import com.fiber.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fiber.util.OpenApiConstants.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "user")
@Tag(name = "User")
public class UserController {

    private LoginService loginService;

    private UserService userService;

    @GetMapping("/{id}")
    @Operation(
            description = "Get user by id.",
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
    public ResponseEntity<UserResponsePayload> get(@PathVariable Long id) {
        return ResponseEntity.ok(UserResponsePayload.from(this.userService.get(id)));
    }

    @GetMapping
    @Operation(
            description = "Return all users.",
            responses = @ApiResponse(
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = UserResponsePayload.class
                                    ),
                                    uniqueItems = true
                            )
                    )
            ),
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = READ
            )}
    )
    public ResponseEntity<List<UserResponsePayload>> get() {
        return ResponseEntity.ok(UserResponsePayload.from(this.userService.getAll()));
    }

    @PostMapping
    @Operation(
            description = "Endpoint for create a new user",
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = WRITE
            )}
    )
    public ResponseEntity<UserResponsePayload> register(@RequestBody UserRegisterRequestPayload user) {
        return ResponseEntity.ok(UserResponsePayload.from(userService.register(user)));
    }

    @PutMapping("/{id}")
    @Operation(
            description = "Update a user by id.",
            responses = @ApiResponse(
                    content = @Content(
                            schema = @Schema(
                                    implementation = UserResponsePayload.class
                            )
                    )
            ),
            parameters = @Parameter(
                    name = "id",
                    description = "ID of user that will be updated.",
                    schema = @Schema(
                            implementation = Long.class
                    ),
                    required = true
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(
                                    implementation = UserUpdateRequestPayload.class
                            )
                    )
            ),
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = WRITE
            )}
    )
    public ResponseEntity<UserResponsePayload> update(
            @PathVariable Long id,
            @RequestBody UserUpdateRequestPayload payload) {
        return ResponseEntity.ok(UserResponsePayload.from(userService.update(id, payload)));
    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "Delete a user by id",
            parameters = @Parameter(
                    name = "id",
                    description = "ID of user that will be updated.",
                    schema = @Schema(
                            implementation = Long.class
                    ),
                    required = true
            ),
            security = {@SecurityRequirement(
                    name = SECURITY_SCHEME_NAME,
                    scopes = WRITE
            )}
    )
    public ResponseEntity delete(@PathVariable Long id) {
        if (userService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("login")
    @Operation(
            description = "URL for you login, Will return a token for allow you use the api.",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(
                                    implementation = LoginResponsePayload.class
                            )
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(
                                    implementation = LoginRequestPayload.class
                            )
                    )
            )
    )
    public ResponseEntity<LoginResponsePayload> login(@RequestBody LoginRequestPayload loginRequestPayload) {
        log.info("login - request:{}", loginRequestPayload);
        LoginResponsePayload responsePayload = loginService.login(loginRequestPayload);
        return ResponseEntity.ok(responsePayload);
    }

}
