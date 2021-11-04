package com.meli.xmen.controller;

import com.meli.xmen.model.mutant.DNARequest;
import com.meli.xmen.service.XMenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/mutant/")
public class AnalizeDNAController {

    private final  XMenService xMenService;

    @PostMapping
    public ResponseEntity<Void> isMutant(@RequestBody DNARequest dna) {
        return xMenService.request(dna);
    }
}
