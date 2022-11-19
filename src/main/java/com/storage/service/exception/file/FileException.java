package com.storage.service.exception.file;

import com.storage.service.exception.ServiceException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileException extends ServiceException {
    private final FileCode code;

    @Override
    public String getCode() {
        return code.name();
    }
}
