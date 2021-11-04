package com.meli.xmen.model.stats;

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
    Integer count_mutant_dna;
    Integer count_human_dna;
    BigDecimal ratio;
}
