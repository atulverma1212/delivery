package com.scalable.service.delivery.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(LocalDateTime timestamp, String message, String errorCode, Map<String, String> errors) {
    public ErrorResponse(LocalDateTime timestamp, String message, String errorCode) {
        this(timestamp, message, errorCode, null);
    }
}
