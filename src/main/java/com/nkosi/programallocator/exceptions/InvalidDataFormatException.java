package com.nkosi.programallocator.exceptions;

public class InvalidDataFormatException extends RuntimeException {
    private static final long serialVersionUID = 10L;

    public InvalidDataFormatException(String message) {
        super(message);
    }
}
