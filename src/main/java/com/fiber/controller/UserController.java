package com.fiber.controller;

import com.fiber.payload.LoginRequestPayload;
import com.fiber.payload.LoginResponsePayload;
import com.fiber.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "user")
public class UserController {

    private LoginService loginService;

    @GetMapping("test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test works");
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponsePayload> login(@RequestBody LoginRequestPayload loginRequestPayload) {
        log.info("login - request:{}", loginRequestPayload);
        LoginResponsePayload responsePayload = loginService.login(loginRequestPayload);
        return ResponseEntity.ok(responsePayload);
    }

}
