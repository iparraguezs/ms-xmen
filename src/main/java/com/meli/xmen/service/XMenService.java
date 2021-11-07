package com.meli.xmen.service;

import com.meli.xmen.model.mutant.DNARequest;
import org.springframework.http.ResponseEntity;

public interface XMenService {
    /**
     *
     * This Method Valid DNA
     * @param @DNARequest.
     * @return Boolean
     */
    ResponseEntity analizeDNA(DNARequest id);
    ResponseEntity statistics();

}
