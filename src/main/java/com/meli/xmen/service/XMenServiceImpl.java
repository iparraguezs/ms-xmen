package com.meli.xmen.service;

import com.meli.xmen.entity.TypePerson;
import com.meli.xmen.model.mutant.DNARequest;
import com.meli.xmen.model.stats.StatsResponse;
import com.meli.xmen.repository.TypePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class XMenServiceImpl implements XMenService{


    @Autowired
    private  TypePersonRepository typePersonRepository;

    @Override
    public ResponseEntity request(DNARequest dnaRequest)   {
        char[][] dna = loadDNAStructure(dnaRequest);
        Integer count = countSecuenceHorizontal(dna)+countSequenceVertical(dna);
        typePersonRepository.save(TypePerson.builder().
                dna(dnaRequest.getDna().toString()).
                ismutant(count>1?Boolean.TRUE:Boolean.FALSE).
                build());
        return responseEntity(count);
    }

    @Override
    public ResponseEntity statistics() {
        BigDecimal mutant = typePersonRepository.countByismutant(true);
        BigDecimal human = typePersonRepository.countByismutant(false);
        return ResponseEntity.ok().body(StatsResponse.builder().
                countMutantDna(mutant.intValue()).
                countHumanDna(human.intValue()).
                ratio(BigDecimal.ZERO.compareTo(human)==0?BigDecimal.ZERO:mutant.divide(human,2,RoundingMode.HALF_UP)).
                build());
    }

    private int countSecuenceHorizontal(char[][] arr){
        int countSequenceRepeat=0;
        for (int firstArray = 0; firstArray < 6; firstArray++) {
            for (int letterDNA = 0; letterDNA < 6; letterDNA++) {
                if (6-letterDNA>=4) {
                    if (arr[firstArray][letterDNA] == arr[firstArray][letterDNA+ 1] && arr[firstArray][letterDNA] == arr[firstArray][letterDNA + 2] && arr[firstArray][letterDNA] == arr[firstArray][letterDNA + 3]) {
                        countSequenceRepeat++;
                        letterDNA = letterDNA + 3;
                        if (countSequenceRepeat>1){
                            return countSequenceRepeat;
                        }
                    }
                }
            }
        }
        return countSequenceRepeat;
    }

    static int countSequenceVertical(char[][] arr){
        int countSecuenceVert=0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (6-i>=4) {
                    if (arr[i][j] == arr[i+1][j] &&
                            arr[i][j] == arr[i+2][j] &&
                            arr[i][j] == arr[i+3][j]
                    ) {
                        countSecuenceVert++;
                        j = j + 3;
                        if (countSecuenceVert>1){
                            return (countSecuenceVert);
                        }
                    }
                }
            }
        }
        return countSecuenceVert;
    }

    private char[][] loadDNAStructure(DNARequest dnaRequest) {
        int vectorLength = dnaRequest.getDna().size();
        char[][] dna = new char[vectorLength][vectorLength];

        for (int i = 0; i < vectorLength; i++) {
            String dnaRow = dnaRequest.getDna().get(i);
            dna[i] = dnaRow.toUpperCase().toCharArray();
        }
        return dna;
    }




    /**
     * Method returns Is Mutant 200 ok and is != 403
     * @param countRepeatDNA
     * @return
     */
    private ResponseEntity responseEntity(Integer countRepeatDNA){
        if(countRepeatDNA>1){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
