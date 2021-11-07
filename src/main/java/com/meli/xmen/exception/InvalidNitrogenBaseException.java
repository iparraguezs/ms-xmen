package com.meli.xmen.exception;

public class InvalidNitrogenBaseException extends RuntimeException{
    public InvalidNitrogenBaseException() {
        super("Base nitrogen only accept A T C G");
    }
}
