package com.storage.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //    @ExceptionHandler(ServiceException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public ErrorResponse handleNoRecordFoundException(ServiceException ex) {
//        ErrorResponse errorResponse = new ErrorResponse();
////        errorResponse.setMessage("No Record Found");
//        return errorResponse;
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public ErrorResponse handleDefaultException(Exception ex) {
//        ErrorResponse response = new ErrorResponse();
////        response.setMessage(ex.getMessage());
//        return response;
//    }

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse securityException(SecurityException ex) {
        return buildResponse(ex.getCode());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse serviceException(ServiceException ex) {
        return buildResponse(ex.getCode());
    }



//    @ExceptionHandler(SecurityException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ErrorResponse securityException(SecurityException ex) {
//        return buildResponse(ex.getCode().name());
//    }
//
//
//    @ExceptionHandler(GroupException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ErrorResponse groupException(GroupException ex) {
//        return buildResponse(ex.getCode().name());
//    }
//
//    @ExceptionHandler(RegistrationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ErrorResponse registrationException(RegistrationException ex) {
//        return buildResponse(ex.getCode().name());
//    }

    private ErrorResponse buildResponse(String name) {
        return new ErrorResponse(name, LocalDateTime.now());
    }
}
