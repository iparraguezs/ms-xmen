package com.meli.xmen.controller;

import com.meli.xmen.entity.TypePerson;
import com.meli.xmen.model.mutant.DNARequest;
import com.meli.xmen.repository.TypePersonRepository;
import com.meli.xmen.service.XMenServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class AnalizeDNATest extends BaseTestCase{

    @InjectMocks
    private XMenServiceImpl xMenService;

    @Mock
    TypePersonRepository typePersonRepository;


    @Test
    public void isMutant(){
        List<String> adn = new ArrayList<>();
        adn.add("ATGCGA");
        adn.add("CTGTGC");
        adn.add("TTATGT");
        adn.add("AGAAGG");
        adn.add("CCCCTA");
        adn.add("TCACTG");
        DNARequest dnaRequest = new DNARequest(adn);

        when(typePersonRepository.save(TypePerson.builder().build())).thenReturn(anyInt());
        ResponseEntity responseEntity = xMenService.request(dnaRequest);
        Assert.assertEquals(responseEntity.getStatusCode().value(),HttpStatus.OK.value());
    }

    @Test
    public void isHuman(){
        List<String> adn = new ArrayList<>();
        adn.add("ATGCGA");
        adn.add("CAGTGC");
        adn.add("TTATTT");
        adn.add("AGACGG");
        adn.add("GCGTCA");
        adn.add("TCACTG");
        DNARequest dnaRequest = new DNARequest(adn);

        when(typePersonRepository.save(TypePerson.builder().build())).thenReturn(anyInt());
        ResponseEntity responseEntity = xMenService.request(dnaRequest);
        Assert.assertEquals(responseEntity.getStatusCode().value(),HttpStatus.FORBIDDEN.value());
    }
}
