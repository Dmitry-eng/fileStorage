package com.storage.service.exception;

public abstract class ServiceException extends RuntimeException {
    public abstract String getCode();
}
