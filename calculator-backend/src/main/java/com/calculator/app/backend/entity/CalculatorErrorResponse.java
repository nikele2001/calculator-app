package com.calculator.app.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculatorErrorResponse extends CalculatorResponse {
    private String error;

    private CalculatorErrorResponse(String error) {
        this.error = error;
    }

    public static CalculatorErrorResponse of(String message) {
        return new CalculatorErrorResponse(message);
    }
}
