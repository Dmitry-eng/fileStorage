package com.storage.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private LocalDateTime dateTime;
}