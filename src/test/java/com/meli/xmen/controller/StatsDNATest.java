package com.meli.xmen.controller;


import com.meli.xmen.service.XMenService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.mockito.Mockito.when;

public class StatsDNATest extends BaseTestCase{
    @Mock
    private XMenService xMenService;

    @Test
    public void mockAnalizeDNA(){
        when(xMenService.statistics()).thenReturn(new ResponseEntity(HttpStatus.OK));
    }
}
