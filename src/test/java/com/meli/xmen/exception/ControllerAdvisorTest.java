package com.meli.xmen.exception;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerAdvisorTest {
    private ControllerAdvisor advice=new ControllerAdvisor();

    @Test
    public void dnaStructureExceptionTest(){
        ResponseEntity<Object> dnaStructureException = advice.dnaStructureException(new DNAStructureException(5,6));
        assertNotNull(dnaStructureException);
        assertNotNull(dnaStructureException.getBody());
        assertEquals(dnaStructureException.getStatusCode().value(),400);
    }

    @Test
    public void invalidNitrogenBaseExceptionTest(){
        ResponseEntity<Object> invalidNitrogenBaseException = advice.invalidNitrogenBaseException(new InvalidNitrogenBaseException());
        assertNotNull(invalidNitrogenBaseException);
        assertNotNull(invalidNitrogenBaseException.getBody());
        assertEquals(invalidNitrogenBaseException.getStatusCode().value(),400);
    }
}