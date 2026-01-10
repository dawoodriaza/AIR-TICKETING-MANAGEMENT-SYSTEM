package com.ga.airticketmanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NonNull;

import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ApiErrorResponse(String code, String message, Map<String, Object> data) {
    public ApiErrorResponse(String code, String message) {
        this(code, message, Map.of());
    }
}
