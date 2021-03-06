package com.meli.xmen.junit;

import com.meli.xmen.entity.TypePerson;
import com.meli.xmen.exception.DNAStructureException;
import com.meli.xmen.exception.InvalidNitrogenBaseException;
import com.meli.xmen.model.mutant.DNARequest;
import com.meli.xmen.repository.TypePersonRepository;
import com.meli.xmen.service.XMenServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class AnalizeDNATest extends BaseTestCase {

    @InjectMocks
    private XMenServiceImpl xMenService;

    @Mock
    TypePersonRepository typePersonRepository;

    Integer defaultDiagonalSize = 6;

    @Test
    public void isMutantTest() {
        xMenService.setDefaultSizeArr(6);
        xMenService.setDefaultDiagonalSize(6);
        List<String> adn = new ArrayList<>();
        adn.add("ATGCGA");
        adn.add("CTGTGC");
        adn.add("TTATGT");
        adn.add("AGAAGG");
        adn.add("CCCCTA");
        adn.add("TCACTG");
        DNARequest dnaRequest = new DNARequest(adn);
        when(typePersonRepository.save(TypePerson.builder().build())).thenReturn(anyInt());
        ResponseEntity responseEntity = xMenService.analizeDNA(dnaRequest);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void isHumanTest() {
        xMenService.setDefaultSizeArr(6);
        xMenService.setDefaultDiagonalSize(6);
        List<String> adn = new ArrayList<>();
        adn.add("ATGCGA");
        adn.add("CAGTGC");
        adn.add("TTATTT");
        adn.add("AGACGG");
        adn.add("GCGTCA");
        adn.add("TCACTG");
        DNARequest dnaRequest = new DNARequest(adn);
        when(typePersonRepository.save(TypePerson.builder().build())).thenReturn(anyInt());
        ResponseEntity responseEntity = xMenService.analizeDNA(dnaRequest);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void validateStructureTest() {
        Integer defaultSizeArr = 6;
        List<String> adn = new ArrayList<>();
        adn.add("ATGCGA");
        adn.add("CAGTGC");
        adn.add("TTATTT");
        adn.add("AGACGG");
        adn.add("GCGTCA");
        DNARequest dnaRequest = new DNARequest(adn);
        xMenService.setDefaultSizeArr(defaultSizeArr);
        DNAStructureException thrown = Assertions.assertThrows(DNAStructureException.class, () -> {
            xMenService.analizeDNA(dnaRequest);
        });
        Assert.assertEquals(thrown.getMessage(), "DNA Structure Expected: 5 and found: 6");
    }

    @Test
    public void validateNitrogenBaseTest() {
        Integer defaultSizeArr = 6;
        List<String> adn = new ArrayList<>();
        adn.add("ATGCHA");
        adn.add("CAGTGC");
        adn.add("TTATTT");
        adn.add("AGACGG");
        adn.add("GCGTCA");
        adn.add("TCACTG");
        DNARequest dnaRequest = new DNARequest(adn);
        xMenService.setDefaultSizeArr(defaultSizeArr);
        InvalidNitrogenBaseException thrown = Assertions.assertThrows(InvalidNitrogenBaseException.class, () -> {
            xMenService.analizeDNA(dnaRequest);
        });
        Assert.assertEquals(thrown.getMessage(), "Base nitrogen only accept A T C G");
    }

}
