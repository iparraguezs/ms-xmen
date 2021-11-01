package com.meli.xmen.service;

import com.meli.xmen.model.DNARequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class XMenServiceImpl implements XMenService{
    @Override
    public ResponseEntity request(DNARequest id)   {
        Boolean isMutant = Boolean.FALSE;


        return responseEntity(isMutant);
    }

    private ResponseEntity responseEntity(Boolean isMutant){
        if(Boolean.TRUE==isMutant){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
