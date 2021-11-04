package com.meli.xmen.controller;

import com.meli.xmen.model.mutant.DNARequest;
import com.meli.xmen.repository.TypePersonRepository;
import com.meli.xmen.service.XMenService;
import com.meli.xmen.service.XMenServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class AnalizeDNATest extends BaseTestCase{

    @InjectMocks
    private XMenServiceImpl xMenService;

    @Mock
    TypePersonRepository typePersonRepository;


    @Test
    public void isMutant(){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        List<String> adn = new ArrayList<>();
        adn.add("ATGCGA");
        adn.add("CTGTGC");
        adn.add("TTATGT");
        adn.add("AGAAGG");
        adn.add("CCCCTA");
        adn.add("TCACTG");
        DNARequest dnaRequest = new DNARequest(adn);
        when(xMenService.request(dnaRequest)).thenReturn(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void isHuman(){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.FORBIDDEN);
        List<String> adn = new ArrayList<>();
        adn.add("ATGCGA");
        adn.add("CAGTGC");
        adn.add("TTATTT");
        adn.add("AGACGG");
        adn.add("GCGTCA");
        adn.add("TCACTG");
        DNARequest dnaRequest = new DNARequest(adn);
        when(xMenService.request(dnaRequest)).thenReturn(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.FORBIDDEN);
    }
}
