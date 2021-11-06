package com.meli.xmen.controller;

import com.meli.xmen.model.stats.StatsResponse;
import com.meli.xmen.service.XMenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/stats/")
public class StatsDNAController {
    private final XMenService xMenService;

    @GetMapping
    public ResponseEntity<StatsResponse> stats( ) {
        return xMenService.statistics();
    }
}
