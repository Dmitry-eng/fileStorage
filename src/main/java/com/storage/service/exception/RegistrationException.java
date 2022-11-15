package com.storage.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationException extends ServiceException {
    private final Registration code;

    @Override
    public String getCode() {
        return code.name();
    }
}
