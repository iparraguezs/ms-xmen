package com.meli.xmen.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @Value("${message}")
    String message;

    @GetMapping(value = "healthcheck")
    public ResponseEntity<String> displayHelloMessage() {
        return ResponseEntity.ok(message);
    }
}
