package com.meli.xmen.junit;


import com.meli.xmen.repository.TypePersonRepository;
import com.meli.xmen.service.XMenServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import static org.mockito.Mockito.when;

public class StatsDNATest extends BaseTestCase{
    @InjectMocks
    private XMenServiceImpl xMenService;

    @Mock
    TypePersonRepository typePersonRepository;

    @Test
    public void mockStats(){
        when(typePersonRepository.countByismutant(true)).thenReturn(new BigDecimal(1l));
        when(typePersonRepository.countByismutant(false)).thenReturn(new BigDecimal(1l));
        ResponseEntity responseEntity = xMenService.statistics();
        Assert.assertEquals(responseEntity.getStatusCode().value(),HttpStatus.OK.value());
    }
}
