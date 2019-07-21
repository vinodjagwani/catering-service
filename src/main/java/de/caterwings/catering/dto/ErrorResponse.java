package de.caterwings.catering.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ErrorResponse {

    private int code;

    private String message;

    private List<ErrorInfo> errors;

    @Value
    @Builder
    public static class ErrorInfo {

        private String domain;

        private String reason;

        private String message;

    }
}