package com.fiber.controller;

import com.fiber.entity.UserEntity;
import com.fiber.payload.LoginRequestPayload;
import com.fiber.payload.LoginResponsePayload;
import com.fiber.payload.UserResponsePayload;
import com.fiber.payload.UserUpdateRequestPayload;
import com.fiber.service.LoginService;
import com.fiber.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "user")
public class UserController {

    private LoginService loginService;

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponsePayload> get(@PathVariable Long id) {
        return ResponseEntity.ok(UserResponsePayload.from(this.userService.get(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserResponsePayload>> get() {
        return ResponseEntity.ok(UserResponsePayload.from(this.userService.getAll()));
    }

    @PostMapping
    public ResponseEntity<UserResponsePayload> register(@RequestBody UserEntity user){
        return ResponseEntity.ok(UserResponsePayload.from(userService.register(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponsePayload> update(
            @PathVariable Long id,
            @RequestBody UserUpdateRequestPayload payload) {
        return ResponseEntity.ok(UserResponsePayload.from(userService.update(id, payload)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(userService.delete(id)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponsePayload> login(@RequestBody LoginRequestPayload loginRequestPayload) {
        log.info("login - request:{}", loginRequestPayload);
        LoginResponsePayload responsePayload = loginService.login(loginRequestPayload);
        return ResponseEntity.ok(responsePayload);
    }

}
