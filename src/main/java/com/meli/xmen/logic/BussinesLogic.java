package com.meli.xmen.logic;

import com.meli.xmen.model.mutant.DNARequest;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

public class BussinesLogic {
    @Setter
    @Value("${spring.application.dna.default-diagonal-size}")
    private Integer defaultDiagonalSize;

    protected int getCountSequenceMutant(char[][] dna) {
        return countSecuenceHorizontal(dna)+
                countSequenceVertical(dna)+
                countDiagonal(diagonalLeft(dna))+
                countDiagonal(diagonalRight(dna));
    }

    private Integer countDiagonal(String diagonal) {
        if(diagonal.contains("AAAA")|| diagonal.contains("TTTT")|| diagonal.contains("GGGG")|| diagonal.contains("CCCC")){
            return 1;
        }
        return 0;
    }

    protected char[][] loadDNAStructure(DNARequest dnaRequest) {
        int vectorLength = dnaRequest.getDna().size();
        char[][] dna = new char[vectorLength][vectorLength];
        for (int i = 0; i < vectorLength; i++) {
            String dnaRow = dnaRequest.getDna().get(i);
            dna[i] = dnaRow.toUpperCase().toCharArray();
        }
        return dna;
    }

    protected int countSecuenceHorizontal(char[][] arr){
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

    protected static int countSequenceVertical(char[][] arr){
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

    protected String diagonalRight(char[][] nuclear) {
        StringBuilder builderRight=new StringBuilder("");
        Integer secondPosition = defaultDiagonalSize;
        for(int firstPosition=0;firstPosition<defaultDiagonalSize;firstPosition++){
            builderRight.append(nuclear[firstPosition][--secondPosition]);
        }
        return builderRight.toString();
    }
    protected String diagonalLeft(char[][] nuclear) {
        StringBuilder builderIzquierdo=new StringBuilder("");
        for(int x =0;x<defaultDiagonalSize;x++){
            builderIzquierdo.append(nuclear[x][x]);
        }
        return builderIzquierdo.toString();
    }
}
