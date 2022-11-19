package com.storage.service.exception;

public abstract class ServiceException extends RuntimeException {
    protected abstract String getCode();
}
