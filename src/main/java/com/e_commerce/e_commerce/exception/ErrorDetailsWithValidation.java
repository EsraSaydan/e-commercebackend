package com.e_commerce.e_commerce.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetailsWithValidation {
    private LocalDateTime timestamp;
    private String path;
    private int errorCode;
    private Map<String, String> validationErrors;
}
