package com.meli.xmen.controller;

import com.meli.xmen.model.stats.StatsResponse;
import com.meli.xmen.service.XMenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation("Returns the statistics of the analyzed DNA")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns the 200 http code if the analyzed DNA is Mutant",response = StatsResponse.class)})
    @GetMapping
    public ResponseEntity<StatsResponse> stats( ) {
        return xMenService.statistics();
    }
}
