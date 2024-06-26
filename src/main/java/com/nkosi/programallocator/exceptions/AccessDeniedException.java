package com.nkosi.programallocator.exceptions;

public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = -879079079079079079L;

    public AccessDeniedException(String message) {
        super(message);
    }

}