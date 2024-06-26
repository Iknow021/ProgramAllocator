package com.nkosi.programallocator.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> duplicateKeyExceptionExceptionHandler(DuplicateKeyException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Duplicate key constraint violation error occurred");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.BAD_REQUEST);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException ex) {
//        ErrorMessage customError = new ErrorMessage();
//        customError.setEventTime(ZonedDateTime.now());
//        customError.setError("Data constraint violation error occurred");
//        customError.setErrorDescription(ex.getLocalizedMessage());
//        customError.setErrorCode(HttpStatus.BAD_REQUEST);
//        log.error(customError.toString(), ex);
//        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Data integrity violation error occurred");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.BAD_REQUEST);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> sqlExceptionHandler(SQLException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Data inconsistency error occurred");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.BAD_REQUEST);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> dateTimeParseExceptionHandler(DateTimeParseException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Date input value is wrong");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.BAD_REQUEST);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        log.error("{}", errors, ex);
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataHandlingExceptionHandler(DataNotFoundException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Searched item not found");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.NOT_FOUND);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> accessDeniedExceptionHandler(AccessDeniedException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Error occurred while processing for requested credentials: The requested authorities could not be granted nor confirmed");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.FORBIDDEN);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TokenAuthenticationException.class)
    public ResponseEntity<Object> tokenAuthenticationExceptionHandler(TokenAuthenticationException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Error occurred while authenticating via bearer token: The bearer token used to request access could not be validated");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.FORBIDDEN);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.FORBIDDEN);
    }

//    @ExceptionHandler(InvalidDataFormatException.class)
//    public ResponseEntity<Object> invalidDataFormatExceptionHandler(ConstraintViolationException ex) {
//        ErrorMessage customError = new ErrorMessage();
//        customError.setEventTime(ZonedDateTime.now());
//        customError.setError("Invalid incoming data error detected");
//        customError.setErrorDescription(ex.getErrorMessage());
//        customError.setErrorCode(HttpStatus.BAD_REQUEST);
//        log.error(customError.toString(), ex);
//        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Object> jsonProcessingExceptionHandler(JsonProcessingException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Error occurred in Processing a JSON request: The XML string could not be mapped");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.EXPECTATION_FAILED);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.EXPECTATION_FAILED);
    }


    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> dataAccessExceptionHandler(DataAccessException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Error occurred in the Data Access Object: Request to commit a transaction by accessing the data object failed");
        customError.setErrorDescription("One or more requested objects not found, causing violation of constraints");
        customError.setErrorCode(HttpStatus.EXPECTATION_FAILED);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundExceptionHandler(EntityNotFoundException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Error occurred in the Data Access Object: Request to commit a transaction by accessing the data object failed");
        customError.setErrorDescription("One or more requested objects not found, causing violation of constraints");
        customError.setErrorCode(HttpStatus.EXPECTATION_FAILED);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<Object> parseExceptionHandler(ParseException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Error occurred while parsing input data: Part of the input data could not be parsed");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.BAD_REQUEST);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointerExceptionHandler(NullPointerException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Null Data Object Encountered: The passed input data has bad elements causing a processing error");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileDeletionFailedException.class)
    public ResponseEntity<Object> fileDeletionFailedExceptionHandler(FileDeletionFailedException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Error Occurred: Request could not be processed");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalExceptionHandler(Exception ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Error Occurred: Request could not be processed");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        ErrorMessage customError = new ErrorMessage();
        customError.setEventTime(ZonedDateTime.now());
        customError.setError("Error Occurred: The passed input data has missing elements");
        customError.setErrorDescription(ex.getLocalizedMessage());
        customError.setErrorCode(HttpStatus.EXPECTATION_FAILED);
        log.error(customError.toString(), ex);
        return new ResponseEntity<>(customError, HttpStatus.EXPECTATION_FAILED);
    }

//    @Override
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ErrorMessage customError = new ErrorMessage();
//        customError.setEventTime(ZonedDateTime.now());
//        customError.setError("Wrong request type was sent");
//        customError.setErrorDescription(ex.getLocalizedMessage());
//        customError.setErrorCode(HttpStatus.BAD_REQUEST);
//        log.error(customError.toString(), ex);
//        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(DuplicationFoundException.class)
    public ResponseEntity<Object> notFoundError(DuplicationFoundException ex) {
        ErrorMessage customError = ErrorMessage.builder().eventTime(ZonedDateTime.now()).error("Possible duplicate found").errorDescription(ex.getLocalizedMessage()).errorCode(
                HttpStatus.CONFLICT).build();
        log.error(customError.toString(), ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(customError);
    }

    @ExceptionHandler(DataHandlingException.class)
    public ResponseEntity<Object> dataHandlingExceptionHandler(DataHandlingException ex) {
        ErrorMessage customError = ErrorMessage.builder()
                .eventTime(ZonedDateTime.now())
                .error("Error occurred while handling input data: Part of the input data could not be processed.")
                .errorDescription(ex.getLocalizedMessage())
                .errorCode(HttpStatus.BAD_REQUEST)
                .build();
        log.error(customError.toString(), ex);
        return ResponseEntity.badRequest().body(customError);
    }

}
