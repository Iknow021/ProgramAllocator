package com.nkosi.programallocator.exceptions;

public class IllegalArgumentException extends RuntimeException {
    private static final long serialVersionUID = 9001134L;

    public IllegalArgumentException(String message) {
        super(message);
    }
}