package com.meli.xmen.model.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StatsResponse {
    @JsonProperty("count_mutant_dna")
    Integer countMutantDna;
    @JsonProperty("count_human_dna")
    Integer countHumanDna;
    BigDecimal ratio;
}
