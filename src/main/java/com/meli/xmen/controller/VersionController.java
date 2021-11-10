package com.meli.xmen.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class VersionController {
    @GetMapping
    public ResponseEntity version( ) {
        return ResponseEntity.ok().body("Version APP X-MEN 0.0.1-SNAPSHOT");
    }
}
