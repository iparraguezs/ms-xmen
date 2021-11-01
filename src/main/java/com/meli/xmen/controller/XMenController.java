package com.meli.xmen.controller;


import com.meli.xmen.model.DNARequest;
import com.meli.xmen.service.XMenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mutant")
public class XMenController {

    @Autowired
    private  XMenService xMenService;

    @PostMapping("/")
    public ResponseEntity<Void> isMutant(@RequestBody DNARequest dna) {
        log.info("Mutant Request is :"+dna);
        return xMenService.request(dna);
    }
}
