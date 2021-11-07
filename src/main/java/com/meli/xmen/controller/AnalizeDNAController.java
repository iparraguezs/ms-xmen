package com.meli.xmen.controller;

import com.meli.xmen.model.mutant.DNARequest;
import com.meli.xmen.service.XMenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation("Analyze the DNA and check if it is human or mutant, additionally it persists in a database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the 200 http code if the analyzed DNA is Mutant"),
            @ApiResponse(code = 400, message = "Returns the 400 http if it has a different nitrogen base ATCG"),
            @ApiResponse(code = 400, message = "Returns the 400 http if base has a different DNA structure than expected"),
            @ApiResponse(code = 403, message = "Returns the 403 http code if the analyzed DNA is Human")
    })
    @PostMapping
    public ResponseEntity<Void> analizeDNA(@RequestBody DNARequest dna) {
        return xMenService.analizeDNA(dna);
    }
}
