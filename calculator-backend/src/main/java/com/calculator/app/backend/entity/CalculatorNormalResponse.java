package com.calculator.app.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CalculatorNormalResponse extends CalculatorResponse {
    private String result;
}
