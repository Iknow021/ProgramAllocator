package com.nkosi.programallocator.exceptions;

import org.springframework.security.core.AuthenticationException;

public class TokenAuthenticationException extends AuthenticationException {

    public TokenAuthenticationException(String msg) {
        super(msg);
    }

    private static final long serialVersionUID = -879079079079079079L;

}