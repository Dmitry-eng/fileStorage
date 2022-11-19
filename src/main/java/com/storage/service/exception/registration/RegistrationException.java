package com.storage.service.exception.registration;

import com.storage.service.exception.ServiceException;
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
