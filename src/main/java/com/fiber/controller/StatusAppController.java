package com.fiber.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StatusAppController {

    @GetMapping("status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Running");
    }
}

