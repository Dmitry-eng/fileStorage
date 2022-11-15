package com.storage.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SecurityException extends ServiceException {
    private final Security code;

    @Override
    public String getCode() {
        return code.name();
    }
}
