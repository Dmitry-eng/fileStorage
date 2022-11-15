package com.storage.service.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileException extends ServiceException {
    private final FileCode code;

    @Override
    public String getCode() {
        return code.name();
    }
}
