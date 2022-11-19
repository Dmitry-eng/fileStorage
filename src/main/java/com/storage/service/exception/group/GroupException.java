package com.storage.service.exception.group;

import com.storage.service.exception.ServiceException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GroupException extends ServiceException {
    private final Group code;

    @Override
    public String getCode() {
        return code.name();
    }
}
