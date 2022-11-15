package com.storage.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GroupException extends ServiceException{
    private final Group code;

    @Override
    public String getCode() {
        return code.name();
    }
}
