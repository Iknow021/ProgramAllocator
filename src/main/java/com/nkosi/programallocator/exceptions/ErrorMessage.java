package com.nkosi.programallocator.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ErrorMessage {
    private ZonedDateTime eventTime;
    private String error;
    private String errorDescription;
    private HttpStatus errorCode;
}
