package com.meli.xmen.service;


import com.meli.xmen.entity.TypePerson;
import com.meli.xmen.exception.DNAStructureException;
import com.meli.xmen.exception.InvalidNitrogenBaseException;
import com.meli.xmen.model.mutant.DNARequest;
import com.meli.xmen.model.stats.StatsResponse;
import com.meli.xmen.repository.TypePersonRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
public class XMenServiceImpl implements XMenService{


    @Autowired
    private  TypePersonRepository typePersonRepository;

    @Setter
    @Value("${spring.application.dna.default-size-arr}")
    private Integer defaultSizeArr;

    @Override
    public ResponseEntity analizeDNA(DNARequest dnaRequest)   {
        validateDNAConsistency(dnaRequest);
        Boolean isMutant = isMutant(dnaRequest);
        typePersonRepository.save(TypePerson.builder().
                dna(dnaRequest.getDna().toString()).
                ismutant(isMutant).
                build());
        return responseEntity(isMutant);
    }

    private Boolean isMutant(DNARequest dnaRequest) {
        char[][] dna = loadDNAStructure(dnaRequest);
        Integer count = countSecuenceHorizontal(dna)+countSequenceVertical(dna);
        Boolean isMutant=count>1?Boolean.TRUE:Boolean.FALSE;
        return isMutant;
    }


    private void validateDNAConsistency(DNARequest dnaRequest) {
        if (dnaRequest.getDna().size() < defaultSizeArr) {
            throw new DNAStructureException(dnaRequest.getDna().size(), defaultSizeArr);
        } else if (dnaRequest.getDna().stream().filter(dnaAnalized->dnaAnalized.matches(".*[^ATCG].*")).count()>0) {
            throw new InvalidNitrogenBaseException();
        }
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
     * @param isMutant
     * @return
     */
    private ResponseEntity responseEntity(Boolean isMutant){
        if(isMutant){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
