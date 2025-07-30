package com.mikadev.autocontrolapi.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ErrorDTO(
        int errorCode,
        LocalDateTime time,
        String httpMethod,
        String path,
        String errorMessage,
        Map<String, String> errorDetails,
        String customMessage
) {
    public record FieldErrorDTO(String field, String message) {}
}
