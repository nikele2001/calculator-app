package com.calculator.app.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CalculatorRequest {
    private String num1;
    private String num2;
    private String operation;
}
