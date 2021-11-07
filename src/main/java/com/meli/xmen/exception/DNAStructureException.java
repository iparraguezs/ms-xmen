package com.meli.xmen.exception;

public class DNAStructureException extends RuntimeException{
    public DNAStructureException(Integer lengthExpect, Integer lengthFound) {
        super("DNA Structure Expected: "+lengthExpect+" and found: "+lengthFound);
    }
}
